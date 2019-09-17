package br.ufsc.bridge.res.service.rest.repository;

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

	private static final String PATH = "/ehr-services/fhir/r4/DocumentReference/";

	private static final String URL = "https://ehr-services.rnds.mbamobi.com.br" + PATH;

	private static final String PARAMS = "?subject=%s&_include=DocumentReference:subject&_include=DocumentReference:author&_sort=%sdate";


	public String save(SaveDTO dto) {
		RestRepositorySaveDTO restDto = new RestRepositorySaveDTO(dto);
		log.debug(restDto.stringfy());
		ResponseEntity<RestRepositorySaveDTO> response = new RestTemplate().exchange(URL, HttpMethod.POST, new HttpEntity<>(restDto), RestRepositorySaveDTO.class);
		String docId = response.getHeaders().getLocation().getRawPath().replace(PATH, "");
		log.debug(docId);
		return docId;

	}

	public SaveDTO read(String uuid) {
		ResponseEntity<RestRepositorySaveDTO> response = new RestTemplate().getForEntity(URL + "/" + uuid, RestRepositorySaveDTO.class);
		log.debug(response.getBody().stringfy());
		return response.getBody().toDto();
	}

	public List<ItemDTO> list(String pacienteId, Sort sort) {
		ResponseEntity<ResultDTO> response = new RestTemplate().getForEntity(String.format(URL + PARAMS, pacienteId,  sort.getCode()), ResultDTO.class);
		return response.getBody().toItems();
	}



}
