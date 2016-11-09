package br.ufsc.bridge.res.builder;

import javax.xml.bind.JAXBElement;

import lombok.AllArgsConstructor;

import oasis.names.tc.ebxml_regrep.xsd.rim._3.ObjectFactory;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.RegistryPackageType;

public class RegistryPackageTypeBuilder<T extends RegistryPackageTypeBuilder<T>> {

	private static final String SS1 = "ss1";
	private RegistryPackageType registryPackage;

	public RegistryPackageTypeBuilder() {
		this.registryPackage = new RegistryPackageType();
		this.registryPackage.setId(SS1);
	}

	public SlotTypeBuilderWrapper buildSlot() {
		return new SlotTypeBuilderWrapper((T) this);
	}

	public ClassificationTypeBuilderWrapper buildClassification() {
		return new ClassificationTypeBuilderWrapper((T) this);
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
	public class SlotTypeBuilderWrapper extends SlotTypeBuilder<SlotTypeBuilderWrapper> {
		private T parent;

		public SlotTypeBuilderWrapper addSlot() {
			this.addSlotToParent();
			return this;
		}

		public T addSlotEnd() {
			this.addSlotToParent();
			return this.parent;
		}

		private void addSlotToParent() {
			((RegistryPackageTypeBuilder) this.parent).registryPackage.getSlot().add(this.createSlot());
		}
	}

	@AllArgsConstructor
	public class ClassificationTypeBuilderWrapper extends ClassificationTypeBuilder<ClassificationTypeBuilderWrapper> {
		private T parent;

		public ClassificationTypeBuilderWrapper addClassification() {
			this.addClassificationToParent();
			return this;
		}

		public T addClassificationEnd() {
			this.addClassificationToParent();
			return this.parent;
		}

		private void addClassificationToParent() {
			this.classifiedObject(SS1);
			((RegistryPackageTypeBuilder) this.parent).registryPackage.getClassification().add(this.createClassification());
		}
	}
}
