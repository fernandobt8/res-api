package br.ufsc.bridge.res.dab.read.caracterizacaoconsulta.localizacao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABLocalizacaoAtribuidaPaciente {

	// TODO LocalDeAtendimento

	@XmlElement(name = "Estabelecimento_de_saúde")
	private ResABEstabelicimentoSaude estabelecimentoSaude;
}
