package br.ufsc.bridge.res.dab.read.caracterizacaoconsulta.profissional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.read.common.ResABBooleanValueEHR;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABResponsavelPeloAtendimento {

	@XmlElement(name = "value")
	private ResABBooleanValueEHR value;
}
