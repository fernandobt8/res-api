package br.ufsc.bridge.res.dab.caracterizacaoconsulta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABAdmissaoPaciente {

	@XmlElement(name = "data")
	private ResABAdmissaoPacienteData data;
}
