package br.ufsc.bridge.res.dab.medicoesobservacoes.crianca;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.common.ResABQualquerEvento;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABAlimentacaoCriancaMenorDoisAnosQualquerEventoData extends ResABQualquerEvento {

	@XmlElement(name = "Aleitamento_materno")
	private ResABAleitamentoMaterno aleitamentoMaterno;
}
