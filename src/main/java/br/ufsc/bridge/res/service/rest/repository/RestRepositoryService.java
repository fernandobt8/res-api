package br.ufsc.bridge.res.service.rest.repository;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.ufsc.bridge.res.domain.Sort;
import br.ufsc.bridge.res.service.rest.repository.dto.ItemDTO;
import br.ufsc.bridge.res.service.rest.repository.dto.RestRepositorySaveDTO;
import br.ufsc.bridge.res.service.rest.repository.dto.ResultDTO;
import br.ufsc.bridge.res.service.rest.repository.dto.SaveDTO;

@Slf4j
public class RestRepositoryService {

	private static final String PARAMS = "?subject=%s&_sort=%sdate";

	private final String url;

	public RestRepositoryService(String url) {
		this.url = url;
	}

	public String save(SaveDTO dto) {
		RestRepositorySaveDTO restDto = new RestRepositorySaveDTO(dto);
		if (log.isDebugEnabled()) {
			log.debug(restDto.stringfy());
		}
		ResponseEntity<RestRepositorySaveDTO> response = new RestTemplate().exchange(this.url, HttpMethod.POST, new HttpEntity<>(restDto), RestRepositorySaveDTO.class);
		String docId = response.getHeaders().getLocation().getRawPath();

		log.debug(docId);
		return docId.substring(docId.lastIndexOf("/") + 1);

	}

	public SaveDTO read(String uuid) {
		ResponseEntity<RestRepositorySaveDTO> response = new RestTemplate().getForEntity(this.url + "/" + uuid, RestRepositorySaveDTO.class);
		if (log.isDebugEnabled()) {
			log.debug(response.getBody().stringfy());
		}
		return response.getBody().toDto();
	}

	public List<ItemDTO> list(String pacienteId, Sort sort) throws NoSuchAlgorithmException, KeyManagementException {
		// CloseableHttpClient httpClient = HttpClientBuilder.create().setSSLSocketFactory(new SSLSocketFactory(context))
		// .build();
		// HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

		// SSLContext sslContext = SSLContexts.createDefault();
		// SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
		// sslContext,
		// new String[] { "TLSv1.2", "TLSv1.1", "TLSv1" },
		// null,
		// SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		// CloseableHttpClient build = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();

		// SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
		// SSLContexts.createDefault(),
		// new String[] { "TLSv1.2" },
		// null,
		// SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		//
		// PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(
		// RegistryBuilder.<ConnectionSocketFactory>create()
		// .register("http", PlainConnectionSocketFactory.getSocketFactory())
		// .register("https", sslConnectionSocketFactory)
		// .build());
		//
		// CloseableHttpClient httpClient = HttpClientBuilder.create()
		// .setConnectionManager(poolingHttpClientConnectionManager)
		// .build();

		ResponseEntity<ResultDTO> response = new RestTemplate()
				.getForEntity(String.format(this.url + PARAMS, pacienteId, sort.getCode()), ResultDTO.class);
		return response.getBody().toItems();
	}

}
