package br.ufsc.bridge.res.dab.writer.xml.procedimentospequenascirurgias;

import java.util.Date;

import br.ufsc.bridge.res.dab.writer.xml.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;
import br.ufsc.bridge.res.util.RDateUtil;

public class ProcedimentoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String nome;
	private Date data;
	private String codigo;
	//	private List<String> resultadoObservacoes;

	public ProcedimentoBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	public String getValue() {
		//		String tagRresultadoObservacoes = "";
		//		if (this.resultadoObservacoes != null && !this.resultadoObservacoes.isEmpty()) {
		//			for (String observacao : this.resultadoObservacoes) {
		//
		//				tagRresultadoObservacoes += " <Resultado_e_fslash_ou_observações_do_procedimento_ou_pequena_cirurgia><name>"
		//						+ "<value>Resultado e/ou observações do procedimento ou pequena cirurgia</value></name><value><oe:value>";
		//				tagRresultadoObservacoes += observacao;
		//				tagRresultadoObservacoes += "</oe:value></value></Resultado_e_fslash_ou_observações_do_procedimento_ou_pequena_cirurgia>";
		//			}
		//		}

		if (this.nome != null || this.codigo != null) {
			return RDateUtil.dateToISOEHR(this.data) + this.openTagsNome() + this.nome + this.openTagsCodigo() + this.codigo + this.closeTagsCodigo() + this.openTagsData() + RDateUtil.dateToISOEHR(this.data);
		}
		return null;
	}

	@Override
	protected String openTags() {
		return "<Procedimento><name><value>Procedimento</value></name><language><terminology_id><value>ISO_639-1</value></terminology_id><code_string>pt</code_string></language><encoding><terminology_id><value>IANA_character-sets</value></terminology_id><code_string>UTF-8</code_string></encoding><subject xsi:type=\"oe:PARTY_SELF\"/><time><oe:value>";
	}

	private String openTagsNome() {
		return "</oe:value></time><description><Procedimento_SUS><name><value>Procedimento SUS</value></name><value><oe:value>";
	}

	private String openTagsCodigo() {
		return "</oe:value><oe:defining_code><oe:terminology_id><oe:value>SIGTAP_201712.v1.0.0</oe:value></oe:terminology_id><oe:code_string>";
	}

	private String closeTagsCodigo() {
		return "</oe:code_string></oe:defining_code></value></Procedimento_SUS>";
	}

	private String openTagsData() {
		return "<Data_da_realização><name><value>Data da realização</value></name><value><oe:value>";
	}

	@Override
	protected String closeTags() {
		return "</oe:value></value></Data_da_realização><Status_do_procedimento><name><value>Status do procedimento</value></name><value><value>Concluído</value><defining_code><terminology_id><value>local</value></terminology_id><code_string>at0.3</code_string></defining_code></value></Status_do_procedimento></description><ism_transition><current_state><oe:value>completed</oe:value><oe:defining_code><oe:terminology_id><oe:value>openehr</oe:value></oe:terminology_id><oe:code_string>532</oe:code_string></oe:defining_code></current_state></ism_transition></Procedimento>";
	}

	public ProcedimentoBuilder<PARENT> nome(String nome) {
		this.nome = nome;
		return this;
	}

	public ProcedimentoBuilder<PARENT> data(Date data) {
		this.data = data;
		return this;
	}

	public ProcedimentoBuilder<PARENT> codigo(String codigo) {
		this.codigo = codigo;
		return this;
	}

	//	public ProcedimentoBuilder<PARENT> resultadoObservacoes(List<String> resultadoObservacoes) {
	//		this.resultadoObservacoes = resultadoObservacoes;
	//		return this;
	//	}

}
