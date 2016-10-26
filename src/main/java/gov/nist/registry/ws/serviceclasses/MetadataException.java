
package gov.nist.registry.ws.serviceclasses;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="MetadataException" type="{http://exception.common2.registry.nist.gov/xsd}MetadataException" minOccurs="0"/>
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
    "metadataException"
})
@XmlRootElement(name = "MetadataException")
public class MetadataException {

    @XmlElementRef(name = "MetadataException", namespace = "http://serviceclasses.ws.registry.nist.gov", type = JAXBElement.class, required = false)
    protected JAXBElement<gov.nist.registry.common2.exception.xsd.MetadataException> metadataException;

    /**
     * Gets the value of the metadataException property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link gov.nist.registry.common2.exception.xsd.MetadataException }{@code >}
     *     
     */
    public JAXBElement<gov.nist.registry.common2.exception.xsd.MetadataException> getMetadataException() {
        return metadataException;
    }

    /**
     * Sets the value of the metadataException property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link gov.nist.registry.common2.exception.xsd.MetadataException }{@code >}
     *     
     */
    public void setMetadataException(JAXBElement<gov.nist.registry.common2.exception.xsd.MetadataException> value) {
        this.metadataException = value;
    }

}
