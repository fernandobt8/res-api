package br.ufsc.bridge.res.dab.read.caracterizacaoconsulta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABCaracterizacaoConsulta {

	@XmlElement(name = "Admissão_do_paciente")
	private ResABAdmissaoPaciente admissaoPaciente;
}
