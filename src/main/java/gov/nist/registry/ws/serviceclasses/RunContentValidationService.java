
package gov.nist.registry.ws.serviceclasses;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import gov.nist.registry.common2.registry.xsd.Metadata;
import gov.nist.registry.common2.registry.xsd.Response;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="m" type="{http://registry.common2.registry.nist.gov/xsd}Metadata" minOccurs="0"/>
 *         &lt;element name="response" type="{http://registry.common2.registry.nist.gov/xsd}Response" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "m",
    "response"
})
@XmlRootElement(name = "runContentValidationService")
public class RunContentValidationService {

    @XmlElementRef(name = "m", namespace = "http://serviceclasses.ws.registry.nist.gov", type = JAXBElement.class, required = false)
    protected JAXBElement<Metadata> m;
    @XmlElementRef(name = "response", namespace = "http://serviceclasses.ws.registry.nist.gov", type = JAXBElement.class, required = false)
    protected JAXBElement<Response> response;

    /**
     * Gets the value of the m property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Metadata }{@code >}
     *     
     */
    public JAXBElement<Metadata> getM() {
        return m;
    }

    /**
     * Sets the value of the m property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Metadata }{@code >}
     *     
     */
    public void setM(JAXBElement<Metadata> value) {
        this.m = value;
    }

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Response }{@code >}
     *     
     */
    public JAXBElement<Response> getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Response }{@code >}
     *     
     */
    public void setResponse(JAXBElement<Response> value) {
        this.response = value;
    }

}
