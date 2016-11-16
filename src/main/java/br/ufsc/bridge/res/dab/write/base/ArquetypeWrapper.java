package br.ufsc.bridge.res.dab.write.base;

import lombok.NoArgsConstructor;

import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor
public abstract class ArquetypeWrapper<PARENT extends ParentArquetypeWrapper> {

	protected PARENT parent;

	public ArquetypeWrapper(PARENT parent) {
		this.parent = parent;
		parent.addChild(this);
	}

	protected abstract String openTags();

	public abstract String getValue();

	protected abstract String closeTags();

	public String getXmlContent() {
		if (StringUtils.isNotBlank(this.getValue())) {
			return this.openTags() + this.getValue() + this.closeTags();
		}
		return "";
	}

	public PARENT close() {
		return this.parent;
	}

}
