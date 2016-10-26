
package gov.nist.registry.ws.serviceclasses;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import gov.nist.registry.common2.registry.xsd.Metadata;
import gov.nist.registry.common2.registry.xsd.Response;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gov.nist.registry.ws.serviceclasses package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ExceptionException_QNAME = new QName("http://serviceclasses.ws.registry.nist.gov", "Exception");
    private final static QName _RunContentValidationServiceM_QNAME = new QName("http://serviceclasses.ws.registry.nist.gov", "m");
    private final static QName _RunContentValidationServiceResponse_QNAME = new QName("http://serviceclasses.ws.registry.nist.gov", "response");
    private final static QName _GetServiceNameResponseReturn_QNAME = new QName("http://serviceclasses.ws.registry.nist.gov", "return");
    private final static QName _MetadataExceptionMetadataException_QNAME = new QName("http://serviceclasses.ws.registry.nist.gov", "MetadataException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gov.nist.registry.ws.serviceclasses
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RunContentValidationService }
     * 
     */
    public RunContentValidationService createRunContentValidationService() {
        return new RunContentValidationService();
    }

    /**
     * Create an instance of {@link GetServiceNameResponse }
     * 
     */
    public GetServiceNameResponse createGetServiceNameResponse() {
        return new GetServiceNameResponse();
    }

    /**
     * Create an instance of {@link RunContentValidationServiceResponse }
     * 
     */
    public RunContentValidationServiceResponse createRunContentValidationServiceResponse() {
        return new RunContentValidationServiceResponse();
    }

    /**
     * Create an instance of {@link gov.nist.registry.ws.serviceclasses.MetadataException }
     * 
     */
    public gov.nist.registry.ws.serviceclasses.MetadataException createMetadataException() {
        return new gov.nist.registry.ws.serviceclasses.MetadataException();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviceclasses.ws.registry.nist.gov", name = "Exception", scope = Exception.class)
    public JAXBElement<Object> createExceptionException(Object value) {
        return new JAXBElement<Object>(_ExceptionException_QNAME, Object.class, Exception.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Metadata }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviceclasses.ws.registry.nist.gov", name = "m", scope = RunContentValidationService.class)
    public JAXBElement<Metadata> createRunContentValidationServiceM(Metadata value) {
        return new JAXBElement<Metadata>(_RunContentValidationServiceM_QNAME, Metadata.class, RunContentValidationService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviceclasses.ws.registry.nist.gov", name = "response", scope = RunContentValidationService.class)
    public JAXBElement<Response> createRunContentValidationServiceResponse(Response value) {
        return new JAXBElement<Response>(_RunContentValidationServiceResponse_QNAME, Response.class, RunContentValidationService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviceclasses.ws.registry.nist.gov", name = "return", scope = GetServiceNameResponse.class)
    public JAXBElement<String> createGetServiceNameResponseReturn(String value) {
        return new JAXBElement<String>(_GetServiceNameResponseReturn_QNAME, String.class, GetServiceNameResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link gov.nist.registry.common2.exception.xsd.MetadataException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviceclasses.ws.registry.nist.gov", name = "MetadataException", scope = gov.nist.registry.ws.serviceclasses.MetadataException.class)
    public JAXBElement<gov.nist.registry.common2.exception.xsd.MetadataException> createMetadataExceptionMetadataException(gov.nist.registry.common2.exception.xsd.MetadataException value) {
        return new JAXBElement<gov.nist.registry.common2.exception.xsd.MetadataException>(_MetadataExceptionMetadataException_QNAME, gov.nist.registry.common2.exception.xsd.MetadataException.class, gov.nist.registry.ws.serviceclasses.MetadataException.class, value);
    }

}
