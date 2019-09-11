package br.ufsc.bridge.res.dab.writer.json;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class BaseJsonBuilder<T extends BaseJsonBuilder<?>> {

	protected T parent;

	protected DocumentContext document;

	protected List<BaseJsonBuilder<?>> children;

	public BaseJsonBuilder(T parent, String fileName) {
		this.document = JsonPath.parse(this.getClass().getClassLoader().getResourceAsStream("json.parties" + File.separator + fileName));
		this.children = new ArrayList<>();
		this.parent = parent;
		if (parent != null) {
			parent.children.add(this);
		}

	}

	public BaseJsonBuilder(String fileName) {
		this(null, fileName);
	}

	public String getJsonString() {
		for (BaseJsonBuilder<?> child : this.children) {
			Object childJson = child.json();
			if (childJson != null) {
				this.document.add(this.childJsonPath(), childJson);
			}
		}
		return this.document.jsonString();
	}

	public Object json() {
		return this.document.json();
	}

	protected String childJsonPath() {
		return null;
	}

	public T close() {
		return this.parent;
	}

}
