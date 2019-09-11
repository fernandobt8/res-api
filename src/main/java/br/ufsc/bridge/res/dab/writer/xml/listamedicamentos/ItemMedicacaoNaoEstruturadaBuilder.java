package br.ufsc.bridge.res.dab.writer.xml.listamedicamentos;

import java.util.List;

import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class ItemMedicacaoNaoEstruturadaBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	private List<String> medicamentosNaoEstruturadas;

	public ItemMedicacaoNaoEstruturadaBuilder(PARENT parent, List<String> medicamentosNaoEstruturadas) {
		super(parent);
		this.medicamentosNaoEstruturadas = medicamentosNaoEstruturadas;
	}

	@Override
	protected String openTags() {
		return "<Medicamentos_prescritos_no_atendimento__openBrkt_não_estruturado_closeBrkt_><name><value>Medicamentos prescritos no atendimento (não estruturado)</value>"
				+ "</name><Descrição_da_prescrição><name><value>Descrição da prescrição</value></name><value><oe:value>";
	}

	@Override
	protected String closeTags() {
		return "</oe:value></value></Descrição_da_prescrição></Medicamentos_prescritos_no_atendimento__openBrkt_não_estruturado_closeBrkt_>";
	}

	@Override
	public String getValue() {
		StringBuilder value = new StringBuilder();
		for (String medicamentoNaoEstruturado : this.medicamentosNaoEstruturadas) {
			value.append(medicamentoNaoEstruturado);
			value.append("; ");
		}
		return value.toString();
	}

}
