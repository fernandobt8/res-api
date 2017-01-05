package br.ufsc.bridge.res.dab.write.builder.medicoesobservacoes.crianca;

import java.util.Date;

import lombok.Getter;

import br.ufsc.bridge.res.dab.domain.ResABAleitamentoMaternoEnum;
import br.ufsc.bridge.res.dab.write.builder.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;
import br.ufsc.bridge.res.util.RDateUtil;

@Getter
public class AleitamentoMaternoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private ResABAleitamentoMaternoEnum value;
	private Date dataEvento;

	public AleitamentoMaternoBuilder(PARENT parent, Date dataEvento, ResABAleitamentoMaternoEnum aleitamentoMaterno) {
		super(parent);
		this.value = aleitamentoMaterno;
		this.dataEvento = dataEvento;
	}

	@Override
	protected String openTags() {
		return "<Alimentação_da_criança_menor_de_2_anos><name><value>Alimentação da criança menor de 2 anos</value></name><language><terminology_id>"
				+ "<value>ISO_639-1</value></terminology_id><code_string>pt</code_string></language><encoding><terminology_id><value>IANA_character-sets</value>"
				+ "</terminology_id><code_string>UTF-8</code_string></encoding><subject></subject><data><Qualquer_evento_as_Point_Event><name><value>Qualquer evento</value>"
				+ "</name><time><oe:value>";
	}

	private String openTagAleitamentoMaterno() {
		return "</oe:value></time><data><Aleitamento_materno><name><value>Aleitamento materno</value></name><value><value>";
	}

	@Override
	protected String closeTags() {
		return "</value></value></Aleitamento_materno></data></Qualquer_evento_as_Point_Event></data>" + "</Alimentação_da_criança_menor_de_2_anos>";
	}

	@Override
	public String getValue() {
		if (this.value != null) {
			return RDateUtil.dateToISOEHR(this.dataEvento) + this.openTagAleitamentoMaterno() + this.value.getDescricao();
		}
		return null;
	}

}
