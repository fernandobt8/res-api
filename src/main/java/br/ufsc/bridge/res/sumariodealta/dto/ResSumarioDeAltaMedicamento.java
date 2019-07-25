package br.ufsc.bridge.res.sumariodealta.dto;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class ResSumarioDeAltaMedicamento {

	// XXX: tirei alguns campos se comparado com AB
	// XXX: questão dose estruturada ser 0..*, não chequei se no AB é assim tbm, mas lá não foi considerado
	private String nomeMedicamento;
	private String descricaoViaAdministracao;
	private String descricaoDose;
	private String duracaoTratamento;

	public ResSumarioDeAltaMedicamento(XPathFactoryAssist xPathMedicamentoEstruturado) throws XPathExpressionException {
		String startXPath = "./data/Lista_de_medicamentos_da_alta__openBrkt_estruturada_closeBrkt_";
		this.nomeMedicamento = xPathMedicamentoEstruturado.getString(startXPath + "/Medicamento/value/value");
		this.descricaoViaAdministracao = xPathMedicamentoEstruturado.getString(startXPath + "/Via_de_administração/value/value");
		this.descricaoDose = xPathMedicamentoEstruturado.getString(startXPath + "/Dose_estruturada/Dose/value/value");
		this.duracaoTratamento = xPathMedicamentoEstruturado.getString(startXPath + "/Dose_estruturada/Duração_de_uso_do_medicamento/value/value");
	}

}
