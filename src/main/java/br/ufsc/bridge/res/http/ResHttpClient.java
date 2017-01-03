package br.ufsc.bridge.res.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import br.ufsc.bridge.res.http.exception.ResHttpConnectionException;
import br.ufsc.bridge.res.http.exception.ResHttpRequestResponseException;

@Slf4j
public class ResHttpClient {

	private CloseableHttpClient httpClient;

	private Map<String, String> customHeaders;
	private URL url;
	private HttpHost host;
	private CreateSOAPMessage soapMessage;

	public ResHttpClient(CreateSOAPMessage soapMessage, String action) {
		this.soapMessage = soapMessage;

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(30000).setExpectContinueEnabled(false).setRedirectsEnabled(true).build();

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setValidateAfterInactivity(10000);
		cm.setDefaultSocketConfig(SocketConfig.custom().setTcpNoDelay(true).build());

		this.httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(cm).build();

		this.customHeaders = new HashMap<>();
		this.putHeader("Content-Type", "application/soap+xml;charset=UTF-8");
		this.putHeader("Accept-Encoding", "gzip,deflate");
		this.putHeader("SOAPAction", action);
	}

	public void putHeader(String key, String value) {
		this.customHeaders.put(key, value);
	}

	public void setUrl(String url) throws MalformedURLException {
		this.url = new URL(url);
		this.host = new HttpHost(this.url.getHost(), this.url.getPort(), this.url.getProtocol());
	}

	public Document send(Object jaxbObject) throws ResHttpConnectionException, ResHttpRequestResponseException {
		HttpPost httpPost = null;
		InputStream is = null;
		try {
			httpPost = new HttpPost(this.url.getFile());

			httpPost.setEntity(this.createMessage(jaxbObject));

			for (Map.Entry<String, String> header : this.customHeaders.entrySet()) {
				httpPost.setHeader(header.getKey(), header.getValue());
			}

			HttpResponse response = this.httpClient.execute(this.host, httpPost);
			int responseCode = response.getStatusLine().getStatusCode();
			if (responseCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
				String string = IOUtils.toString(is = response.getEntity().getContent());
				log.error(string);
				throw new ResHttpRequestResponseException("HTTP Response code: " + responseCode);
			} else if (responseCode != HttpStatus.SC_OK) {
				throw new ResHttpRequestResponseException("HTTP Response code: " + responseCode);
			}

			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is = response.getEntity().getContent());
		} catch (IOException e) {
			if (null != httpPost) {
				httpPost.abort();
			}
			throw new ResHttpConnectionException("Error in connection", e);
		} catch (SAXException | JAXBException | ParserConfigurationException | SOAPException e) {
			throw new ResHttpRequestResponseException("Error parsing request/response", e);
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException ioe) {
					log.error("Error while closing response inputStream", ioe);
				}
			}
		}
	}

	private ByteArrayEntity createMessage(Object jaxbObject) throws SOAPException, JAXBException, ParserConfigurationException, IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		this.soapMessage.create(jaxbObject).writeTo(outputStream);
		return new ByteArrayEntity(outputStream.toByteArray());
	}
}
