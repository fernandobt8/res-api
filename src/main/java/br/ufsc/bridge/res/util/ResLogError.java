package br.ufsc.bridge.res.util;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang3.StringUtils;

import br.ufsc.bridge.res.service.soap.exception.ResConsentPolicyException;
import br.ufsc.bridge.res.service.soap.exception.ResServerErrorException;
import br.ufsc.bridge.res.service.soap.exception.ResServiceFatalException;
import br.ufsc.bridge.res.service.soap.exception.ResXDSbException;
import br.ufsc.bridge.res.service.soap.registry.RegistryErrorListXPath;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class ResLogError {

	private static final String CODIGO_ERRO_INTERNO_INSTABILIDADE_RES_NACIONAL = "BEA-380002";

	public void parserException(RegistryErrorListXPath errorXPath) throws ResServerErrorException, ResServiceFatalException, ResXDSbException, ResConsentPolicyException {
		StringBuilder value = new StringBuilder();
		try {
			for (XPathFactoryAssist element : errorXPath.getErrors()) {
				value.append("------- Registry Error -------\n");
				value.append("codeContext: " + errorXPath.getErrorCodeContext(element) + "\n");
				value.append("errorCode: " + errorXPath.getErrorErrorCode(element) + "\n");
				value.append("location: " + errorXPath.getErrorLocation(element) + "\n");
				value.append("severity: " + errorXPath.getErrorServerity(element) + "\n");
				value.append("value: " + errorXPath.getErrorValue(element) + "\n");

				if (StringUtils.containsIgnoreCase(errorXPath.getErrorCodeContext(element),
						CODIGO_ERRO_INTERNO_INSTABILIDADE_RES_NACIONAL)) {
					throw new ResServerErrorException(value.toString());
				} else if (StringUtils.containsIgnoreCase(errorXPath.getErrorCodeContext(element), "consent policy") ||
						StringUtils.containsIgnoreCase(errorXPath.getErrorCodeContext(element), "consent denied")) {
					throw new ResConsentPolicyException(value.toString());
				}
			}

		} catch (XPathExpressionException e) {
			throw new ResXDSbException("Error parsing \"RegistryErrorList\"");
		}
		throw new ResServiceFatalException(value.toString());
	}
}
