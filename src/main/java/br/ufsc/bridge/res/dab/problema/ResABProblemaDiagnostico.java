package br.ufsc.bridge.res.dab.problema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABProblemaDiagnostico {

	@XmlElement(name = "data")
	private ResABProblemaDiagnosticoData data;
}
