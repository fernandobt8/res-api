package br.ufsc.bridge.res.dab.caracterizacaoconsulta.localizacao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.common.ResABStringValueEHR;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABCNESLocalAtendimento {

	@XmlElement(name = "value")
	private ResABStringValueEHR value;

}
