package br.ufsc.bridge.res.dab.write.builder.medicoesobservacoes.gestante;

import br.ufsc.bridge.res.dab.write.builder.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;

public class SumarioObstetricoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String gestasPrevias;
	private String partos;

	public SumarioObstetricoBuilder(PARENT parent, String gestasPrevias, String partos) {
		super(parent);
		this.gestasPrevias = gestasPrevias;
		this.partos = partos;
	}

	@Override
	public String getValue() {
		String value = "";
		if (this.gestasPrevias != null) {
			value += this.openTagsGestas() + this.gestasPrevias + this.closeTagsGestas();
		}
		if (this.partos != null) {
			value += this.openTagsPartos() + this.partos + this.closeTagsPartos();
		}
		return value;
	}

	@Override
	protected String openTags() {
		return "<Sumário_obstétrico><name><value>Sumário obstétrico</value></name>"
				+ "<language><terminology_id><value>ISO_639-1</value></terminology_id>"
				+ "<code_string>pt</code_string></language><encoding><terminology_id>"
				+ "<value>IANA_character-sets</value></terminology_id>"
				+ "<code_string>UTF-8</code_string></encoding><subject></subject><data>";
	}

	public String openTagsGestas() {
		return "<Gestas_prévias><name><value>Gestas prévias</value></name><value><magnitude>";
	}

	public String closeTagsGestas() {
		return "</magnitude></value></Gestas_prévias>";
	}

	public String openTagsPartos() {
		return "<Partos><name><value>Partos</value></name><value><magnitude>";
	}

	public String closeTagsPartos() {
		return "</magnitude></value></Partos>";
	}

	@Override
	protected String closeTags() {
		return "</data></Sumário_obstétrico>";
	}
}
