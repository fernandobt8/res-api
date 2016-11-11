package br.ufsc.bridge.res.service.builder;

import java.util.List;

import javax.xml.bind.JAXBElement;

import oasis.names.tc.ebxml_regrep.xsd.rim._3.ObjectFactory;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.SlotType1;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ValueListType;

@SuppressWarnings("unchecked")
public class SlotTypeBuilder<T extends SlotTypeBuilder<T>> {

	private SlotType1 slot;

	public SlotTypeBuilder() {
		this.clear();
	}

	protected void clear() {
		this.slot = new SlotType1();
		ValueListType listType = new ValueListType();
		this.slot.setValueList(listType);
	}

	public T name(String name) {
		this.slot.setName(name);
		return (T) this;
	}

	public T value(String value) {
		this.slot.getValueList().getValue().add(value);
		return (T) this;
	}

	@SuppressWarnings("rawtypes")
	public JAXBElement createElement() {
		return new ObjectFactory().createSlot(this.createSlot());
	}

	public SlotType1 createSlot() {
		SlotType1 aux = this.slot;
		this.clear();
		return aux;
	}

	public static class SlotTypeBuilderWrapper<PARENT> extends SlotTypeBuilder<SlotTypeBuilderWrapper<PARENT>> {
		private PARENT parent;
		private List<SlotType1> slots;

		public SlotTypeBuilderWrapper(List<SlotType1> slots) {
			this(null, slots);
		}

		public SlotTypeBuilderWrapper(PARENT parent, List<SlotType1> slots) {
			this.slots = slots;
			this.parent = parent;
		}

		public SlotTypeBuilderWrapper<PARENT> addSlot() {
			return this.addSlotIf(true);
		}

		public SlotTypeBuilderWrapper<PARENT> addSlotIf(boolean add) {
			if (add) {
				this.slots.add(this.createSlot());
			} else {
				this.clear();
			}
			return this;
		}

		public PARENT addSlotEnd() {
			this.addSlot();
			return this.parent;
		}
	}

}
