package br.ufsc.bridge.res.dab.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.ResABEncontro;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABStringValueEHR {

	@XmlElement(name = "value", namespace = ResABEncontro.OPEN_EHR_NAMESPACE)
	private String value;
}
