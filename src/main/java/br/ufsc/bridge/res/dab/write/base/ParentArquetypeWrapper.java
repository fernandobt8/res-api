package br.ufsc.bridge.res.dab.write.base;

import java.util.ArrayList;
import java.util.List;

public abstract class ParentArquetypeWrapper extends ArquetypeWrapper {

	protected List<ArquetypeWrapper> childs = new ArrayList<>();

	@Override
	protected String getContent() {
		StringBuilder sb = new StringBuilder();
		for (ArquetypeWrapper child : this.childs) {
			sb.append(child.getXmlContent());
		}
		return sb.toString();
	}

}
