package br.ufsc.bridge.res.dab.write.builder.medicoesobservacoes.avaliacao;

import java.util.Date;

import lombok.Getter;

import br.ufsc.bridge.res.dab.write.builder.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;
import br.ufsc.bridge.res.util.RDateUtil;

@Getter
public class PerimetroCefalicoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String value;
	private Date dataEvento;

	public PerimetroCefalicoBuilder(PARENT parent, Date dataEvento, String perimetroCefalico) {
		super(parent);
		this.value = perimetroCefalico;
		this.dataEvento = dataEvento;
	}

	@Override
	protected String openTags() {
		return "<Perímetro_cefálico><name><value>Perímetro cefálico</value></name><language>"
				+ "<terminology_id><value>ISO_639-1</value></terminology_id><code_string>pt</code_string>"
				+ "</language><encoding><terminology_id><value>IANA_character-sets</value></terminology_id>"
				+ "<code_string>UTF-8</code_string></encoding><subject></subject><data><Qualquer_ponto_de_tempo_no_evento_prd_>"
				+ "<name><value>Qualquer ponto de tempo no evento.</value></name><time><oe:value>";
	}

	private String openTagPerimetroCefalico() {
		return "</oe:value></time><data><_exclm___-__Perímetro_cefálico><name><value>! - Perímetro cefálico</value></name><value><magnitude>";
	}

	@Override
	protected String closeTags() {
		return "</magnitude><precision>1</precision><units>cm</units></value></_exclm___-__Perímetro_cefálico></data><state></state></Qualquer_ponto_de_tempo_no_evento_prd_></data></Perímetro_cefálico>";
	}

	@Override
	public String getValue() {
		if (this.value != null) {
			return RDateUtil.dateToISOEHR(this.dataEvento) + this.openTagPerimetroCefalico() + this.value;
		}
		return null;
	}

}
