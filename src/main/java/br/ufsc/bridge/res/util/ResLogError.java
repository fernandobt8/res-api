package br.ufsc.bridge.res.util;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.res.service.dto.RegistryErrorListXPath;
import br.ufsc.bridge.res.service.exception.ResServiceSevereException;
import br.ufsc.bridge.res.service.exception.ResXDSbException;

public class ResLogError {

	private static final String CODIGO_ERRO_INTERNO_INSTABILIDADE_RES_NACIONAL = "BEA-380002";

	private static final String MSG_ERRO_INTERNO_INESPERADO_RES_NACIONAL = "Ocorreu um erro inesperado ao pesquisar informações no RES-nacional.";
	private static final String MSG_ERRO_INTERNO_INESPERADO_RES_NACIONAL_E = "Ocorreu um erro inesperado ao enviar este registro para o RES-nacional.";

	public static final String SERVICO_RES_INDISPONIVEL = "Serviço RES-nacional não disponínel. Tente novamente.";

	public void parserException(RegistryErrorListXPath errorXPath) throws ResXDSbException, ResServiceSevereException {
		boolean fatalError = true;
		String value = "";
		try {
			for (XPathFactoryAssist element : errorXPath.getErrors()) {
				if (errorXPath.getErrorCodeContext(element).contains(CODIGO_ERRO_INTERNO_INSTABILIDADE_RES_NACIONAL)) {
					fatalError = false;
				}
				value += "------- Registry Error -------\n";
				value += "codeContext: " + errorXPath.getErrorCodeContext(element) + "\n";
				value += "errorCode: " + errorXPath.getErrorErrorCode(element) + "\n";
				value += "location: " + errorXPath.getErrorLocation(element) + "\n";
				value += "severity: " + errorXPath.getErrorServerity(element) + "\n";
				value += "value: " + errorXPath.getErrorValue(element) + "\n";
			}

		} catch (XPathExpressionException e) {
			throw new ResXDSbException("Error parsing \"RegistryErrorList\"");
		}

		if (fatalError) {
			throw new ResXDSbException(value);
		} else {
			throw new ResServiceSevereException(value);
		}
	}
}
