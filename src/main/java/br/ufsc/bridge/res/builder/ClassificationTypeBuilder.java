package br.ufsc.bridge.res.builder;

import java.util.List;

import javax.xml.bind.JAXBElement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import oasis.names.tc.ebxml_regrep.xsd.rim._3.ClassificationType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ObjectFactory;

@NoArgsConstructor
@SuppressWarnings("unchecked")
public class ClassificationTypeBuilder<T extends ClassificationTypeBuilder<T>> {

	private List<JAXBElement> identifiables;
	private ClassificationType classificationType;

	public ClassificationTypeBuilder(List<JAXBElement> list) {
		this.identifiables = list;
		this.classificationType = new ClassificationType();
	}

	public T classificationScheme(String classificationScheme) {
		this.classificationType.setClassificationScheme(classificationScheme);
		return (T) this;
	}

	public T classifiedObject(String classifiedObject) {
		this.classificationType.setClassifiedObject(classifiedObject);
		return (T) this;
	}

	public T id(String id) {
		this.classificationType.setId(id);
		return (T) this;
	}

	public T nodeRepresentation(String nodeRepresentation) {
		this.classificationType.setNodeRepresentation(nodeRepresentation);
		return (T) this;
	}

	public T classificationNode(String classificationNode) {
		this.classificationType.setClassificationNode(classificationNode);
		return (T) this;
	}

	public T add() {
		this.identifiables.add(this.createElement());
		return (T) this;
	}

	public JAXBElement<ClassificationType> createElement() {
		return new ObjectFactory().createClassification(this.createClassification());
	}

	public ClassificationType createClassification() {
		ClassificationType aux = this.classificationType;
		aux.setObjectType("urn:oasis:names:tc:ebxml-regrep:ObjectType:RegistryObject:Classification");
		this.classificationType = new ClassificationType();
		return aux;
	}

	public SlotTypeBuilderWrapper buildSlot() {
		return new SlotTypeBuilderWrapper((T) this);
	}

	@AllArgsConstructor
	@SuppressWarnings("rawtypes")
	public class SlotTypeBuilderWrapper extends SlotTypeBuilder<SlotTypeBuilderWrapper> {
		private T parent;

		public SlotTypeBuilderWrapper addSlot() {
			((ClassificationTypeBuilder) this.parent).classificationType.getSlot().add(this.createSlot());
			return this;
		}

		public T addSlotEnd() {
			((ClassificationTypeBuilder) this.parent).classificationType.getSlot().add(this.createSlot());
			return this.parent;
		}
	}
}
