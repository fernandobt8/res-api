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
		return "<Problema__fslash_Diagnóstico><name><value>Problema /Diagnóstico</value></name>"
				+ "<language><terminology_id><value>ISO_639-1</value></terminology_id>"
				+ "<code_string>pt</code_string></language><encoding><terminology_id><value>IANA_character-sets"
				+ "</value></terminology_id><code_string>UTF-8</code_string></encoding><subject></subject>"
				+ "<data><Problema__fslash__Diagnóstico><name><value>Problema / Diagnóstico</value></name><value><value>";
	}

	private String openTagsTipo() {
		return "</value><defining_code><terminology_id><value>";
	}

	private String openTagsCodigo() {
		return "</value></terminology_id><code_string>";
	}

	@Override
	protected String closeTags() {
		return "</code_string></defining_code></value></Problema__fslash__Diagnóstico></data></Problema__fslash_Diagnóstico>";
	}

	@Override
	public String getValue() {
		return this.descricao + this.openTagsTipo() + this.tipo + this.openTagsCodigo() + this.codigo;
	}

}
