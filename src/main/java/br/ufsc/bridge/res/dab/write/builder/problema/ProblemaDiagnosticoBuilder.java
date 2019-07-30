package br.ufsc.bridge.res.dab.write.builder.problema;

import br.ufsc.bridge.res.dab.write.builder.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;

public class ProblemaDiagnosticoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String descricao;
	private String tipo;
	private String codigo;

	public ProblemaDiagnosticoBuilder(PARENT parent) {
		super(parent);
	}

	public ProblemaDiagnosticoBuilder<PARENT> descricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public ProblemaDiagnosticoBuilder<PARENT> tipo(String tipo) {
		this.tipo = tipo;
		return this;
	}

	public ProblemaDiagnosticoBuilder<PARENT> codigo(String codigo) {
		this.codigo = codigo;
		return this;
	}

	@Override
	protected String openTags() {
		return "<Problema_Diagnóstico><name><value>Problema Diagnóstico</value></name><language><terminology_id><value>ISO_639-1</value></terminology_id><code_string>pt</code_string></language><encoding><terminology_id><value>IANA_character-sets</value></terminology_id><code_string>UTF-8</code_string></encoding><subject xsi:type=\"oe:PARTY_SELF\"/><data><Diagnóstico><name><value>Diagnóstico</value></name><value><oe:value>";
	}

	private String openTagsTipo() {
		return "</oe:value><oe:defining_code><oe:terminology_id><oe:value>";
	}

	private String openTagsCodigo() {
		return "</oe:value></oe:terminology_id><oe:code_string>";
	}

	@Override
	protected String closeTags() {
		return "</oe:code_string></oe:defining_code></value></Diagnóstico></data></Problema_Diagnóstico>";
	}

	@Override
	public String getValue() {
		if (this.descricao != null || this.tipo != null || this.codigo != null) {
			return this.descricao + this.openTagsTipo() + this.tipo + this.openTagsCodigo() + this.codigo;
		}
		return null;
	}

}
