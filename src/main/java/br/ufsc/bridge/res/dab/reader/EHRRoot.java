package br.ufsc.bridge.res.dab.reader;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import br.ufsc.bridge.res.util.ReflectionHelper;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;

@RequiredArgsConstructor
@Slf4j
public class EHRRoot<T> {

	@NonNull
	private String path;

	@NonNull
	private Class<T> objClass;

	private List<EHRSelect> fields = new ArrayList<>();

	public EHRSelect select(String node) {
		EHRSelect<T> select = new EHRSelect<>(node, this);
		this.fields.add(select);
		return select;
	}

	public T build(String json) {
		DocumentContext context = JsonPath.parse(json);
		try {
			T obj = ReflectionHelper.newInstance(this.objClass);
			for(EHRSelect select : this.fields) {
				Object value;
				String node = this.path + "." + select.path;
				if(select.filters.isEmpty()) {
					value = context.read(node);
				} else {
					value = context.read(node, Filter.filter(select.filters));
				}
				ReflectionHelper.executeSetter(select.property, value, this.objClass, obj );
			}
			return obj;
		} catch (BindException e) {
			log.error("", e); //FIXME
		}
		return null;
	}
}
