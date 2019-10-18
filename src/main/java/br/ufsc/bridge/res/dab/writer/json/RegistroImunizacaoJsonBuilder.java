package br.ufsc.bridge.res.dab.writer.json;

import org.apache.commons.lang3.StringUtils;

import br.ufsc.bridge.res.registroimunobiologico.dto.Estrategia;
import br.ufsc.bridge.res.registroimunobiologico.dto.ViaAdministracao;
import br.ufsc.bridge.res.util.json.BaseJsonBuilder;

public class RegistroImunizacaoJsonBuilder<T extends BaseJsonBuilder<?>> extends BaseJsonBuilder<T> {

	public RegistroImunizacaoJsonBuilder(T parent) {
		super(parent, "registro-imunizacao");
	}

	//	public SituacaoCondicaoJsonBuilder<RegistroImunizacaoJsonBuilder<T>> situacaoCondicao() {
	//		return new SituacaoCondicaoJsonBuilder<>(this);
	//	}

	//	public RegistroImunizacaoJsonBuilder<T> situacao(List<SituacaoCondicao> value) {
	//		if (CollectionUtils.isEmpty(value)) {
	//			this.document.delete("$.items[?(@.name.value == 'Sumário de Imunização')]"
	//					+ ".data.items[?(@.name.value == 'Situação/condição')]");
	//		} else {
	//			for (SituacaoCondicao situacao : value) {
	//				this.document.add("$.items[?(@.name.value == 'Sumário de Imunização')]"
	//						+ ".data.items[?(@.name.value == 'Situação/condição')]"
	//						+ ".value.value", situacao.getValue());
	//			}
	//		}
	//		return this;
	//	}

	public RegistroImunizacaoJsonBuilder<T> imunobiologico(String value) {
		this.document.set("$.items[?(@.name.value == 'Gestão de medicação')]"
				+ ".description.items[?(@.name.value == 'Medicamento')]"
				+ ".items[?(@.name.value == 'Imunobiológico')]"
				+ ".value.value", value);
		return this;
	}

	public RegistroImunizacaoJsonBuilder<T> estrategia(Estrategia value) {
		this.document.set("$.items[?(@.name.value == 'Sumário de Imunização')]"
				+ ".data.items[?(@.name.value == 'Estratégia')]"
				+ ".value.value", value.getValue());
		return this;
	}

	public RegistroImunizacaoJsonBuilder<T> dose(String value) {
		if (StringUtils.isNotEmpty(value)) {
			this.document.set("$.items[?(@.name.value == 'Gestão de medicação')]"
					+ ".description.items[?(@.name.value == 'Medicamento')]"
					+ ".items[?(@.name.value == 'Dose')]"
					+ ".items.value.value", value);
		} else {
			this.document.delete("$.items[?(@.name.value == 'Gestão de medicação')]"
					+ ".description.items[?(@.name.value == 'Medicamento')]"
					+ ".items[?(@.name.value == 'Dose')]");
		}
		return this;
	}

	public RegistroImunizacaoJsonBuilder<T> viaAdministracao(ViaAdministracao value) {
		if (value != null) {
			this.document.set("$.items[?(@.name.value == 'Gestão de medicação')]"
					+ ".description.items[?(@.name.value == 'Detalhes da administração')]"
					+ ".items[?(@.name.value == 'Via de administração')]"
					+ ".value.value", value.getValue());
		} else {
			this.document.delete("$.items[?(@.name.value == 'Gestão de medicação')]"
					+ ".description.items[?(@.name.value == 'Detalhes da administração')]"
					+ ".items[?(@.name.value == 'Via de administração')]");
		}
		return this;
	}

	public RegistroImunizacaoJsonBuilder<T> localAplicacao(String value) {
		if (StringUtils.isNotEmpty(value)) {
			this.document.set("$.items[?(@.name.value == 'Gestão de medicação')]"
					+ ".description.items[?(@.name.value == 'Detalhes da administração')]"
					+ ".items[?(@.name.value == 'Local de aplicação')]"
					+ ".value.value", value);
		} else {
			this.document.delete("$.items[?(@.name.value == 'Gestão de medicação')]"
					+ ".description.items[?(@.name.value == 'Detalhes da administração')]"
					+ ".items[?(@.name.value == 'Local de aplicação')]");
		}
		return this;
	}

	public RegistroImunizacaoJsonBuilder<T> lote(String value) {
		if (StringUtils.isNotEmpty(value)) {
			this.document.set("$.items[?(@.name.value == 'Gestão de medicação')]"
					+ ".description.items[?(@.name.value == 'Medicamento')]"
					+ ".items[?(@.name.value == 'Lote')]"
					+ ".value.value", value);
		} else {
			this.document.delete("$.items[?(@.name.value == 'Gestão de medicação')]"
					+ ".description.items[?(@.name.value == 'Medicamento')]"
					+ ".items[?(@.name.value == 'Lote')]");
		}
		return this;
	}

	public RegistroImunizacaoJsonBuilder<T> fabricante(String value) {
		if (StringUtils.isNotEmpty(value)) {
			this.document.set("$.items[?(@.name.value == 'Gestão de medicação')]"
					+ ".description.items[?(@.name.value == 'Medicamento')]"
					+ ".items[?(@.name.value == 'Fabricante')]"
					+ ".value.value", value);
		} else {
			this.document.delete("$.items[?(@.name.value == 'Gestão de medicação')]"
					+ ".description.items[?(@.name.value == 'Medicamento')]"
					+ ".items[?(@.name.value == 'Fabricante')]");
		}
		return this;
	}

}
