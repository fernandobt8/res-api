package br.ufsc.bridge.res.dab.caracterizacaoconsulta.profissional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABIdentificacaoProfissional {

	@XmlElement(name = "_exclm___-__Número_CNS_do_profissional")
	private ResABNumeroCnsProfissional cns;

	@XmlElement(name = "_exclm___-__Código_CBO_do_profissional")
	private ResABNumeroCboProfissional cbo;

	@XmlElement(name = "É_o_responsável_pelo_atendimento")
	private ResABResponsavelPeloAtendimento responsavelAtendimento;
}
