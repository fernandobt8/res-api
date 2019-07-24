package br.ufsc.bridge.res.sumariodealta.dto;

import java.io.Serializable;
import java.util.Date;

import br.ufsc.bridge.res.dab.exception.ResABXMLParserException;
import br.ufsc.bridge.res.sumariodealta.write.builder.SumarioDeAltaBuilder;
import br.ufsc.bridge.res.util.ResDocument;

public class ResSumarioDeAlta extends ResDocument implements Serializable {

	// XXX: verificar necessidade
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ResSumarioDeAlta(String xml) throws ResABXMLParserException {
		// XXX: implementar parser
		// XPathFactoryAssist xPathRoot = this.getXPathRoot(xml);
		// try {
		// } catch (XPathExpressionException e) {
		// throw new ResABXMLParserException("Erro no parser do XML para o DTO", e);
		// }
	}

	@Override
	public String getXml() {
		SumarioDeAltaBuilder sumarioDeAltaBuilder = new SumarioDeAltaBuilder();
		// XXX: implementar
		return sumarioDeAltaBuilder.getXmlContent();
	}

	@Override
	public Date getDataAtendimento() {
		// XXX: confirmar necessidade com documento
		return null;
	}

}
