package br.ufsc.bridge.res.dto.header;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.PortInfo;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.output.ByteArrayOutputStream;

@Slf4j
public class RepositoryHeader implements SOAPHandler<SOAPMessageContext>, HandlerResolver {
	private Credential c;

	public RepositoryHeader(Credential c) {
		this.c = c;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (outboundProperty.booleanValue()) {
			try {
				SOAPEnvelope envelope = context.getMessage().getSOAPPart().getEnvelope();
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

				SOAPHeader header = envelope.getHeader();
				if (header == null) {
					header = envelope.addHeader();
				}
				header.addChildElement(securityElem);

				this.addAdditionalElements(header);

				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				context.getMessage().writeTo(outputStream);
				log.debug("Soap Envelope: " + outputStream.toString("UTF-8"));
			} catch (Exception e) {
				log.error("Erro ao montar header soap", e);
			}
		}
		return true;
	}

	protected void addAdditionalElements(SOAPHeader header) throws SOAPException {
	}

	@Override
	public Set<QName> getHeaders() {
		return new TreeSet<>();
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	@Override
	public void close(MessageContext context) {
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getHandlerChain(PortInfo portInfo) {
		return Arrays.asList(this);
	}
}