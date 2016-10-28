package br.ufsc.bridge.res.dab.medicoesobservacoes.crianca;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABAlimentacaoCriancaMenorDoisAnosQualquerEventoData {

	@XmlElement(name = "Aleitamento_materno")
	private ResABAleitamentoMaterno aleitamentoMaterno;
}
