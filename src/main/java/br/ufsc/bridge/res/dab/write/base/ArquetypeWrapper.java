package br.ufsc.bridge.res.dab.write.base;

public abstract class ArquetypeWrapper {

	protected abstract String openTags();

	protected abstract String closeTags();

	protected abstract String getContent();

	public String getXmlContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.openTags());
		sb.append(this.getContent());
		sb.append(this.closeTags());
		return sb.toString();
	}

}
