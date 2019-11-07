package br.ufsc.bridge.res.dispensacaomedicamentos.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.util.json.JsonPathProperty;

@Getter
@Setter
@NoArgsConstructor
public class ResponsavelDispensacao implements Serializable {

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Nome da pessoa')]"
			+ ".items.value.value")
	private String nome;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Conselho profissional')]"
			+ ".items[?(@.name.value == 'Tipo de conselho profissional')]"
			+ ".value.value")
	private String tipoConselhoProfissional;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Conselho profissional')]"
			+ ".items[?(@.name.value == 'Unidade Federativa')]"
			+ ".value.value")
	private String unidadeFederativa;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Conselho profissional')]"
			+ ".items[?(@.name.value == 'Número do registro')]"
			+ ".value.value")
	private String numeroRegistro;
}
