package br.ufsc.bridge.res.builder;

import lombok.AllArgsConstructor;

import oasis.names.tc.ebxml_regrep.xsd.rim._3.InternationalStringType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.LocalizedStringType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.RegistryObjectType;

@SuppressWarnings("unchecked")
public class InternationalStringTypeBuilder<T extends InternationalStringTypeBuilder<T>> {

	private InternationalStringType internationalString;
	private String lang;
	private String value;

	public InternationalStringTypeBuilder() {
		this.internationalString = new InternationalStringType();
	}

	public T lang(String lang) {
		this.lang = lang;
		return (T) this;
	}

	public T value(String value) {
		this.value = value;
		return (T) this;
	}

	public InternationalStringType createElement() {
		this.addLocalizedString();
		InternationalStringType aux = this.internationalString;
		this.internationalString = new InternationalStringType();
		return aux;
	}

	public T addLocalizedString() {
		LocalizedStringType localizedStringType = new LocalizedStringType();
		localizedStringType.setValue(this.value);
		localizedStringType.setLang(this.lang);

		this.internationalString.getLocalizedString().add(localizedStringType);

		this.value = null;
		this.lang = null;
		return (T) this;
	}

	@AllArgsConstructor
	public static class InternationalStringTypeBuilderWrapper<PARENT> extends InternationalStringTypeBuilder<InternationalStringTypeBuilderWrapper<PARENT>> {
		private PARENT parent;
		private RegistryObjectType registryObject;

		public InternationalStringTypeBuilderWrapper<PARENT> addInternationalString() {
			this.registryObject.setName(this.createElement());
			return this;
		}

		public PARENT addInternationalStringEnd() {
			this.addInternationalString();
			return this.parent;
		}
	}
}
