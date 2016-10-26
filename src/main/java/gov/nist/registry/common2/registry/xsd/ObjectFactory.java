
package gov.nist.registry.common2.registry.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gov.nist.registry.common2.registry.xsd package. 
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

    private final static QName _ResponseErrorsAndWarnings_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "errorsAndWarnings");
    private final static QName _ResponseResponse_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "response");
    private final static QName _ResponseRoot_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "root");
    private final static QName _MetadataPatientIds_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "patientIds");
    private final static QName _MetadataIdsOfReferencedObjects_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "idsOfReferencedObjects");
    private final static QName _MetadataSubmissionSetId_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "submissionSetId");
    private final static QName _MetadataFolderIds_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "folderIds");
    private final static QName _MetadataMetadataAsObjectRefs_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "metadataAsObjectRefs");
    private final static QName _MetadataObjectIdsToDeprecate_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "objectIdsToDeprecate");
    private final static QName _MetadataRegistryPackages_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "registryPackages");
    private final static QName _MetadataAllDefinedIds_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "allDefinedIds");
    private final static QName _MetadataUidHashMap_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "uidHashMap");
    private final static QName _MetadataAssociations_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "associations");
    private final static QName _MetadataRegistryPackageIds_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "registryPackageIds");
    private final static QName _MetadataAssociationIds_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "associationIds");
    private final static QName _MetadataMetadata_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "metadata");
    private final static QName _MetadataFolders_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "folders");
    private final static QName _MetadataAllUids_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "allUids");
    private final static QName _MetadataSubmissionSetPatientId_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "submissionSetPatientId");
    private final static QName _MetadataSubmissionSet_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "submissionSet");
    private final static QName _MetadataAssocReferences_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "assocReferences");
    private final static QName _MetadataObjectRefs_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "objectRefs");
    private final static QName _MetadataWrapper_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "wrapper");
    private final static QName _MetadataObjectRefIds_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "objectRefIds");
    private final static QName _MetadataSubmissionSets_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "submissionSets");
    private final static QName _MetadataExtrinsicObjects_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "extrinsicObjects");
    private final static QName _MetadataAllObjects_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "allObjects");
    private final static QName _MetadataClassifications_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "classifications");
    private final static QName _MetadataSubmissionSetUniqueId_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "submissionSetUniqueId");
    private final static QName _MetadataUidMap_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "uidMap");
    private final static QName _MetadataNonObjectRefs_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "nonObjectRefs");
    private final static QName _MetadataSubmissionSetIds_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "submissionSetIds");
    private final static QName _MetadataV2_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "v2");
    private final static QName _MetadataV3_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "v3");
    private final static QName _MetadataReferencedObjectsThatMustHaveSamePatientId_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "referencedObjectsThatMustHaveSamePatientId");
    private final static QName _MetadataDocumentUidMap_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "documentUidMap");
    private final static QName _MetadataOriginal_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "original");
    private final static QName _MetadataAllLeafClasses_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "allLeafClasses");
    private final static QName _MetadataExtrinsicObjectIds_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "extrinsicObjectIds");
    private final static QName _MetadataMajorObjects_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "majorObjects");
    private final static QName _MetadataMetadataDescription_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "metadataDescription");
    private final static QName _MetadataV3SubmitObjectsRequest_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "v3SubmitObjectsRequest");
    private final static QName _MetadataLeafClassObjects_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "leafClassObjects");
    private final static QName _MetadataV2SubmitObjectsRequest_QNAME = new QName("http://registry.common2.registry.nist.gov/xsd", "v2SubmitObjectsRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gov.nist.registry.common2.registry.xsd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Metadata }
     * 
     */
    public Metadata createMetadata() {
        return new Metadata();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "errorsAndWarnings", scope = Response.class)
    public JAXBElement<String> createResponseErrorsAndWarnings(String value) {
        return new JAXBElement<String>(_ResponseErrorsAndWarnings_QNAME, String.class, Response.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "response", scope = Response.class)
    public JAXBElement<Object> createResponseResponse(Object value) {
        return new JAXBElement<Object>(_ResponseResponse_QNAME, Object.class, Response.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "root", scope = Response.class)
    public JAXBElement<Object> createResponseRoot(Object value) {
        return new JAXBElement<Object>(_ResponseRoot_QNAME, Object.class, Response.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "patientIds", scope = Metadata.class)
    public JAXBElement<Object> createMetadataPatientIds(Object value) {
        return new JAXBElement<Object>(_MetadataPatientIds_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "idsOfReferencedObjects", scope = Metadata.class)
    public JAXBElement<Object> createMetadataIdsOfReferencedObjects(Object value) {
        return new JAXBElement<Object>(_MetadataIdsOfReferencedObjects_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "root", scope = Metadata.class)
    public JAXBElement<Object> createMetadataRoot(Object value) {
        return new JAXBElement<Object>(_ResponseRoot_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "submissionSetId", scope = Metadata.class)
    public JAXBElement<String> createMetadataSubmissionSetId(String value) {
        return new JAXBElement<String>(_MetadataSubmissionSetId_QNAME, String.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "folderIds", scope = Metadata.class)
    public JAXBElement<Object> createMetadataFolderIds(Object value) {
        return new JAXBElement<Object>(_MetadataFolderIds_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Metadata }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "metadataAsObjectRefs", scope = Metadata.class)
    public JAXBElement<Metadata> createMetadataMetadataAsObjectRefs(Metadata value) {
        return new JAXBElement<Metadata>(_MetadataMetadataAsObjectRefs_QNAME, Metadata.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "objectIdsToDeprecate", scope = Metadata.class)
    public JAXBElement<Object> createMetadataObjectIdsToDeprecate(Object value) {
        return new JAXBElement<Object>(_MetadataObjectIdsToDeprecate_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "registryPackages", scope = Metadata.class)
    public JAXBElement<Object> createMetadataRegistryPackages(Object value) {
        return new JAXBElement<Object>(_MetadataRegistryPackages_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "allDefinedIds", scope = Metadata.class)
    public JAXBElement<Object> createMetadataAllDefinedIds(Object value) {
        return new JAXBElement<Object>(_MetadataAllDefinedIds_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "uidHashMap", scope = Metadata.class)
    public JAXBElement<Object> createMetadataUidHashMap(Object value) {
        return new JAXBElement<Object>(_MetadataUidHashMap_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "associations", scope = Metadata.class)
    public JAXBElement<Object> createMetadataAssociations(Object value) {
        return new JAXBElement<Object>(_MetadataAssociations_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "registryPackageIds", scope = Metadata.class)
    public JAXBElement<Object> createMetadataRegistryPackageIds(Object value) {
        return new JAXBElement<Object>(_MetadataRegistryPackageIds_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "associationIds", scope = Metadata.class)
    public JAXBElement<Object> createMetadataAssociationIds(Object value) {
        return new JAXBElement<Object>(_MetadataAssociationIds_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "metadata", scope = Metadata.class)
    public JAXBElement<Object> createMetadataMetadata(Object value) {
        return new JAXBElement<Object>(_MetadataMetadata_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "folders", scope = Metadata.class)
    public JAXBElement<Object> createMetadataFolders(Object value) {
        return new JAXBElement<Object>(_MetadataFolders_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "allUids", scope = Metadata.class)
    public JAXBElement<Object> createMetadataAllUids(Object value) {
        return new JAXBElement<Object>(_MetadataAllUids_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "submissionSetPatientId", scope = Metadata.class)
    public JAXBElement<String> createMetadataSubmissionSetPatientId(String value) {
        return new JAXBElement<String>(_MetadataSubmissionSetPatientId_QNAME, String.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "submissionSet", scope = Metadata.class)
    public JAXBElement<Object> createMetadataSubmissionSet(Object value) {
        return new JAXBElement<Object>(_MetadataSubmissionSet_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "assocReferences", scope = Metadata.class)
    public JAXBElement<Object> createMetadataAssocReferences(Object value) {
        return new JAXBElement<Object>(_MetadataAssocReferences_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "objectRefs", scope = Metadata.class)
    public JAXBElement<Object> createMetadataObjectRefs(Object value) {
        return new JAXBElement<Object>(_MetadataObjectRefs_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "wrapper", scope = Metadata.class)
    public JAXBElement<Object> createMetadataWrapper(Object value) {
        return new JAXBElement<Object>(_MetadataWrapper_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "objectRefIds", scope = Metadata.class)
    public JAXBElement<Object> createMetadataObjectRefIds(Object value) {
        return new JAXBElement<Object>(_MetadataObjectRefIds_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "submissionSets", scope = Metadata.class)
    public JAXBElement<Object> createMetadataSubmissionSets(Object value) {
        return new JAXBElement<Object>(_MetadataSubmissionSets_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "extrinsicObjects", scope = Metadata.class)
    public JAXBElement<Object> createMetadataExtrinsicObjects(Object value) {
        return new JAXBElement<Object>(_MetadataExtrinsicObjects_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "allObjects", scope = Metadata.class)
    public JAXBElement<Object> createMetadataAllObjects(Object value) {
        return new JAXBElement<Object>(_MetadataAllObjects_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "classifications", scope = Metadata.class)
    public JAXBElement<Object> createMetadataClassifications(Object value) {
        return new JAXBElement<Object>(_MetadataClassifications_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "submissionSetUniqueId", scope = Metadata.class)
    public JAXBElement<String> createMetadataSubmissionSetUniqueId(String value) {
        return new JAXBElement<String>(_MetadataSubmissionSetUniqueId_QNAME, String.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "uidMap", scope = Metadata.class)
    public JAXBElement<Object> createMetadataUidMap(Object value) {
        return new JAXBElement<Object>(_MetadataUidMap_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "nonObjectRefs", scope = Metadata.class)
    public JAXBElement<Object> createMetadataNonObjectRefs(Object value) {
        return new JAXBElement<Object>(_MetadataNonObjectRefs_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "submissionSetIds", scope = Metadata.class)
    public JAXBElement<Object> createMetadataSubmissionSetIds(Object value) {
        return new JAXBElement<Object>(_MetadataSubmissionSetIds_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "v2", scope = Metadata.class)
    public JAXBElement<Object> createMetadataV2(Object value) {
        return new JAXBElement<Object>(_MetadataV2_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "v3", scope = Metadata.class)
    public JAXBElement<Object> createMetadataV3(Object value) {
        return new JAXBElement<Object>(_MetadataV3_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "referencedObjectsThatMustHaveSamePatientId", scope = Metadata.class)
    public JAXBElement<Object> createMetadataReferencedObjectsThatMustHaveSamePatientId(Object value) {
        return new JAXBElement<Object>(_MetadataReferencedObjectsThatMustHaveSamePatientId_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "documentUidMap", scope = Metadata.class)
    public JAXBElement<Object> createMetadataDocumentUidMap(Object value) {
        return new JAXBElement<Object>(_MetadataDocumentUidMap_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "original", scope = Metadata.class)
    public JAXBElement<Object> createMetadataOriginal(Object value) {
        return new JAXBElement<Object>(_MetadataOriginal_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "allLeafClasses", scope = Metadata.class)
    public JAXBElement<Object> createMetadataAllLeafClasses(Object value) {
        return new JAXBElement<Object>(_MetadataAllLeafClasses_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "extrinsicObjectIds", scope = Metadata.class)
    public JAXBElement<Object> createMetadataExtrinsicObjectIds(Object value) {
        return new JAXBElement<Object>(_MetadataExtrinsicObjectIds_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "majorObjects", scope = Metadata.class)
    public JAXBElement<Object> createMetadataMajorObjects(Object value) {
        return new JAXBElement<Object>(_MetadataMajorObjects_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "metadataDescription", scope = Metadata.class)
    public JAXBElement<String> createMetadataMetadataDescription(String value) {
        return new JAXBElement<String>(_MetadataMetadataDescription_QNAME, String.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "v3SubmitObjectsRequest", scope = Metadata.class)
    public JAXBElement<Object> createMetadataV3SubmitObjectsRequest(Object value) {
        return new JAXBElement<Object>(_MetadataV3SubmitObjectsRequest_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "leafClassObjects", scope = Metadata.class)
    public JAXBElement<Object> createMetadataLeafClassObjects(Object value) {
        return new JAXBElement<Object>(_MetadataLeafClassObjects_QNAME, Object.class, Metadata.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.common2.registry.nist.gov/xsd", name = "v2SubmitObjectsRequest", scope = Metadata.class)
    public JAXBElement<Object> createMetadataV2SubmitObjectsRequest(Object value) {
        return new JAXBElement<Object>(_MetadataV2SubmitObjectsRequest_QNAME, Object.class, Metadata.class, value);
    }

}
