package br.ufsc.bridge.res.http;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public interface CreateSOAPMessage {

	SOAPMessage create(Object soapBodyJaxbObject, String action, String url) throws SOAPException, IOException, JAXBException, ParserConfigurationException;
}
