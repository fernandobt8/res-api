package br.ufsc.bridge.res.util;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.res.service.dto.RegistryErrorListXPath;
import br.ufsc.bridge.res.service.exception.ResServiceSevereException;
import br.ufsc.bridge.res.service.exception.ResXDSbException;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class ResLogError {

	private static final String CODIGO_ERRO_INTERNO_INSTABILIDADE_RES_NACIONAL = "BEA-380002";

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
