
package gov.nist.registry.common2.registry.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorsAndWarnings" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="response" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="root" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", propOrder = {
    "errorsAndWarnings",
    "response",
    "root"
})
public class Response {

    @XmlElementRef(name = "errorsAndWarnings", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> errorsAndWarnings;
    @XmlElementRef(name = "response", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> response;
    @XmlElementRef(name = "root", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> root;

    /**
     * Gets the value of the errorsAndWarnings property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getErrorsAndWarnings() {
        return errorsAndWarnings;
    }

    /**
     * Sets the value of the errorsAndWarnings property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setErrorsAndWarnings(JAXBElement<String> value) {
        this.errorsAndWarnings = value;
    }

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setResponse(JAXBElement<Object> value) {
        this.response = value;
    }

    /**
     * Gets the value of the root property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getRoot() {
        return root;
    }

    /**
     * Sets the value of the root property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setRoot(JAXBElement<Object> value) {
        this.root = value;
    }

}
