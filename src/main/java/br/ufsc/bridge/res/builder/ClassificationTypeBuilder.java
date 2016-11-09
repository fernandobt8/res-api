package br.ufsc.bridge.res.builder;

import java.util.List;

import javax.xml.bind.JAXBElement;

import lombok.AllArgsConstructor;

import br.ufsc.bridge.res.builder.InternationalStringTypeBuilder.InternationalStringTypeBuilderWrapper;
import br.ufsc.bridge.res.builder.SlotTypeBuilder.SlotTypeBuilderWrapper;

import oasis.names.tc.ebxml_regrep.xsd.rim._3.ClassificationType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ObjectFactory;

@SuppressWarnings("unchecked")
public class ClassificationTypeBuilder<T extends ClassificationTypeBuilder<T>> {

	private ClassificationType classificationType;

	public ClassificationTypeBuilder() {
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

	public JAXBElement<ClassificationType> createElement() {
		return new ObjectFactory().createClassification(this.createClassification());
	}

	public ClassificationType createClassification() {
		ClassificationType aux = this.classificationType;
		aux.setObjectType("urn:oasis:names:tc:ebxml-regrep:ObjectType:RegistryObject:Classification");
		this.classificationType = new ClassificationType();
		return aux;
	}

	public SlotTypeBuilderWrapper<T> buildSlot() {
		return new SlotTypeBuilderWrapper<>((T) this, this.classificationType.getSlot());
	}

	public InternationalStringTypeBuilderWrapper<T> buildInternationalString() {
		return new InternationalStringTypeBuilderWrapper<>((T) this, this.classificationType);
	}

	@AllArgsConstructor
	public static class ClassificationTypeBuilderWrapper<PARENT> extends ClassificationTypeBuilder<ClassificationTypeBuilderWrapper<PARENT>> {
		protected PARENT parent;
		protected List<ClassificationType> classifications;
		protected String classifiedObject;

		public ClassificationTypeBuilderWrapper<PARENT> addClassification() {
			this.classifiedObject(this.classifiedObject);
			this.classifications.add(this.createClassification());
			return this;
		}

		public PARENT addClassificationEnd() {
			this.addClassification();
			return this.parent;
		}
	}
}
