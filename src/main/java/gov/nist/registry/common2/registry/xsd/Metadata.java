
package gov.nist.registry.common2.registry.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Metadata complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Metadata">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allDefinedIds" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="allLeafClasses" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="allObjects" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="allUids" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="assocReferences" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="associationIds" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="associations" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="classifications" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="documentUidMap" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="extrinsicObjectIds" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="extrinsicObjects" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="folderIds" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="folders" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="grokMetadata" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="idsOfReferencedObjects" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="leafClassObjects" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="majorObjects" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="metadata" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="metadataAsObjectRefs" type="{http://registry.common2.registry.nist.gov/xsd}Metadata" minOccurs="0"/>
 *         &lt;element name="metadataDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nonObjectRefs" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="objectIdsToDeprecate" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="objectRefIds" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="objectRefs" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="objectRefsOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="original" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="patientIdConsistent" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="patientIds" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="referencedObjectsThatMustHaveSamePatientId" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="registryPackageClassificationBroken" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="registryPackageIds" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="registryPackages" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="root" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="submissionSet" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="submissionSetId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="submissionSetIds" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="submissionSetPatientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="submissionSetUniqueId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="submissionSets" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="uidHashMap" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="uidMap" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="uriChunkSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="v2" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="v2SubmitObjectsRequest" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="v3" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="v3SubmitObjectsRequest" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="version2" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="wrapper" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Metadata", propOrder = {
    "allDefinedIds",
    "allLeafClasses",
    "allObjects",
    "allUids",
    "assocReferences",
    "associationIds",
    "associations",
    "classifications",
    "documentUidMap",
    "extrinsicObjectIds",
    "extrinsicObjects",
    "folderIds",
    "folders",
    "grokMetadata",
    "idsOfReferencedObjects",
    "leafClassObjects",
    "majorObjects",
    "metadata",
    "metadataAsObjectRefs",
    "metadataDescription",
    "nonObjectRefs",
    "objectIdsToDeprecate",
    "objectRefIds",
    "objectRefs",
    "objectRefsOnly",
    "original",
    "patientIdConsistent",
    "patientIds",
    "referencedObjectsThatMustHaveSamePatientId",
    "registryPackageClassificationBroken",
    "registryPackageIds",
    "registryPackages",
    "root",
    "submissionSet",
    "submissionSetId",
    "submissionSetIds",
    "submissionSetPatientId",
    "submissionSetUniqueId",
    "submissionSets",
    "uidHashMap",
    "uidMap",
    "uriChunkSize",
    "v2",
    "v2SubmitObjectsRequest",
    "v3",
    "v3SubmitObjectsRequest",
    "version2",
    "wrapper"
})
public class Metadata {

