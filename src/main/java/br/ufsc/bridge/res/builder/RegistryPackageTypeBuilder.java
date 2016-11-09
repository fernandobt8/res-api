package br.ufsc.bridge.res.builder;

import java.util.List;

import javax.xml.bind.JAXBElement;

import lombok.AllArgsConstructor;

import br.ufsc.bridge.res.builder.ClassificationTypeBuilder.ClassificationTypeBuilderWrapper;
import br.ufsc.bridge.res.builder.ExternalIdentifierTypeBuilder.ExternalIdentifierTypeBuilderWrapper;
import br.ufsc.bridge.res.builder.SlotTypeBuilder.SlotTypeBuilderWrapper;

import oasis.names.tc.ebxml_regrep.xsd.rim._3.ObjectFactory;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.RegistryPackageType;

@SuppressWarnings("unchecked")
public class RegistryPackageTypeBuilder<T extends RegistryPackageTypeBuilder<T>> {

	private static final String SS1 = "ss1";
	private RegistryPackageType registryPackage;

	public RegistryPackageTypeBuilder() {
		this.registryPackage = new RegistryPackageType();
		this.id(SS1);
	}

	public T id(String id) {
		this.registryPackage.setId(id);
		return (T) this;
	}

	public SlotTypeBuilderWrapper<T> buildSlot() {
		return new SlotTypeBuilderWrapper<>((T) this, this.registryPackage.getSlot());
	}

	public ClassificationTypeBuilderWrapper<T> buildClassification() {
		return new ClassificationTypeBuilderWrapper<>((T) this, this.registryPackage.getClassification(), SS1);
	}

	public ExternalIdentifierTypeBuilderWrapper<T> buildExternalIdentifier() {
		return new ExternalIdentifierTypeBuilderWrapper<>((T) this, this.registryPackage.getExternalIdentifier(), SS1);
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
