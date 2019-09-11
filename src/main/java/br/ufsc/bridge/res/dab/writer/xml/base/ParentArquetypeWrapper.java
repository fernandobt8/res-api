package br.ufsc.bridge.res.dab.writer.xml.base;

import java.util.LinkedList;
import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class ParentArquetypeWrapper<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	protected List<ArquetypeWrapper<?>> children = new LinkedList<>();

	public ParentArquetypeWrapper(PARENT parent) {
		super(parent);
	}

	public void addChild(ArquetypeWrapper<?> child) {
		this.children.add(child);
	}

	@Override
	public String getValue() {
		StringBuilder sb = new StringBuilder();
		for (ArquetypeWrapper<?> child : this.children) {
			sb.append(child.getXmlContent());
		}
		return sb.toString();
	}

}
