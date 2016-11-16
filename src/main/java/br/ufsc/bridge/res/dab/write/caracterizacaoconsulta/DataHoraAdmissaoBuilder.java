package br.ufsc.bridge.res.dab.write.caracterizacaoconsulta;

import java.util.Date;

import br.ufsc.bridge.res.dab.write.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.base.ParentArquetypeWrapper;
import br.ufsc.bridge.res.util.RDateUtil;

public class DataHoraAdmissaoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private Date value;

	public DataHoraAdmissaoBuilder(PARENT parent, Date date) {
		super(parent);
		this.value = date;
	}

	@Override
	protected String openTags() {
		return "<Data_fslash_hora_da_admissão><name><value>Data/hora da admissão</value>" +
				"</name><value><oe:value>";
	}

	@Override
	public String getValue() {
		return RDateUtil.dateToISOEHR(this.value);
	}

	@Override
	protected String closeTags() {
		return "</oe:value></value></Data_fslash_hora_da_admissão>";
	}
}
