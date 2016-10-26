package br.ufsc.bridge.res.dab.caracterizacaoconsulta;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import br.ufsc.bridge.res.dab.caracterizacaoconsulta.equipe.ResABIdentificaçãoEquipeSaude;
import br.ufsc.bridge.res.dab.caracterizacaoconsulta.horaadmissao.ResABDataHoraAdmissao;
import br.ufsc.bridge.res.dab.caracterizacaoconsulta.localizacao.ResABLocalizacaoAtribuidaPaciente;
import br.ufsc.bridge.res.dab.caracterizacaoconsulta.profissional.ResABIdentificacaoProfissional;
import br.ufsc.bridge.res.dab.caracterizacaoconsulta.tipoatendimento.ResABTipoAtendimento;
import br.ufsc.bridge.res.dab.caracterizacaoconsulta.turno.ResABTurnoAtendimento;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ResABAdmissaoPacienteData {

	@XmlElement(name = "Tipo_de_atendimento")
	private ResABTipoAtendimento tipoAtendimento;

	@XmlElement(name = "Localização_atribuída_ao_paciente")
	private ResABLocalizacaoAtribuidaPaciente localizacaoAtribuidaPaciente;

	@XmlElement(name = "Identificação_do_profissional")
	private List<ResABIdentificacaoProfissional> profissionais;

	@XmlElement(name = "Identificação_da_equipe_de_saúde")
	private ResABIdentificaçãoEquipeSaude equipe;

	@XmlElement(name = "_exclm___-__Data_fslash_hora_de_admissão")
	private ResABDataHoraAdmissao dataHoraAdmissao;

	@XmlElement(name = "Turno_de_atendimento")
	private ResABTurnoAtendimento turno;
}
