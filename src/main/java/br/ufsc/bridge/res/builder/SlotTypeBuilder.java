package br.ufsc.bridge.res.builder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import lombok.NoArgsConstructor;

import oasis.names.tc.ebxml_regrep.xsd.rim._3.ObjectFactory;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.SlotType1;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ValueListType;

@NoArgsConstructor
@SuppressWarnings("unchecked")
public class SlotTypeBuilder<T extends SlotTypeBuilder<T>> {

	private List<SlotType1> list;
	private String name;
	private List<String> values;

	public SlotTypeBuilder(List<SlotType1> list) {
		this.list = list;
		this.values = new LinkedList<>();
	}

	public T name(String name) {
		this.name = name;
		return (T) this;
	}

	public T value(String value) {
		this.values.add(value);
		return (T) this;
	}

	@SuppressWarnings("rawtypes")
	public JAXBElement createElement() {
		return new ObjectFactory().createSlot(this.createSlot());
	}

	public T add() {
		return this.addIf(true);
	}

	public T addIf(boolean add) {
		if (add) {
			this.list.add(this.createSlot());
		} else {
			this.clear();
		}
		return (T) this;
	}

	public SlotType1 createSlot() {
		ValueListType listType = new ValueListType();
		listType.getValue().addAll(this.values);

		SlotType1 slotType1 = new SlotType1();
		slotType1.setName(this.name);
		slotType1.setValueList(listType);

		this.clear();

		return slotType1;
	}

	private void clear() {
		this.name = null;
		this.values = new ArrayList<>();
	}

}
