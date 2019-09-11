package br.ufsc.bridge.res.dab.writer.xml.listamedicamentos;

import br.ufsc.bridge.res.dab.domain.ResABEstadoMedicamentoEnum;
import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class ItemMedicacaoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	private String nomeMedicamento;
	private String codigoMedicamentoCatmat;
	private String descricaoFormaFarmaceutica;
	private String codigoFormaFarmaceutica;
	private String descricaoViaAdministracao;
	private String codigoViaAdministracao;
	private String descricaoDose;
	private String duracaoTratamento;
	private ResABEstadoMedicamentoEnum estadoMedicamento;

	public ItemMedicacaoBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	public String getValue() {
		if (this.nomeMedicamento != null || this.codigoMedicamentoCatmat != null || this.descricaoFormaFarmaceutica != null || this.codigoFormaFarmaceutica != null
				|| this.descricaoViaAdministracao != null || this.codigoViaAdministracao != null || this.descricaoDose != null || this.duracaoTratamento != null
				|| this.estadoMedicamento != null) {
			StringBuilder xmlMedicao = new StringBuilder();
			xmlMedicao.append(this.nomeMedicamento);
			xmlMedicao.append(this.openTagsCodigoMedicamento());
			xmlMedicao.append(this.codigoMedicamentoCatmat);
			xmlMedicao.append(this.openTagsDescricaoFormaFarmaceutica());
			xmlMedicao.append(this.descricaoFormaFarmaceutica);
			xmlMedicao.append(this.openTagsCodigoFormaFarmaceutica());
			xmlMedicao.append(this.codigoFormaFarmaceutica);
			xmlMedicao.append(this.openTagsDescricaoViaAdministracao());
			xmlMedicao.append(this.descricaoViaAdministracao);
			xmlMedicao.append(this.openTagsCodigoViaAdministracao());
			xmlMedicao.append(this.codigoViaAdministracao);
			xmlMedicao.append(this.openTagsDescricaoDose());
			xmlMedicao.append(this.descricaoDose);
			xmlMedicao.append(this.closeTagsDescricaoDose());
			if (this.duracaoTratamento != null) {
				xmlMedicao.append(this.openTagsCodigoDoseEstruturada());
				xmlMedicao.append(this.duracaoTratamento);
				xmlMedicao.append(this.closeTagsCodigoDoseEstruturada());
			}
			if (this.estadoMedicamento != null) {
				xmlMedicao.append(this.openTagsDetalhesDoProcessoMedicao());
				xmlMedicao.append(this.estadoMedicamento.getCodigo());
				xmlMedicao.append(this.closeTagsDetalhesDoProcessoMedicao());
			}
			return xmlMedicao.toString();
		}
		return null;
	}

	private String openTagsCodigoMedicamento() {
		return "</value><defining_code><terminology_id><value>HORUS</value></terminology_id><code_string>";
	}

	private String openTagsDescricaoFormaFarmaceutica() {
		return "</code_string></defining_code></value></Medicamento><Forma_farmacêutica><name><value>Forma farmacêutica</value></name><value><value>";
	}

	private String openTagsCodigoFormaFarmaceutica() {
		return "</value><defining_code><terminology_id><value>HORUS</value></terminology_id><code_string>";
	}

	private String openTagsDescricaoViaAdministracao() {
		return "</code_string></defining_code></value></Forma_farmacêutica><Via_de_administração><name><value>Via de administração</value></name><value><value>";
	}

	private String openTagsCodigoViaAdministracao() {
		return "</value><defining_code><terminology_id><value>ANVISA-VIAS-ADM</value></terminology_id><code_string>";
	}

	private String openTagsDescricaoDose() {
		return "</code_string></defining_code></value></Via_de_administração><Dose><name><value>Dose</value></name><value><oe:value>";
	}

	private String closeTagsDescricaoDose() {
		return "</oe:value></value></Dose>";
	}

	private String openTagsCodigoDoseEstruturada() {
		return "<Dose_estruturada><name><value>Dose estruturada</value></name><Duração_do_tratamento><name><value>Duração do tratamento</value></name><value><oe:value>";
	}

	private String closeTagsCodigoDoseEstruturada() {
		return "</oe:value></value></Duração_do_tratamento></Dose_estruturada>";
	}

	private String openTagsDetalhesDoProcessoMedicao() {
		return "<Detalhes_do_processo_medicação><name><value>Detalhes do processo medicação</value>"
				+ "</name><Estado_do_medicamento><name><value>Estado do medicamento</value></name><value><defining_code><terminology_id><value>local</value>"
				+ "</terminology_id><code_string>";
	}

	private String closeTagsDetalhesDoProcessoMedicao() {
		return "</code_string></defining_code></value></Estado_do_medicamento></Detalhes_do_processo_medicação>";
	}

	@Override
	protected String openTags() {
		return "<Linha_de_Medicação><name><value>Linha de Medicação</value></name><language><terminology_id><value>ISO_639-1</value></terminology_id>"
				+ "<code_string>pt</code_string></language><encoding><terminology_id><value>IANA_character-sets</value></terminology_id><code_string>UTF-8</code_string>"
				+ "</encoding><subject></subject><data><Item_de_medicação><name><value>Item de medicação</value></name><Medicamento><name><value>Medicamento</value>"
				+ "</name><value><value>";
	}

	@Override
	protected String closeTags() {
		return "</Item_de_medicação></data></Linha_de_Medicação>";
	}

	public ItemMedicacaoBuilder<PARENT> medicamento(String nome, String codigoCatmat) {
		this.nomeMedicamento = nome;
		this.codigoMedicamentoCatmat = codigoCatmat;
		return this;
	}

	public ItemMedicacaoBuilder<PARENT> formaFarmaceutica(String descricao, String codigo) {
		this.descricaoFormaFarmaceutica = descricao;
		this.codigoFormaFarmaceutica = codigo;
		return this;
	}

	public ItemMedicacaoBuilder<PARENT> viaAdministracao(String descricao, String codigo) {
		this.descricaoViaAdministracao = descricao;
		this.codigoViaAdministracao = codigo;
		return this;
	}

	public ItemMedicacaoBuilder<PARENT> dose(String descricao) {
		this.descricaoDose = descricao;
		return this;
	}

	public ItemMedicacaoBuilder<PARENT> doseEstruturada(String codigo) {
		this.duracaoTratamento = codigo;
		return this;
	}

	public ItemMedicacaoBuilder<PARENT> estadoMedicamento(ResABEstadoMedicamentoEnum estadoMedicamento) {
		this.estadoMedicamento = estadoMedicamento;
		return this;
	}

}
