package br.ufsc.bridge.res.dab.problema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABProblemaDiagnosticoData {

	@XmlElement(name = "Problema__fslash__Diagnóstico")
	private ResABProblemaDiagnosticoValue value;

	// TODO local estruturado

	// TODO detalhes

	// TODO estado do problema
}
