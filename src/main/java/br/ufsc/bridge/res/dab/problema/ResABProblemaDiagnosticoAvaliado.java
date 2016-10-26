package br.ufsc.bridge.res.dab.problema;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABProblemaDiagnosticoAvaliado {

	@XmlElement(name = "Problema__fslash_Diagnóstico")
	private List<ResABProblemaDiagnostico> problemaDiagnostico;
}
