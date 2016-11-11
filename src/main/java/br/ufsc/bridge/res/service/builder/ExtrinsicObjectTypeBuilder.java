package br.ufsc.bridge.res.service.builder;

import java.util.List;

import javax.xml.bind.JAXBElement;

import lombok.AllArgsConstructor;

import br.ufsc.bridge.res.service.builder.ClassificationTypeBuilder.ClassificationTypeBuilderWrapper;
import br.ufsc.bridge.res.service.builder.ExternalIdentifierTypeBuilder.ExternalIdentifierTypeBuilderWrapper;
import br.ufsc.bridge.res.service.builder.SlotTypeBuilder.SlotTypeBuilderWrapper;

import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExtrinsicObjectType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ObjectFactory;

@SuppressWarnings("unchecked")
public class ExtrinsicObjectTypeBuilder<T extends ExtrinsicObjectTypeBuilder<T>> {

	private static final String DOC1 = "doc1";
	private ExtrinsicObjectType extrinsicObject;

	public ExtrinsicObjectTypeBuilder() {
		this.extrinsicObject = new ExtrinsicObjectType();
		this.extrinsicObject.setId(DOC1);
	}

	public T id(String id) {
		this.extrinsicObject.setId(id);
		return (T) this;
	}

	public T mimeType(String mimeType) {
		this.extrinsicObject.setMimeType(mimeType);
		return (T) this;
	}

	public T objectType(String objectType) {
		this.extrinsicObject.setObjectType(objectType);
		return (T) this;
	}

	public SlotTypeBuilderWrapper<T> buildSlot() {
		return new SlotTypeBuilderWrapper<>((T) this, this.extrinsicObject.getSlot());
	}

	public ClassificationTypeBuilderWrapper<T> buildClassification() {
		return new ClassificationTypeBuilderWrapper<>((T) this, this.extrinsicObject.getClassification(), DOC1);
	}

	public ExternalIdentifierTypeBuilderWrapper<T> buildExternalIdentifier() {
		return new ExternalIdentifierTypeBuilderWrapper<>((T) this, this.extrinsicObject.getExternalIdentifier(), DOC1);
	}

	public JAXBElement<ExtrinsicObjectType> createElement() {
		return new ObjectFactory().createExtrinsicObject(this.createExtrinsicObject());
	}

	public ExtrinsicObjectType createExtrinsicObject() {
		ExtrinsicObjectType aux = this.extrinsicObject;
		this.extrinsicObject = new ExtrinsicObjectType();
		return aux;
	}

	@AllArgsConstructor
	public static class ExtrinsicObjectTypeBuilderWrapper<PARENT> extends ExtrinsicObjectTypeBuilder<ExtrinsicObjectTypeBuilderWrapper<PARENT>> {
		private PARENT parent;
		private List<ExtrinsicObjectType> objects;

		public ExtrinsicObjectTypeBuilderWrapper<PARENT> addExtrinsicObject() {
			this.objects.add(this.createExtrinsicObject());
			return this;
		}

		public PARENT addExtrinsicObjectEnd() {
			this.addExtrinsicObject();
			return this.parent;
		}
	}
}
