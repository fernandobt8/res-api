package br.ufsc.bridge.res.dab.read.caracterizacaoconsulta.tipoatendimento;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.read.common.ResABValueDefiningCode;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABTipoAtendimento {

	@XmlElement(name = "value")
	private ResABValueDefiningCode value;
}
