package br.ufsc.bridge.res.sumarioalta.dto;

import java.io.Serializable;

import javax.xml.xpath.XPathExpressionException;

import lombok.Getter;

import br.ufsc.bridge.res.dab.domain.ResABTipoProblemaDiagnostico;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

@Getter
public class ResSumarioAltaProblemaDiagnostico implements Serializable {

	private static final long serialVersionUID = 1L;
	private String descricao;
	private String codigo;
	private ResABTipoProblemaDiagnostico tipo;

	public ResSumarioAltaProblemaDiagnostico(XPathFactoryAssist xPathProblemaDiagnostico) throws XPathExpressionException {
		this.descricao = xPathProblemaDiagnostico.getString("./data/Diagnóstico/value/value");
		this.codigo = xPathProblemaDiagnostico.getString("./data/Diagnóstico/value/defining_code/code_string");
		this.tipo = ResABTipoProblemaDiagnostico.getByTipo(xPathProblemaDiagnostico.getString("./data/Diagnóstico/value/defining_code/terminology_id/value"));
	}
}
