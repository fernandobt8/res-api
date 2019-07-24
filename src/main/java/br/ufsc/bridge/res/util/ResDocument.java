package br.ufsc.bridge.res.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import br.ufsc.bridge.res.dab.exception.ResABXMLParserException;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public abstract class ResDocument {

	protected XPathFactoryAssist getXPathRoot(String xml) throws ResABXMLParserException {
		Document document;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
		} catch (SAXException | IOException | ParserConfigurationException e) {
			throw new ResABXMLParserException("Erro no parser do XML para \"Document\"", e);
		}
		return new XPathFactoryAssist(document);
	}

	public abstract String getXml();

	public abstract Date getDataAtendimento();

}
