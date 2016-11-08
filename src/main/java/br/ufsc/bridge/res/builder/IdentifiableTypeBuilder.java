package br.ufsc.bridge.res.builder;

import java.util.List;

import javax.xml.bind.JAXBElement;

import lombok.AllArgsConstructor;

public class IdentifiableTypeBuilder {

	private List<JAXBElement> identifiables;

	public IdentifiableTypeBuilder(List<JAXBElement> list) {
		this.identifiables = list;
	}

	public SlotTypeBuilderWrapper buildSlot() {
		return new SlotTypeBuilderWrapper(this);
	}

	public ClassificationTypeBuilderWrapper buildClassification() {
		return new ClassificationTypeBuilderWrapper(this);
	}

	public RegistryPackageTypeBuilderWrapper buildRegistryPackage() {
		return new RegistryPackageTypeBuilderWrapper(this);
	}

	@AllArgsConstructor
	public class SlotTypeBuilderWrapper extends SlotTypeBuilder<SlotTypeBuilderWrapper> {
		private IdentifiableTypeBuilder parent;

		public SlotTypeBuilderWrapper addSlot() {
			this.parent.identifiables.add(this.createElement());
			return this;
		}

		public IdentifiableTypeBuilder addSlotEnd() {
			this.parent.identifiables.add(this.createElement());
			return this.parent;
		}
	}

	@AllArgsConstructor
	public class ClassificationTypeBuilderWrapper extends ClassificationTypeBuilder<ClassificationTypeBuilderWrapper> {
		private IdentifiableTypeBuilder parent;

		public ClassificationTypeBuilderWrapper addClassification() {
			this.parent.identifiables.add(this.createElement());
			return this;
		}

		public IdentifiableTypeBuilder addClassificationEnd() {
			this.parent.identifiables.add(this.createElement());
			return this.parent;
		}
	}

	@AllArgsConstructor
	public class RegistryPackageTypeBuilderWrapper extends RegistryPackageTypeBuilder<RegistryPackageTypeBuilderWrapper> {
		private IdentifiableTypeBuilder parent;

		public RegistryPackageTypeBuilderWrapper addClassification() {
			this.parent.identifiables.add(this.createElement());
			return this;
		}

		public IdentifiableTypeBuilder addClassificationEnd() {
			this.parent.identifiables.add(this.createElement());
			return this.parent;
		}
	}
}
