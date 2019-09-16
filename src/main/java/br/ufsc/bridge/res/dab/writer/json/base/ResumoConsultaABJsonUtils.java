package br.ufsc.bridge.res.dab.writer.json.base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

public class ResumoConsultaABJsonUtils {

	private static Map<String, String> jsons = new HashMap<>();

	public static DocumentContext getJsonDocument(String name) {
		return JsonPath.parse(jsons.get(name));
	}

	static {
		try {
			putJson("resumo-consulta", null, null);
			putJson("procedimento-realizado", "procedimento", "$.items[0]");
			putJson("problema-diagnostico", "problema", "$.items[0]");
			putJson("prescricao-atendimento",
					"medicamento-nao-estruturado",
					"$.items.data.items[?(@.name.value == 'Medicamentos prescritos no atendimento (não estruturado)')]");

			putJson("medicoes-observacoes", null, null);
			putJson("dados-desfecho", "desfecho", "$.items.data.items[0]");
			putJson("caracterizacao-atendimento", "profissional", "$.items.data.items[?(@.name.value == 'Profissionais do atendimento')]");
			putJson("alergia-reacoes", "alergia", "$.items[0]");

			DocumentContext alergia = getJsonDocument("alergia");
			String eventoJson = new ObjectMapper().writeValueAsString(((JSONArray) alergia.read("$.data.items[?(@.name.value == 'Evento da reação')]")).get(0));
			alergia.delete("$.data.items[?(@.name.value == 'Evento da reação')]");
			jsons.put("alergia", alergia.jsonString());
			jsons.put("evento-alergia", eventoJson);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao carregar templates json");
		}
	}

	private static String putJson(String name, String childName, String childPath) throws IOException {
		InputStream resourceAsStream = ResumoConsultaABJsonUtils.class.getClassLoader().getResourceAsStream("json.parties" + File.separator + name + ".json");
		String jsonValue = IOUtils.toString(resourceAsStream, Charset.forName("UTF-8"));
		if (childName != null) {
			DocumentContext document = JsonPath.parse(jsonValue);
			Object childObject = document.read(childPath);
			if (childObject instanceof JSONArray) {
				childObject = ((JSONArray) childObject).get(0);
			}
			jsons.put(childName, new ObjectMapper().writeValueAsString(childObject));
			document.delete(childPath);
			jsonValue = document.jsonString();
		}
		jsons.put(name, jsonValue);
		return jsonValue;
	}
}
