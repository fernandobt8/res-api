package br.ufsc.bridge.res.dab.write.builder.medicoesobservacoes.avaliacao;

import java.util.Date;

import lombok.Getter;

import br.ufsc.bridge.res.dab.write.builder.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;
import br.ufsc.bridge.res.util.RDateUtil;

@Getter
public class PesoCorporalBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String value;
	private Date dataEvento;

	public PesoCorporalBuilder(PARENT parent, Date dataEvento, String peso) {
		super(parent);
		this.value = peso;
		this.dataEvento = dataEvento;
	}

	@Override
	protected String openTags() {
		return "<Peso_corporal><name><value>Peso corporal</value></name><language><terminology_id><value>ISO_639-1</value></terminology_id><code_string>pt</code_string></language><encoding><terminology_id><value>IANA_character-sets</value></terminology_id><code_string>UTF-8</code_string></encoding><subject xsi:type=\"oe:PARTY_SELF\"/><data><Qualquer_evento_as_Point_Event><name><value>Qualquer evento</value></name><time><oe:value>";
	}

	private String openTagPeso() {
		return "</oe:value></time><data><Peso><name><value>Peso</value></name><value><magnitude>";
	}

	@Override
	protected String closeTags() {
		return "</magnitude><units>kg</units></value></Peso></data><state/></Qualquer_evento_as_Point_Event></data></Peso_corporal>";
	}

	@Override
	public String getValue() {
		if (this.value != null) {
			return RDateUtil.dateToISOEHR(this.dataEvento) + this.openTagPeso() + this.value;
		}
		return null;
	}

}
