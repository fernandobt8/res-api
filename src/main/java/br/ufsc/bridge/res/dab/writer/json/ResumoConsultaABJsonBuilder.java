package br.ufsc.bridge.res.dab.writer.json;

import java.io.IOException;
import java.util.Date;

import br.ufsc.bridge.res.util.RDateUtil;

public class ResumoConsultaABJsonBuilder extends BaseJsonBuilder<ResumoConsultaABJsonBuilder> {

	public ResumoConsultaABJsonBuilder() throws IOException {
		super("resumo-consulta.json");
	}

	@Override
	protected String childJsonPath() {
		return "$.content";
	}

	public ResumoConsultaABJsonBuilder data(Date date) {
		this.document.set("$.context.start_time.value", RDateUtil.dateToISOEHR(date));
		return this;
	}

	public CaracterizacaoConsultaABJsonBuilder<ResumoConsultaABJsonBuilder> caracterizacaoConsulta() {
		return new CaracterizacaoConsultaABJsonBuilder<>(this);
	}

	public MedicoesObservacoesJsonBuilder<ResumoConsultaABJsonBuilder> medicoesObservacoes() {
		return new MedicoesObservacoesJsonBuilder<>(this);
	}
}
