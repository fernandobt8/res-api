
package ihe.iti.xds_b._2007;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import oasis.names.tc.ebxml_regrep.xsd.lcm._3.SubmitObjectsRequest;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProvideAndRegisterDocumentSetRequestType", propOrder = {
		"submitObjectsRequest",
		"document"
})
@XmlRootElement(name = "ProvideAndRegisterDocumentSetRequest")
public class ProvideAndRegisterDocumentSetRequestType {

	@XmlElement(name = "SubmitObjectsRequest", namespace = "urn:oasis:names:tc:ebxml-regrep:xsd:lcm:3.0", required = true)
	protected SubmitObjectsRequest submitObjectsRequest;

	@XmlElement(name = "Document")
	protected List<ProvideAndRegisterDocumentSetRequestType.Document> document;

	public List<ProvideAndRegisterDocumentSetRequestType.Document> getDocument() {
		if (this.document == null) {
			this.document = new ArrayList<>();
		}
		return this.document;
	}

	@Getter
	@Setter
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = {
			"value"
	})
	public static class Document {

		@XmlElement(name = "Include", namespace = "http://www.w3.org/2004/08/xop/include")
		protected Include include;

		@XmlAttribute(name = "id", required = true)
		@XmlSchemaType(name = "anyURI")
		protected String id;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Include {
		@XmlAttribute
		private String href;
	}
}
