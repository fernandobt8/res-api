package br.ufsc.bridge.res.dab.read.caracterizacaoconsulta.profissional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.read.common.ResABStringValueEHR;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABNumeroCnsProfissional {

	@XmlElement(name = "value")
	private ResABStringValueEHR value;
}
