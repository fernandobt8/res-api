package br.ufsc.bridge.res.sumarioalta.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import br.ufsc.bridge.res.util.json.JsonPathProperty;
import br.ufsc.bridge.res.util.json.ListStringJsonPathValueConverter;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

@Getter
@Setter
@NoArgsConstructor
public class ResSumarioAltaMedicamentoNaoEstruturado implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonPathProperty(value = ".items[?(@.name.value == 'Recipiente')]"
			+ ".data"
			+ ".items.items"
			+ ".value.value", converter = ListStringJsonPathValueConverter.class)
	private List<String> descricoes = new ArrayList<>();

	//Workaround para não mexer no ResJsonUtils
	public void setDescricoes(List<?> value) {
		List<?> newValue = value;
		if (!CollectionUtils.isEmpty(newValue) && newValue.get(0) instanceof List) {
			newValue = (List<?>) value.get(0);
		}
		this.descricoes = (List<String>) newValue;
	}

	public ResSumarioAltaMedicamentoNaoEstruturado(XPathFactoryAssist xPathMedicamentoNaoEstruturado) throws XPathExpressionException {
		String descricao = xPathMedicamentoNaoEstruturado
				.getString("./data/Medicamentos_prescritos_na_alta__openBrkt_não_estruturado_closeBrkt_/Descrição_da_prescrição/value/value");
		if (descricao != null) {
			for (String medicamento : descricao.split(";")) {
				if (StringUtils.isNotBlank(medicamento)) {
					this.descricoes.add(medicamento);
				}
			}
		}
	}

}
