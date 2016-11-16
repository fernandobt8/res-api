package br.ufsc.bridge.res.dab.write.medicoesobservacoes.gestante;

import java.util.Date;

import lombok.Getter;

import br.ufsc.bridge.res.dab.write.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.base.ParentArquetypeWrapper;
import br.ufsc.bridge.res.util.RDateUtil;

@Getter
public class GestacaoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String value;
	private Date dataMedida;

	public GestacaoBuilder(PARENT parent, String idadeGestacional, Date dataMedida) {
		super(parent);
		this.value = idadeGestacional;
		this.dataMedida = dataMedida;
	}

	@Override
	protected String openTags() {
		return "<Gestação><name><value>Gestação</value></name><language><terminology_id>"
				+ "<value>ISO_639-1</value></terminology_id><code_string>pt</code_string>"
				+ "</language><encoding><terminology_id><value>IANA_character-sets</value>"
				+ "</terminology_id><code_string>UTF-8</code_string></encoding><subject></subject>"
				+ "<data><Data_da_medida><name><value>Data da medida</value></name><time><oe:value>";
	}

	public String openTagsIdade() {
		return "</oe:value></time><data><Idade_gestacional><name><value>Idade gestacional</value></name><value><value>";
	}

	@Override
	protected String closeTags() {
		return "</value></value></Idade_gestacional></data></Data_da_medida></data></Gestação>";
	}

	@Override
	public String getXmlContent() {
		return this.openTags() + RDateUtil.dateToISOEHR(this.dataMedida) + this.openTagsIdade() + this.getValue() + this.closeTags();
	}
}
