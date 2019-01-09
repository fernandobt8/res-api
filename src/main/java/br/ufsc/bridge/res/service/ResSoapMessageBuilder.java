package br.ufsc.bridge.res.service;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import lombok.Getter;

import br.ufsc.bridge.soap.http.SoapCredential;
import br.ufsc.bridge.soap.http.exception.SoapCreateMessageException;
import br.ufsc.bridge.soap.jaxb.JAXBMessageBuilder;

@Getter
public class ResSoapMessageBuilder extends JAXBMessageBuilder<Object> {
	private String url;
	private String action;

	public ResSoapMessageBuilder(SoapCredential c, String url, String action) {
		super(c);
		this.url = url;
		this.action = action;
	}

	@Override
	public SOAPMessage soapMessage(Object data) throws SoapCreateMessageException {
		SOAPMessage message = super.soapMessage(data);
		try {
			String envelopePrefix = message.getSOAPPart().getEnvelope().getPrefix();

			SOAPFactory factory = SOAPFactory.newInstance();
			String prefix = "a";
			String uri = "http://www.w3.org/2005/08/addressing";
			SOAPElement elementAction = factory.createElement("Action", prefix, uri);
			elementAction.addAttribute(QName.valueOf(envelopePrefix + ":mustUnderstand"), "1");
			elementAction.addTextNode(this.action);

			SOAPElement messageID = factory.createElement("MessageID", prefix, uri);
			messageID.addTextNode("urn:uuid:a02ca8cd-86fa-4afc-a27c-616c183b2055");
			messageID.addAttribute(QName.valueOf(envelopePrefix + ":mustUnderstand"), "1");

			SOAPElement replyTo = factory.createElement("ReplyTo", prefix, uri);
			replyTo.addAttribute(QName.valueOf(envelopePrefix + ":mustUnderstand"), "1");

			SOAPElement adress = factory.createElement("Address", prefix, uri);
			adress.addTextNode("http://www.w3.org/2005/08/addressing/anonymous");
			replyTo.addChildElement(adress);

			SOAPElement to = factory.createElement("To", prefix, uri);
			to.addAttribute(QName.valueOf(envelopePrefix + ":mustUnderstand"), "1");
			to.addTextNode(this.url);

			SOAPHeader header = message.getSOAPHeader();
			header.addChildElement(elementAction);
			header.addChildElement(messageID);
			header.addChildElement(replyTo);
			header.addChildElement(to);
		} catch (Exception e) {
			throw new SoapCreateMessageException("Erro ao criar envelope soap", e);
		}
		return message;
	}
}
