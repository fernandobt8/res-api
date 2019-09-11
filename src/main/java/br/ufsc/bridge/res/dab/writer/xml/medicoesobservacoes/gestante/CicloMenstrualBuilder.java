package br.ufsc.bridge.res.dab.writer.xml.medicoesobservacoes.gestante;

import java.util.Date;

import br.ufsc.bridge.res.dab.writer.xml.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;
import br.ufsc.bridge.res.util.RDateUtil;

public class CicloMenstrualBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private Date value;
	private Date dataEvento;

	public CicloMenstrualBuilder(PARENT parent, Date dataEvento, Date value) {
		super(parent);
		this.value = value;
		this.dataEvento = dataEvento;
	}

	@Override
	protected String openTags() {
		return "<Ciclo_menstrual><name><value>Ciclo menstrual</value></name><language><terminology_id>"
				+ "<value>ISO_639-1</value></terminology_id><code_string>pt</code_string></language>"
				+ "<encoding><terminology_id><value>IANA_character-sets</value></terminology_id>"
				+ "<code_string>UTF-8</code_string></encoding><subject></subject><data>"
				+ "<Qualquer_evento_as_Point_Event><name><value>Qualquer evento</value></name><time><oe:value>";
	}

	public String openTagsDum() {
		return "</oe:value></time><data><DUM__openBrkt_Data_da_última_menstruação_closeBrkt_>"
				+ "<name><value>DUM (Data da última menstruação)</value></name><value><oe:value>";
	}

	@Override
	public String getValue() {
		if (this.value != null) {
			return RDateUtil.dateToISOEHR(this.dataEvento) + this.openTagsDum() + RDateUtil.dateToISOEHR(this.value);
		}
		return null;
	}

	@Override
	protected String closeTags() {
		return "</oe:value></value></DUM__openBrkt_Data_da_última_menstruação_closeBrkt_>"
				+ "</data><state/></Qualquer_evento_as_Point_Event></data></Ciclo_menstrual>";
	}

}
