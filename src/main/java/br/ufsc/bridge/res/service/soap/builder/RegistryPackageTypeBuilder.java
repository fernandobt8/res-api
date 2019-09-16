package br.ufsc.bridge.res.service.soap.builder;

import java.util.List;

import javax.xml.bind.JAXBElement;

import lombok.AllArgsConstructor;

import br.ufsc.bridge.res.service.soap.builder.ClassificationTypeBuilder.ClassificationTypeBuilderWrapper;
import br.ufsc.bridge.res.service.soap.builder.SlotTypeBuilder.SlotTypeBuilderWrapper;

import oasis.names.tc.ebxml_regrep.xsd.rim._3.ObjectFactory;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.RegistryPackageType;

@SuppressWarnings("unchecked")
public class RegistryPackageTypeBuilder<T extends RegistryPackageTypeBuilder<T>> {

	private RegistryPackageType registryPackage;

	public RegistryPackageTypeBuilder() {
		this.registryPackage = new RegistryPackageType();
	}

	public T id(String id) {
		this.registryPackage.setId(id);
		return (T) this;
	}

	public SlotTypeBuilderWrapper<T> buildSlot() {
		return new SlotTypeBuilderWrapper<>((T) this, this.registryPackage.getSlot());
	}

	public ClassificationTypeBuilderWrapper<T> buildClassification() {
		return new ClassificationTypeBuilderWrapper<>((T) this, this.registryPackage.getClassification(), this.registryPackage.getId());
	}

	public ExternalIdentifierTypeBuilder.ExternalIdentifierTypeBuilderWrapper<T> buildExternalIdentifier() {
		return new ExternalIdentifierTypeBuilder.ExternalIdentifierTypeBuilderWrapper<>((T) this, this.registryPackage.getExternalIdentifier(), this.registryPackage.getId());
	}

	public JAXBElement<RegistryPackageType> createElement() {
		return new ObjectFactory().createRegistryPackage(this.createRegistryPackage());
	}

	public RegistryPackageType createRegistryPackage() {
		RegistryPackageType aux = this.registryPackage;
		this.registryPackage = new RegistryPackageType();
		return aux;
	}

	@AllArgsConstructor
	public static class RegistryPackageTypeBuilderWrapper<PARENT> extends RegistryPackageTypeBuilder<RegistryPackageTypeBuilderWrapper<PARENT>> {
		protected PARENT parent;
		protected List<RegistryPackageType> registryPackages;

		public RegistryPackageTypeBuilderWrapper<PARENT> addRegistryPackage() {
			this.registryPackages.add(this.createRegistryPackage());
			return this;
		}

		public PARENT addRegistryPackageEnd() {
			this.addRegistryPackage();
			return this.parent;
		}
	}
}
