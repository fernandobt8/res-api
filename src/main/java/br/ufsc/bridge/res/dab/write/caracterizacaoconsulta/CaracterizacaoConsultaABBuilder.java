package br.ufsc.bridge.res.dab.write.caracterizacaoconsulta;

import java.util.Date;

import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.dab.domain.ResABTurnoEnum;
import br.ufsc.bridge.res.dab.write.base.ParentArquetypeWrapper;

public class CaracterizacaoConsultaABBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public CaracterizacaoConsultaABBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Caracterização_da_consulta><name><value>Caracterização da consulta</value></name><Admissão_do_paciente><name><value>Admissão do paciente</value></name>"
				+ "<language><terminology_id><value>ISO_639-1</value></terminology_id><code_string>pt</code_string></language><encoding><terminology_id>"
				+ "<value>IANA_character-sets</value></terminology_id><code_string>UTF-8</code_string></encoding><subject></subject><data>";
	}

	@Override
	protected String closeTags() {
		return "</data></Admissão_do_paciente></Caracterização_da_consulta>";
	}

	public CaracterizacaoConsultaABBuilder<PARENT> tipoAtendimento(ResABTipoAtendimentoEnum value) {
		new TipoAtendimentoABBuilder<>(this, value);
		return this;
	}

	public CaracterizacaoConsultaABBuilder<PARENT> cnes(String value) {
		new CnesLocalAtendimentoBuilder<>(this, value);
		return this;
	}

	public IdentificacaoProfissionalBuilder<CaracterizacaoConsultaABBuilder<PARENT>> identificacaoProfissional() {
		return new IdentificacaoProfissionalBuilder<>(this);
	}

	public CaracterizacaoConsultaABBuilder<PARENT> ine(String value) {
		new IdentificacaoEquipeBuilder<>(this, value);
		return this;
	}

	public CaracterizacaoConsultaABBuilder<PARENT> dataHoraAdmissao(Date value) {
		new DataHoraAdmissaoBuilder<>(this, value);
		return this;
	}

	public CaracterizacaoConsultaABBuilder<PARENT> turnoAtendimento(ResABTurnoEnum value) {
		new TurnoAtendimentoBuilder<>(this, value);
		return this;
	}
}
