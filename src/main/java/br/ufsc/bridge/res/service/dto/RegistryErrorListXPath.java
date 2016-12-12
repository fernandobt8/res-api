package br.ufsc.bridge.res.service.dto;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

import br.ufsc.bridge.res.util.XPathFactoryAssist;

public class RegistryErrorListXPath {

	private XPathFactoryAssist xPAth;

	public RegistryErrorListXPath(Document document) {
		this.xPAth = new XPathFactoryAssist(document);
	}

	public Iterable<XPathFactoryAssist> getErrors() {
		return this.xPAth.iterable("//RegistryErrorList/RegistryError");
	}

	public String getErrorCodeContext(XPathFactoryAssist element) throws XPathExpressionException {
		return element.getString("./@codeContext");
	}

	public String getErrorErrorCode(XPathFactoryAssist element) throws XPathExpressionException {
		return element.getString("./@errorCode");
	}

	public String getErrorLocation(XPathFactoryAssist element) throws XPathExpressionException {
		return element.getString("./@location");
	}

	public String getErrorServerity(XPathFactoryAssist element) throws XPathExpressionException {
		return element.getString("./@severity");
	}

	public String getErrorValue(XPathFactoryAssist element) throws XPathExpressionException {
		return element.getString("./@value");
	}
}
