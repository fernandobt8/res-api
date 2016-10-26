package br.ufsc.bridge.res.dab.caracterizacaoconsulta.localizacao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABEstabelicimentoSaude {

	@XmlElement(name = "CNES_do_local_do_atendimento")
	private ResABCNESLocalAtendimento cnesLocalAtendimento;
}
