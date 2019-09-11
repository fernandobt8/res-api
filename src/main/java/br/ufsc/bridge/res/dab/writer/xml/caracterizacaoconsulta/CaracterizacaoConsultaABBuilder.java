package br.ufsc.bridge.res.dab.writer.xml.caracterizacaoconsulta;

import java.util.Date;

import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class CaracterizacaoConsultaABBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public CaracterizacaoConsultaABBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Caracterização_do_atendimento><name><value>Caracterização do atendimento</value></name><Admissão_do_paciente><name><value>Admissão do paciente</value></name><language><terminology_id><value>ISO_639-1</value></terminology_id><code_string>pt</code_string></language><encoding><terminology_id><value></value></terminology_id><code_string></code_string></encoding><subject></subject><data>";
	}

	@Override
	protected String closeTags() {
		return "</data></Admissão_do_paciente></Caracterização_do_atendimento>";
	}

	public CaracterizacaoConsultaABBuilder<PARENT> tipoAtendimento(ResABTipoAtendimentoEnum value) {
		new TipoAtendimentoABBuilder<>(this, value);
		return this;
	}

	public CaracterizacaoConsultaABBuilder<PARENT> localizacaoAtribuidaPaciente(String cnes, String ine) {
		new LocalizacaoAtribuidaPacienteBuilder<>(this, cnes, ine);
		return this;
	}

	public IdentificacaoProfissionalBuilder<CaracterizacaoConsultaABBuilder<PARENT>> identificacaoProfissional() {
		return new IdentificacaoProfissionalBuilder<>(this);
	}

	public CaracterizacaoConsultaABBuilder<PARENT> dataHoraAdmissao(Date value) {
		new DataHoraAdmissaoBuilder<>(this, value);
		return this;
	}

}
