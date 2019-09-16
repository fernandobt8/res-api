package br.ufsc.bridge.res.service.soap.builder;

import java.util.List;

import javax.xml.bind.JAXBElement;

import lombok.AllArgsConstructor;

import br.ufsc.bridge.res.service.soap.builder.SlotTypeBuilder.SlotTypeBuilderWrapper;

import oasis.names.tc.ebxml_regrep.xsd.rim._3.AssociationType1;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ObjectFactory;

@SuppressWarnings("unchecked")
public class AssociationTypeBuilder<T extends AssociationTypeBuilder<T>> {

	private AssociationType1 association;

	public AssociationTypeBuilder() {
		this.association = new AssociationType1();
	}

	public T id(String id) {
		this.association.setId(id);
		return (T) this;
	}

	public T associationType(String associationType) {
		this.association.setAssociationType(associationType);
		return (T) this;
	}

	public T sourceObject(String sourceObject) {
		this.association.setSourceObject(sourceObject);
		return (T) this;
	}

	public T targetObject(String targetObject) {
		this.association.setTargetObject(targetObject);
		return (T) this;
	}

	public AssociationType1 createAssociation() {
		AssociationType1 aux = this.association;
		this.association = new AssociationType1();
		aux.setObjectType("urn:oasis:names:tc:ebxml-regrep:ObjectType:RegistryObject:Association");
		return aux;
	}

	public JAXBElement<AssociationType1> createElement() {
		return new ObjectFactory().createAssociation(this.createAssociation());
	}

	public SlotTypeBuilderWrapper<T> buildSlot() {
		return new SlotTypeBuilderWrapper<>((T) this, this.association.getSlot());
	}

	@AllArgsConstructor
	public static class AssociationTypeBuilderWrapper<PARENT> extends AssociationTypeBuilder<AssociationTypeBuilderWrapper<PARENT>> {
		private PARENT parent;
		private List<AssociationType1> associations;

		public AssociationTypeBuilderWrapper<PARENT> addAssociation() {
			this.associations.add(this.createAssociation());
			return this;
		}

		public PARENT addAssociationEnd() {
			this.addAssociation();
			return this.parent;
		}
	}
}
