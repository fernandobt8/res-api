package br.ufsc.bridge.res.dab.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABMagnitudeUnits {

	@XmlElement(name = "magnitude")
	private String magnitude;

	@XmlElement(name = "units")
	private String units;
}