    @XmlElementRef(name = "allDefinedIds", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> allDefinedIds;
    @XmlElementRef(name = "allLeafClasses", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> allLeafClasses;
    @XmlElementRef(name = "allObjects", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> allObjects;
    @XmlElementRef(name = "allUids", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> allUids;
    @XmlElementRef(name = "assocReferences", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> assocReferences;
    @XmlElementRef(name = "associationIds", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> associationIds;
    @XmlElementRef(name = "associations", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> associations;
    @XmlElementRef(name = "classifications", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> classifications;
    @XmlElementRef(name = "documentUidMap", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> documentUidMap;
    @XmlElementRef(name = "extrinsicObjectIds", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> extrinsicObjectIds;
    @XmlElementRef(name = "extrinsicObjects", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> extrinsicObjects;
    @XmlElementRef(name = "folderIds", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> folderIds;
    @XmlElementRef(name = "folders", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> folders;
    protected Boolean grokMetadata;
    @XmlElementRef(name = "idsOfReferencedObjects", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> idsOfReferencedObjects;
    @XmlElementRef(name = "leafClassObjects", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> leafClassObjects;
    @XmlElementRef(name = "majorObjects", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> majorObjects;
    @XmlElementRef(name = "metadata", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> metadata;
    @XmlElementRef(name = "metadataAsObjectRefs", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Metadata> metadataAsObjectRefs;
    @XmlElementRef(name = "metadataDescription", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> metadataDescription;
    @XmlElementRef(name = "nonObjectRefs", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> nonObjectRefs;
    @XmlElementRef(name = "objectIdsToDeprecate", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> objectIdsToDeprecate;
    @XmlElementRef(name = "objectRefIds", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> objectRefIds;
    @XmlElementRef(name = "objectRefs", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> objectRefs;
    protected Boolean objectRefsOnly;
    @XmlElementRef(name = "original", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> original;
    protected Boolean patientIdConsistent;
    @XmlElementRef(name = "patientIds", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> patientIds;
    @XmlElementRef(name = "referencedObjectsThatMustHaveSamePatientId", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> referencedObjectsThatMustHaveSamePatientId;
    protected Boolean registryPackageClassificationBroken;
    @XmlElementRef(name = "registryPackageIds", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> registryPackageIds;
    @XmlElementRef(name = "registryPackages", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> registryPackages;
    @XmlElementRef(name = "root", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> root;
    @XmlElementRef(name = "submissionSet", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> submissionSet;
    @XmlElementRef(name = "submissionSetId", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> submissionSetId;
    @XmlElementRef(name = "submissionSetIds", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> submissionSetIds;
    @XmlElementRef(name = "submissionSetPatientId", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> submissionSetPatientId;
    @XmlElementRef(name = "submissionSetUniqueId", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> submissionSetUniqueId;
    @XmlElementRef(name = "submissionSets", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> submissionSets;
    @XmlElementRef(name = "uidHashMap", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> uidHashMap;
    @XmlElementRef(name = "uidMap", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> uidMap;
    protected Integer uriChunkSize;
    @XmlElementRef(name = "v2", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> v2;
    @XmlElementRef(name = "v2SubmitObjectsRequest", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> v2SubmitObjectsRequest;
    @XmlElementRef(name = "v3", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> v3;
    @XmlElementRef(name = "v3SubmitObjectsRequest", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> v3SubmitObjectsRequest;
    protected Boolean version2;
    @XmlElementRef(name = "wrapper", namespace = "http://registry.common2.registry.nist.gov/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> wrapper;

    /**
     * Gets the value of the allDefinedIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getAllDefinedIds() {
        return allDefinedIds;
    }

    /**
     * Sets the value of the allDefinedIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setAllDefinedIds(JAXBElement<Object> value) {
        this.allDefinedIds = value;
    }

    /**
     * Gets the value of the allLeafClasses property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getAllLeafClasses() {
        return allLeafClasses;
    }

    /**
     * Sets the value of the allLeafClasses property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setAllLeafClasses(JAXBElement<Object> value) {
        this.allLeafClasses = value;
    }

    /**
     * Gets the value of the allObjects property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getAllObjects() {
        return allObjects;
    }

    /**
     * Sets the value of the allObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setAllObjects(JAXBElement<Object> value) {
        this.allObjects = value;
    }

    /**
     * Gets the value of the allUids property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getAllUids() {
        return allUids;
    }

    /**
     * Sets the value of the allUids property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setAllUids(JAXBElement<Object> value) {
        this.allUids = value;
    }

    /**
     * Gets the value of the assocReferences property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getAssocReferences() {
        return assocReferences;
    }

    /**
     * Sets the value of the assocReferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setAssocReferences(JAXBElement<Object> value) {
        this.assocReferences = value;
    }

    /**
     * Gets the value of the associationIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getAssociationIds() {
        return associationIds;
    }

    /**
     * Sets the value of the associationIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setAssociationIds(JAXBElement<Object> value) {
        this.associationIds = value;
    }

    /**
     * Gets the value of the associations property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getAssociations() {
        return associations;
    }

    /**
     * Sets the value of the associations property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setAssociations(JAXBElement<Object> value) {
        this.associations = value;
    }

    /**
     * Gets the value of the classifications property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getClassifications() {
        return classifications;
    }

    /**
     * Sets the value of the classifications property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setClassifications(JAXBElement<Object> value) {
        this.classifications = value;
    }

    /**
     * Gets the value of the documentUidMap property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getDocumentUidMap() {
        return documentUidMap;
    }

    /**
     * Sets the value of the documentUidMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setDocumentUidMap(JAXBElement<Object> value) {
        this.documentUidMap = value;
    }

    /**
     * Gets the value of the extrinsicObjectIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getExtrinsicObjectIds() {
        return extrinsicObjectIds;
    }

    /**
     * Sets the value of the extrinsicObjectIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setExtrinsicObjectIds(JAXBElement<Object> value) {
        this.extrinsicObjectIds = value;
    }

    /**
     * Gets the value of the extrinsicObjects property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getExtrinsicObjects() {
        return extrinsicObjects;
    }

    /**
     * Sets the value of the extrinsicObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setExtrinsicObjects(JAXBElement<Object> value) {
        this.extrinsicObjects = value;
    }

    /**
     * Gets the value of the folderIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getFolderIds() {
        return folderIds;
    }

    /**
     * Sets the value of the folderIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setFolderIds(JAXBElement<Object> value) {
        this.folderIds = value;
    }

    /**
     * Gets the value of the folders property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getFolders() {
        return folders;
    }

    /**
     * Sets the value of the folders property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setFolders(JAXBElement<Object> value) {
        this.folders = value;
    }

    /**
     * Gets the value of the grokMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGrokMetadata() {
        return grokMetadata;
    }

    /**
     * Sets the value of the grokMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGrokMetadata(Boolean value) {
        this.grokMetadata = value;
    }

    /**
     * Gets the value of the idsOfReferencedObjects property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getIdsOfReferencedObjects() {
        return idsOfReferencedObjects;
    }

    /**
     * Sets the value of the idsOfReferencedObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setIdsOfReferencedObjects(JAXBElement<Object> value) {
        this.idsOfReferencedObjects = value;
    }

    /**
     * Gets the value of the leafClassObjects property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getLeafClassObjects() {
        return leafClassObjects;
    }

    /**
     * Sets the value of the leafClassObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setLeafClassObjects(JAXBElement<Object> value) {
        this.leafClassObjects = value;
    }

    /**
     * Gets the value of the majorObjects property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getMajorObjects() {
        return majorObjects;
    }

    /**
     * Sets the value of the majorObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setMajorObjects(JAXBElement<Object> value) {
        this.majorObjects = value;
    }

    /**
     * Gets the value of the metadata property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getMetadata() {
        return metadata;
    }

    /**
     * Sets the value of the metadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setMetadata(JAXBElement<Object> value) {
        this.metadata = value;
    }

    /**
     * Gets the value of the metadataAsObjectRefs property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Metadata }{@code >}
     *     
     */
    public JAXBElement<Metadata> getMetadataAsObjectRefs() {
        return metadataAsObjectRefs;
    }

    /**
     * Sets the value of the metadataAsObjectRefs property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Metadata }{@code >}
     *     
     */
    public void setMetadataAsObjectRefs(JAXBElement<Metadata> value) {
        this.metadataAsObjectRefs = value;
    }

    /**
     * Gets the value of the metadataDescription property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMetadataDescription() {
        return metadataDescription;
    }

    /**
     * Sets the value of the metadataDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMetadataDescription(JAXBElement<String> value) {
        this.metadataDescription = value;
    }

    /**
     * Gets the value of the nonObjectRefs property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getNonObjectRefs() {
        return nonObjectRefs;
    }

    /**
     * Sets the value of the nonObjectRefs property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setNonObjectRefs(JAXBElement<Object> value) {
        this.nonObjectRefs = value;
    }

    /**
     * Gets the value of the objectIdsToDeprecate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getObjectIdsToDeprecate() {
        return objectIdsToDeprecate;
    }

    /**
     * Sets the value of the objectIdsToDeprecate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setObjectIdsToDeprecate(JAXBElement<Object> value) {
        this.objectIdsToDeprecate = value;
    }

    /**
     * Gets the value of the objectRefIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getObjectRefIds() {
        return objectRefIds;
    }

    /**
     * Sets the value of the objectRefIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setObjectRefIds(JAXBElement<Object> value) {
        this.objectRefIds = value;
    }

    /**
     * Gets the value of the objectRefs property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getObjectRefs() {
        return objectRefs;
    }

    /**
     * Sets the value of the objectRefs property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setObjectRefs(JAXBElement<Object> value) {
        this.objectRefs = value;
    }

    /**
     * Gets the value of the objectRefsOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isObjectRefsOnly() {
        return objectRefsOnly;
    }

    /**
     * Sets the value of the objectRefsOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setObjectRefsOnly(Boolean value) {
        this.objectRefsOnly = value;
    }

    /**
     * Gets the value of the original property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getOriginal() {
        return original;
    }

    /**
     * Sets the value of the original property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setOriginal(JAXBElement<Object> value) {
        this.original = value;
    }

    /**
     * Gets the value of the patientIdConsistent property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPatientIdConsistent() {
        return patientIdConsistent;
    }

    /**
     * Sets the value of the patientIdConsistent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPatientIdConsistent(Boolean value) {
        this.patientIdConsistent = value;
    }

    /**
     * Gets the value of the patientIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getPatientIds() {
        return patientIds;
    }

    /**
     * Sets the value of the patientIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setPatientIds(JAXBElement<Object> value) {
        this.patientIds = value;
    }

    /**
     * Gets the value of the referencedObjectsThatMustHaveSamePatientId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getReferencedObjectsThatMustHaveSamePatientId() {
        return referencedObjectsThatMustHaveSamePatientId;
    }

    /**
     * Sets the value of the referencedObjectsThatMustHaveSamePatientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setReferencedObjectsThatMustHaveSamePatientId(JAXBElement<Object> value) {
        this.referencedObjectsThatMustHaveSamePatientId = value;
    }

    /**
     * Gets the value of the registryPackageClassificationBroken property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRegistryPackageClassificationBroken() {
        return registryPackageClassificationBroken;
    }

    /**
     * Sets the value of the registryPackageClassificationBroken property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRegistryPackageClassificationBroken(Boolean value) {
        this.registryPackageClassificationBroken = value;
    }

    /**
     * Gets the value of the registryPackageIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getRegistryPackageIds() {
        return registryPackageIds;
    }

    /**
     * Sets the value of the registryPackageIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setRegistryPackageIds(JAXBElement<Object> value) {
        this.registryPackageIds = value;
    }

    /**
     * Gets the value of the registryPackages property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getRegistryPackages() {
        return registryPackages;
    }

    /**
     * Sets the value of the registryPackages property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setRegistryPackages(JAXBElement<Object> value) {
        this.registryPackages = value;
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

    /**
     * Gets the value of the submissionSet property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getSubmissionSet() {
        return submissionSet;
    }

    /**
     * Sets the value of the submissionSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setSubmissionSet(JAXBElement<Object> value) {
        this.submissionSet = value;
    }

    /**
     * Gets the value of the submissionSetId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubmissionSetId() {
        return submissionSetId;
    }

    /**
     * Sets the value of the submissionSetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubmissionSetId(JAXBElement<String> value) {
        this.submissionSetId = value;
    }

    /**
     * Gets the value of the submissionSetIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getSubmissionSetIds() {
        return submissionSetIds;
    }

    /**
     * Sets the value of the submissionSetIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setSubmissionSetIds(JAXBElement<Object> value) {
        this.submissionSetIds = value;
    }

    /**
     * Gets the value of the submissionSetPatientId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubmissionSetPatientId() {
        return submissionSetPatientId;
    }

    /**
     * Sets the value of the submissionSetPatientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubmissionSetPatientId(JAXBElement<String> value) {
        this.submissionSetPatientId = value;
    }

    /**
     * Gets the value of the submissionSetUniqueId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubmissionSetUniqueId() {
        return submissionSetUniqueId;
    }

    /**
     * Sets the value of the submissionSetUniqueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubmissionSetUniqueId(JAXBElement<String> value) {
        this.submissionSetUniqueId = value;
    }

    /**
     * Gets the value of the submissionSets property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getSubmissionSets() {
        return submissionSets;
    }

    /**
     * Sets the value of the submissionSets property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setSubmissionSets(JAXBElement<Object> value) {
        this.submissionSets = value;
    }

    /**
     * Gets the value of the uidHashMap property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getUidHashMap() {
        return uidHashMap;
    }

    /**
     * Sets the value of the uidHashMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setUidHashMap(JAXBElement<Object> value) {
        this.uidHashMap = value;
    }

    /**
     * Gets the value of the uidMap property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getUidMap() {
        return uidMap;
    }

    /**
     * Sets the value of the uidMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setUidMap(JAXBElement<Object> value) {
        this.uidMap = value;
    }

    /**
     * Gets the value of the uriChunkSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUriChunkSize() {
        return uriChunkSize;
    }

    /**
     * Sets the value of the uriChunkSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUriChunkSize(Integer value) {
        this.uriChunkSize = value;
    }

    /**
     * Gets the value of the v2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getV2() {
        return v2;
    }

    /**
     * Sets the value of the v2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setV2(JAXBElement<Object> value) {
        this.v2 = value;
    }

    /**
     * Gets the value of the v2SubmitObjectsRequest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getV2SubmitObjectsRequest() {
        return v2SubmitObjectsRequest;
    }

    /**
     * Sets the value of the v2SubmitObjectsRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setV2SubmitObjectsRequest(JAXBElement<Object> value) {
        this.v2SubmitObjectsRequest = value;
    }

    /**
     * Gets the value of the v3 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getV3() {
        return v3;
    }

    /**
     * Sets the value of the v3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setV3(JAXBElement<Object> value) {
        this.v3 = value;
    }

    /**
     * Gets the value of the v3SubmitObjectsRequest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getV3SubmitObjectsRequest() {
        return v3SubmitObjectsRequest;
    }

    /**
     * Sets the value of the v3SubmitObjectsRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setV3SubmitObjectsRequest(JAXBElement<Object> value) {
        this.v3SubmitObjectsRequest = value;
    }

    /**
     * Gets the value of the version2 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVersion2() {
        return version2;
    }

    /**
     * Sets the value of the version2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVersion2(Boolean value) {
        this.version2 = value;
    }

    /**
     * Gets the value of the wrapper property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getWrapper() {
        return wrapper;
    }

    /**
     * Sets the value of the wrapper property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setWrapper(JAXBElement<Object> value) {
        this.wrapper = value;
    }

}
