package br.ufsc.bridge.res.service.dto.header;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

public class RegistryHeader extends RepositoryHeader {

	public RegistryHeader(Credential c) {
		super(c);
	}

	@Override
	protected void addAdditionalElements(SOAPMessage message) throws SOAPException {
		String envelopePrefix = message.getSOAPPart().getEnvelope().getPrefix();

		SOAPFactory factory = SOAPFactory.newInstance();
		String prefix = "a";
		String uri = "http://www.w3.org/2005/08/addressing";
		SOAPElement action = factory.createElement("Action", prefix, uri);
		action.addAttribute(QName.valueOf(envelopePrefix + ":mustUnderstand"), "1");
		action.addTextNode("urn:ihe:iti:2007:RegistryStoredQuery");

		SOAPElement messageID = factory.createElement("MessageID", prefix, uri);
		messageID.addTextNode("urn:uuid:a02ca8cd-86fa-4afc-a27c-616c183b2055");

		SOAPElement replyTo = factory.createElement("ReplyTo", prefix, uri);

		SOAPElement adress = factory.createElement("Address", prefix, uri);
		adress.addTextNode("http://www.w3.org/2005/08/addressing/anonymous");
		replyTo.addChildElement(adress);

		SOAPElement to = factory.createElement("To", prefix, uri);
		to.addAttribute(QName.valueOf(envelopePrefix + ":mustUnderstand"), "1");
		to.addTextNode("https://servicoshm.saude.gov.br/EHR-UNB/ProxyService/RegistryPS");

		SOAPHeader header = message.getSOAPHeader();
		header.addChildElement(action);
		header.addChildElement(messageID);
		header.addChildElement(replyTo);
		header.addChildElement(to);
	}
}