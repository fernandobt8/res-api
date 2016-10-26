package br.ufsc.bridge.res.dab.desfecho;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.common.ResABValue;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABConduta {

	@XmlElement(name = "value")
	private ResABValue value;
}
