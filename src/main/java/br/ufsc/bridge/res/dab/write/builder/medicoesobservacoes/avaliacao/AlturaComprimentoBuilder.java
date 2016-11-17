package br.ufsc.bridge.res.dab.write.builder.medicoesobservacoes.avaliacao;

import java.util.Date;

import lombok.Getter;

import br.ufsc.bridge.res.dab.write.builder.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;
import br.ufsc.bridge.res.util.RDateUtil;

@Getter
public class AlturaComprimentoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String value;
	private Date dataEvento;

	public AlturaComprimentoBuilder(PARENT parent, Date dataEvento, String altura) {
		super(parent);
		this.value = altura;
		this.dataEvento = dataEvento;
	}

	@Override
	protected String openTags() {
		return "<Altura__fslash__comprimento><name><value>Altura / comprimento</value></name><language>"
				+ "<terminology_id><value>ISO_639-1</value></terminology_id><code_string>pt</code_string>"
				+ "</language><encoding><terminology_id><value>IANA_character-sets</value></terminology_id>"
				+ "<code_string>UTF-8</code_string></encoding><subject></subject><data><Qualquer_evento_as_Point_Event>"
				+ "<name><value>Qualquer evento</value></name><time><oe:value>";
	}

	private String openTagAltura() {
		return "</oe:value></time><data><Altura__fslash__comprimento><name>"
				+ "<value>Altura / comprimento</value></name><value><magnitude>";
	}

	@Override
	protected String closeTags() {
		return "</magnitude><units>cm</units></value></Altura__fslash__comprimento></data><state/>"
				+ "</Qualquer_evento_as_Point_Event></data></Altura__fslash__comprimento>";
	}

	@Override
	public String getXmlContent() {
		return this.openTags() + RDateUtil.dateToISOEHR(this.dataEvento) + this.openTagAltura() + this.value + this.closeTags();
	}

}
