package br.ufsc.bridge.res.builder;

import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExternalIdentifierType;

public class ExternalIdentifierTypeBuilder<T extends ExternalIdentifierTypeBuilder<T>> {

	private ExternalIdentifierType externalIdentifier;

	public ExternalIdentifierTypeBuilder() {
		this.externalIdentifier = new ExternalIdentifierType();
		this.externalIdentifier.setObjectType("urn:oasis:names:tc:ebxml-regrep:ObjectType:RegistryObject:ExternalIdentifier");
	}

	public T identificationScheme(String identificationScheme) {
		this.externalIdentifier.setIdentificationScheme(identificationScheme);
		return (T) this;
	}

	public T value(String value) {
		this.externalIdentifier.setValue(value);
		return (T) this;
	}

	public T id(String id) {
		this.externalIdentifier.setId(id);
		return (T) this;
	}

	public T registryObject(String registryObject) {
		this.externalIdentifier.setRegistryObject(registryObject);
		return (T) this;
	}

	public ExternalIdentifierType createExternalIdentifier() {
		ExternalIdentifierType aux = this.externalIdentifier;
		aux.setObjectType("urn:oasis:names:tc:ebxml-regrep:ObjectType:RegistryObject:ExternalIdentifier");
		this.externalIdentifier = new ExternalIdentifierType();
		return aux;
	}

}
