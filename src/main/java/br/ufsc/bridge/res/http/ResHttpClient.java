package br.ufsc.bridge.res.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import br.ufsc.bridge.res.http.exception.ResHttpException;

@Slf4j
public class ResHttpClient {

	private CloseableHttpClient httpClient;

	private Map<String, String> customHeaders;
	private URL url;
	private HttpHost host;
	private CreateSOAPMessage soapMessage;

	public ResHttpClient(CreateSOAPMessage soapMessage, String action) {
		this.soapMessage = soapMessage;

		SSLContext ctx;
		SSLSocketFactory scsf = null;
		try {
			ctx = SSLContext.getInstance("SSL");
			ctx.init(null, new TrustManager[] { this.getTrustManager() }, new java.security.SecureRandom()); // Install the all-trusting trust manager
			scsf = ctx.getSocketFactory();
			HttpsURLConnection.setDefaultSSLSocketFactory(scsf);
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}

			}); // Install the all-trusting host verifier
		} catch (Exception e) {
			e.printStackTrace();
		}

		Registry<SSLSocketFactory> registry = RegistryBuilder.<SSLSocketFactory> create()
				.register("https", scsf)
				.build();

		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(60000)
				.setConnectTimeout(30000)
				.setExpectContinueEnabled(false)
				.setRedirectsEnabled(true)
				.build();

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setValidateAfterInactivity(10000);
		cm.setDefaultSocketConfig(SocketConfig.custom().setTcpNoDelay(true).build());

		this.httpClient = HttpClients.custom()
				// .setSSLSocketFactory(scsf)
				.setDefaultRequestConfig(requestConfig)
				// .setConnectionManager(cm)
				.build();

		this.customHeaders = new HashMap<>();
		this.putHeader("Content-Type", "application/soap+xml;charset=UTF-8;action=\"" + action + "\"");
		this.putHeader("Accept-Encoding", "gzip,deflate");
		this.putHeader("Cookie", "BIGipServerservicoshm.saude.gov.br=3298339008.20480.0000");
		// this.putHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
	}

	private X509TrustManager getTrustManager() {
		return new X509TrustManager() {

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			}
		};
	}

	public void putHeader(String key, String value) {
		this.customHeaders.put(key, value);
	}

	public void setUrl(String url) throws MalformedURLException {
		this.url = new URL(url);
		this.host = new HttpHost(this.url.getHost(), this.url.getPort(), this.url.getProtocol());
	}

	public <T> T send(Object jaxbObject, Class<T> response) throws ResHttpException {
		HttpPost httpPost = null;
		InputStream is = null;
		try {
			httpPost = new HttpPost("");
			// httpPost.setProtocolVersion(HttpVersion.HTTP_1_1);

			httpPost.setEntity(this.createMessage(jaxbObject));

			for (Map.Entry<String, String> header : this.customHeaders.entrySet()) {
				httpPost.setHeader(header.getKey(), header.getValue());
			}

			HttpResponse httpResponse = this.httpClient.execute(this.host, httpPost);
			int responseCode = httpResponse.getStatusLine().getStatusCode();
			if (responseCode != HttpStatus.SC_OK) {
				throw new ResHttpException("HTTP Response code: " + responseCode);
			}

			is = httpResponse.getEntity().getContent();

			try {
				EntityUtils.consume(httpResponse.getEntity());
			} catch (IOException ioe) {
				log.info("Error consume res, it might only mean the server has no keep-alive capability.", ioe.getMessage());
			}

			return this.createResponse(is, response);
		} catch (IOException e) {
			if (null != httpPost) {
				httpPost.abort();
			}
			throw new ResHttpException("Error in connection", e);
		} catch (JAXBException | ParserConfigurationException | SOAPException e) {
			throw new ResHttpException("Error parsing request/response", e);
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException ioe) {
					throw new ResHttpException("Error while closing response inputStream", ioe);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T createResponse(InputStream is, Class<T> response) throws JAXBException {
		return (T) JAXBContext.newInstance(response).createUnmarshaller().unmarshal(is);
	}

	private ByteArrayEntity createMessage(Object jaxbObject) throws SOAPException, JAXBException, ParserConfigurationException, IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		this.soapMessage.create(jaxbObject).writeTo(outputStream);
		return new ByteArrayEntity(outputStream.toByteArray());
	}
}
