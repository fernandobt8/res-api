package br.ufsc.bridge.res.service.dto.header;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import lombok.extern.slf4j.Slf4j;

import org.w3c.dom.Document;

import br.ufsc.bridge.res.http.CreateSOAPMessage;

@Slf4j
public class RepositoryHeader implements CreateSOAPMessage {
	private Credential c;

	public RepositoryHeader(Credential c) {
		this.c = c;
	}

	@Override
	public SOAPMessage create(Object data, String action, String url) throws SOAPException, JAXBException, ParserConfigurationException {
		SOAPMessage message = null;
		message = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL).createMessage();

		if (this.c != null) {
			SOAPHeader header = message.getSOAPHeader();
			if (header == null) {
				header = message.getSOAPPart().getEnvelope().addHeader();
			}
			SOAPFactory factory = SOAPFactory.newInstance();
			String prefix = "wsse";
			String uri = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
			SOAPElement securityElem = factory.createElement("Security", prefix, uri);

			SOAPElement tokenElem = factory.createElement("UsernameToken", prefix, uri);
			tokenElem.addAttribute(QName.valueOf("wsu:Id"), "UsernameToken-2");
			tokenElem.addAttribute(QName.valueOf("xmlns:wsu"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

			SOAPElement aElem = factory.createElement("Username", prefix, uri);
			aElem.addTextNode(this.c.getUsername());

			SOAPElement bElem = factory.createElement("Password", prefix, uri);
			bElem.addTextNode(this.c.getPassword());
			bElem.addAttribute(QName.valueOf("Type"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");

			tokenElem.addChildElement(aElem);
			tokenElem.addChildElement(bElem);
			securityElem.addChildElement(tokenElem);

			header.addChildElement(securityElem);
		}

		SOAPBody body = message.getSOAPBody();
		body.addDocument(this.jaxbObjectToDocument(data));

		this.addAdditionalElements(message, action, url);

		if (log.isDebugEnabled()) {
			try {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				message.writeTo(outputStream);
				log.info("Soap Envelope: " + outputStream.toString("UTF-8"));
			} catch (IOException e) {
				log.debug("Error debug writeTo Soap Envelope");
			}
		}
		return message;
	}

	private Document jaxbObjectToDocument(Object data) throws JAXBException, ParserConfigurationException {
		JAXBContext jc = JAXBContext.newInstance(data.getClass());

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

		Marshaller marshaller = jc.createMarshaller();
		marshaller.marshal(data, document);
		return document;
	}

	protected void addAdditionalElements(SOAPMessage message, String action, String url) throws SOAPException {
	}
}