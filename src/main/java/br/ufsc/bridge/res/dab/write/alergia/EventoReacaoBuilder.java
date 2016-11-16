package br.ufsc.bridge.res.dab.write.alergia;

import java.util.Date;

import br.ufsc.bridge.res.dab.write.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.base.ParentArquetypeWrapper;
import br.ufsc.bridge.res.util.RDateUtil;

public class EventoReacaoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String manifestacao;
	private Date dataInstalacao;
	private String evolucaoAlergia;

	public EventoReacaoBuilder(PARENT parent) {
		super(parent);
	}

	public EventoReacaoBuilder<PARENT> manifestacao(String manifestacao) {
		this.manifestacao = manifestacao;
		return this;
	}

	public EventoReacaoBuilder<PARENT> evolucaoAlergia(String evolucaoAlergia) {
		this.evolucaoAlergia = evolucaoAlergia;
		return this;
	}

	public EventoReacaoBuilder<PARENT> dataInstalacao(Date dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
		return this;
	}

	@Override
	protected String openTags() {
		return "<Evento_da_reação><name><value>Evento da reação</value></name>";
	}

	@Override
	protected String closeTags() {
		return "</Evento_da_reação>";
	}

	private String openTagsManifestacao() {
		return "<Manifestação><name><value>Manifestação</value></name><value><oe:value>";
	}

	private String closeTagsManifestacao() {
		return "</oe:value></value></Manifestação>";
	}

	private String openTagsEvolucaoAlergia() {
		return "<Evolução_da_alergia__fslash__reação_adversa><name><value>Evolução da alergia / reação adversa</value></name><value><oe:value>";
	}

	private String closeTagsEvolucaoAlergia() {
		return "</oe:value></value></Evolução_da_alergia__fslash__reação_adversa>";
	}

	private String openTagsDataInstalacao() {
		return "<Data_da_instalação_da_alergia__fslash__reação_adversa><name><value>"
				+ "Data da instalação da alergia / reação adversa</value></name><value><oe:value>";
	}

	private String closeTagsDataInstalacao() {
		return "</oe:value></value></Data_da_instalação_da_alergia__fslash__reação_adversa>";
	}

	@Override
	public String getValue() {
		String value = "";
		if (this.manifestacao != null) {
			value += this.openTagsManifestacao() + this.manifestacao + this.closeTagsManifestacao();
		}
		if (this.evolucaoAlergia != null) {
			value += this.openTagsEvolucaoAlergia() + this.evolucaoAlergia + this.closeTagsEvolucaoAlergia();
		}
		if (this.dataInstalacao != null) {
			value += this.openTagsDataInstalacao() + RDateUtil.dateToISOEHR(this.dataInstalacao) + this.closeTagsDataInstalacao();
		}
		return value;
	}

}
