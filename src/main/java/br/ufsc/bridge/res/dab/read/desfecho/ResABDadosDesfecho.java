package br.ufsc.bridge.res.dab.read.desfecho;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABDadosDesfecho {

	@XmlElement(name = "Desfecho__fslash__alta_do_contato_assistencial")
	private ResABDesfechoAltaContatoAssistencial desfecho;
}
