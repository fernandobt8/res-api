package br.ufsc.bridge.res.service.builder;

import java.util.List;

import lombok.AllArgsConstructor;

import br.ufsc.bridge.res.service.builder.InternationalStringTypeBuilder.InternationalStringTypeBuilderWrapper;

import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExternalIdentifierType;

@SuppressWarnings("unchecked")
public class ExternalIdentifierTypeBuilder<T extends ExternalIdentifierTypeBuilder<T>> {

	private ExternalIdentifierType externalIdentifier;

	public ExternalIdentifierTypeBuilder() {
		this.externalIdentifier = new ExternalIdentifierType();
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

	public InternationalStringTypeBuilderWrapper<T> buildInternationalString() {
		return new InternationalStringTypeBuilderWrapper<>((T) this, this.externalIdentifier);
	}

	public ExternalIdentifierType createExternalIdentifier() {
		ExternalIdentifierType aux = this.externalIdentifier;
		aux.setObjectType("urn:oasis:names:tc:ebxml-regrep:ObjectType:RegistryObject:ExternalIdentifier");
		this.externalIdentifier = new ExternalIdentifierType();
		return aux;
	}

	@AllArgsConstructor
	public static class ExternalIdentifierTypeBuilderWrapper<PARENT> extends ExternalIdentifierTypeBuilder<ExternalIdentifierTypeBuilderWrapper<PARENT>> {
		private PARENT parent;
		private List<ExternalIdentifierType> externals;
		private String registryObject;

		public ExternalIdentifierTypeBuilderWrapper<PARENT> addExternalIdentifier() {
			this.registryObject(this.registryObject);
			this.externals.add(this.createExternalIdentifier());
			return this;
		}

		public PARENT addExternalIdentifierEnd() {
			this.addExternalIdentifier();
			return this.parent;
		}
	}
}
