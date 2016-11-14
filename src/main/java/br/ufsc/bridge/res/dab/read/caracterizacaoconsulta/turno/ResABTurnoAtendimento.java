package br.ufsc.bridge.res.dab.read.caracterizacaoconsulta.turno;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.read.common.ResABValueDefiningCode;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABTurnoAtendimento {

	@XmlElement(name = "value")
	private ResABValueDefiningCode value;
}
