
package gov.nist.registry.common2.exception.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import gov.nist.registry.ws.serviceclasses.Exception;


/**
 * <p>Java class for XdsException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="XdsException">
 *   &lt;complexContent>
 *     &lt;extension base="{http://serviceclasses.ws.registry.nist.gov}Exception">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XdsException")
@XmlSeeAlso({
    MetadataException.class
})
public class XdsException
    extends Exception
{


}
