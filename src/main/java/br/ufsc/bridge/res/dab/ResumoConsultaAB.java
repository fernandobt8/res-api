package br.ufsc.bridge.res.dab;

import br.ufsc.bridge.res.dab.caracterizacaoconsulta.CaracterizacaoConsultaAB;
import br.ufsc.bridge.res.dab.caracterizacaoconsulta.localizacao.CnesLocalAtendimento;
import br.ufsc.bridge.res.dab.caracterizacaoconsulta.tipoatendimento.TipoAtendimentoAB;
import br.ufsc.bridge.res.dab.caracterizacaoconsulta.tipoatendimento.TipoAtendimentoEnum;
import br.ufsc.bridge.res.repository.write.ParentArquetypeWrapper;

public class ResumoConsultaAB extends ParentArquetypeWrapper {

	private ResumoConsultaAB(Builder builder) {
		this.childs.add(new CaracterizacaoConsultaAB(new TipoAtendimentoAB(builder.tipoAtendimentoEnum)));
		this.childs.add(new CnesLocalAtendimento(builder.cnesLocalAtendimento));
	}

	// @Override
	// public String getContent() {
	// return Base64.encodeBase64String(super.getContent().getBytes());
	// }

	@Override
	protected String openTags() {
		return "﻿<?xml version=\"1.0\" encoding=\"UTF-8\"?><Encontro xmlns:oe=\"http://schemas.openehr.org/v1\" xmlns=\"http://schemas.oceanehr.com/templates\""
				+ " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
				+ " xsi:schemaLocation=\"http://schemas.oceanehr.com/templates ResumoConsultaAB.xsd\" template_id=\"Resumo de consulta ab\"><name>"
				+ "<value>Encontro</value></name><language><terminology_id><value>ISO_639-1</value>"
				+ "</terminology_id><code_string>pt</code_string></language><territory><terminology_id>"
				+ "<value>ISO_3166-1</value></terminology_id><code_string>BR</code_string></territory><category>"
				+ "<value>event</value><defining_code><terminology_id><value>openehr</value></terminology_id><code_string>433</code_string></defining_code></category>"
				+ "<composer xsi:type=\"oe:PARTY_SELF\"></composer><context><start_time><oe:value>2016-10-04T17:04:00.000-03:00</oe:value></start_time><setting>"
				+ "<oe:value>other care</oe:value><oe:defining_code><oe:terminology_id><oe:value>openehr</oe:value></oe:terminology_id><oe:code_string>238</oe:code_string>"
				+ "</oe:defining_code></setting></context>";

	}

	@Override
	protected String closeTags() {
		return "</Encontro>";
	}

	public static class Builder {

		private final TipoAtendimentoEnum tipoAtendimentoEnum;
		private String cnesLocalAtendimento;

		public Builder(TipoAtendimentoEnum tipoAtendimentoEnum) {
			this.tipoAtendimentoEnum = tipoAtendimentoEnum;
		}

		public Builder cnesLocalAtendimento(String cnesLocalAtendimento) {
			this.cnesLocalAtendimento = cnesLocalAtendimento;
			return this;
		}

		public ResumoConsultaAB build() {
			return new ResumoConsultaAB(this);
		}

	}

}
