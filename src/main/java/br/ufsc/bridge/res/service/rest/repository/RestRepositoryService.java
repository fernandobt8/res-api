package br.ufsc.bridge.res.service.rest.repository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import br.ufsc.bridge.res.service.rest.repository.dto.RestRepositorySaveDTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
public class RestRepositoryService {

	private static final String URL = "https://ehr-services.rnds.mbamobi.com.br/ehr-services/fhir/r4/DocumentReference";


	public void save(RestRepositorySaveDTO dto) {

		try {
			log.debug(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(dto));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		new RestTemplate().postForObject(URL, new HttpEntity<>(dto), RestRepositorySaveDTO.class);

	}

}
