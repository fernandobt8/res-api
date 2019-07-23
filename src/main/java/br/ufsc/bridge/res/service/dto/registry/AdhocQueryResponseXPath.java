package br.ufsc.bridge.res.service.dto.registry;

import java.util.Date;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

import br.ufsc.bridge.res.dab.TipoDocumento;
import br.ufsc.bridge.res.util.RDateUtil;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class AdhocQueryResponseXPath {
	private static final String SUCCESS = "urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Success";

	private XPathFactoryAssist xPathRoot;

	public AdhocQueryResponseXPath(Document document) throws XPathExpressionException {
		this.xPathRoot = new XPathFactoryAssist(document).getXPathAssist("Envelope//AdhocQueryResponse");
	}

	public Boolean isSuccess() throws XPathExpressionException {
		return AdhocQueryResponseXPath.isSuccess(this.xPathRoot.getString("./@status"));
	}

	public static Boolean isSuccess(String value) throws XPathExpressionException {
		return SUCCESS.equals(value);
	}

	public Iterable<XPathFactoryAssist> getObjectRefs() {
		return this.xPathRoot.iterable(".//RegistryObjectList/ObjectRef");
	}

	// get header attributes
	public Iterable<XPathFactoryAssist> getHeaders() {
		return this.xPathRoot.iterable(".//RegistryObjectList/ExtrinsicObject");
	}

	public String getHeaderRepositoryUniqueId(XPathFactoryAssist element) throws XPathExpressionException {
		return element.getString("./Slot[@name='repositoryUniqueId']/ValueList/Value");
	}

	public Date getHeaderServiceStartTime(XPathFactoryAssist element) throws XPathExpressionException {
		return RDateUtil.isoXDSbToDate(element.getString("./Slot[@name='serviceStartTime']/ValueList/Value"));
	}

	public String getHeaderRepositoryURL(XPathFactoryAssist element) throws XPathExpressionException {
		return element.getString("./Slot[@name='URI']/ValueList/Value");
	}

	public String getHeaderUnidadeSaude(XPathFactoryAssist element) throws XPathExpressionException {
		return element.getString(".//Slot[@name='authorInstitution']/ValueList/Value");
	}

	public String getHeaderAuthor(XPathFactoryAssist element) throws XPathExpressionException {
		return element.getString(".//Slot[@name='authorPerson']/ValueList/Value");
	}

	public String getHeaderCbo(XPathFactoryAssist element) throws XPathExpressionException {
		return element.getString(".//Slot[@name='authorSpecialty']/ValueList/Value");
	}

	public String getDocumentUniqueId(XPathFactoryAssist element) throws XPathExpressionException {
		return element.getString("./ExternalIdentifier[@identificationScheme='urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab']/@value");
	}

	public TipoDocumento getDocumentType(XPathFactoryAssist element) {
		// XXX: implementar
		return null;
	}
}
