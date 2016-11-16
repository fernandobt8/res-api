package br.ufsc.bridge.res.dab.write.base;

import java.util.LinkedList;
import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class ParentArquetypeWrapper<PARENT extends ParentArquetypeWrapper> extends ArquetypeWrapper<PARENT> {

	protected List<ArquetypeWrapper<?>> childs = new LinkedList<>();

	public ParentArquetypeWrapper(PARENT parent) {
		super(parent);
	}

	public void addChild(ArquetypeWrapper<?> child) {
		this.childs.add(child);
	}

	@Override
	public String getValue() {
		StringBuilder sb = new StringBuilder();
		for (ArquetypeWrapper<?> child : this.childs) {
			sb.append(child.getXmlContent());
		}
		return sb.toString();
	}

}
