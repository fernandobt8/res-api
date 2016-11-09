package br.ufsc.bridge.res.builder;

import java.util.List;

import javax.xml.bind.JAXBElement;

import br.ufsc.bridge.res.builder.AssociationTypeBuilder.AssociationTypeBuilderWrapper;
import br.ufsc.bridge.res.builder.ClassificationTypeBuilder.ClassificationTypeBuilderWrapper;
import br.ufsc.bridge.res.builder.ExtrinsicObjectTypeBuilder.ExtrinsicObjectTypeBuilderWrapper;
import br.ufsc.bridge.res.builder.RegistryPackageTypeBuilder.RegistryPackageTypeBuilderWrapper;
import br.ufsc.bridge.res.builder.SlotTypeBuilder.SlotTypeBuilderWrapper;

public class IdentifiableTypeBuilder {

	private List<JAXBElement<?>> identifiables;

	public IdentifiableTypeBuilder(List<JAXBElement<?>> list) {
		this.identifiables = list;
	}

	public SlotTypeBuilderWrapper<IdentifiableTypeBuilder> buildSlot() {
		return new SlotTypeBuilderWrapper<IdentifiableTypeBuilder>(this, null) {
			@Override
			public SlotTypeBuilderWrapper<IdentifiableTypeBuilder> addSlot() {
				IdentifiableTypeBuilder.this.identifiables.add(this.createElement());
				return this;
			}
		};
	}

	public ClassificationTypeBuilderWrapper<IdentifiableTypeBuilder> buildClassification() {
		return new ClassificationTypeBuilderWrapper<IdentifiableTypeBuilder>(this, null, "") {
			@Override
			public ClassificationTypeBuilderWrapper<IdentifiableTypeBuilder> addClassification() {
				IdentifiableTypeBuilder.this.identifiables.add(this.createElement());
				return this;
			}
		};
	}

	public RegistryPackageTypeBuilderWrapper<IdentifiableTypeBuilder> buildRegistryPackage() {
		return new RegistryPackageTypeBuilderWrapper<IdentifiableTypeBuilder>(this, null) {
			@Override
			public RegistryPackageTypeBuilderWrapper<IdentifiableTypeBuilder> addRegistryPackage() {
				IdentifiableTypeBuilder.this.identifiables.add(this.createElement());
				return this;
			}
		};
	}

	public AssociationTypeBuilderWrapper<IdentifiableTypeBuilder> buildAssociation() {
		return new AssociationTypeBuilderWrapper<IdentifiableTypeBuilder>(this, null) {
			@Override
			public AssociationTypeBuilder.AssociationTypeBuilderWrapper<IdentifiableTypeBuilder> addAssociation() {
				IdentifiableTypeBuilder.this.identifiables.add(this.createElement());
				return this;
			}
		};
	}

	public ExtrinsicObjectTypeBuilderWrapper<IdentifiableTypeBuilder> buildExtrinsicObject() {
		return new ExtrinsicObjectTypeBuilderWrapper<IdentifiableTypeBuilder>(this, null) {
			@Override
			public ExtrinsicObjectTypeBuilderWrapper<IdentifiableTypeBuilder> addExtrinsicObject() {
				IdentifiableTypeBuilder.this.identifiables.add(this.createElement());
				return this;
			}
		};
	}
}
