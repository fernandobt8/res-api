package br.ufsc.bridge.res.dispensacaomedicamentos.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.util.json.JsonPathProperty;

@Getter
@Setter
@NoArgsConstructor
public class ResponsavelPrescricao implements Serializable {

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Nome da pessoa')]"
			+ ".items.value.value")
	private String nome;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'CNS do profissional responsável pela prescrição')]"
			+ ".value.value")
	private String cns;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Código Brasileiro de Ocupação')]"
			+ ".value.value")
	private String cbo;
}
