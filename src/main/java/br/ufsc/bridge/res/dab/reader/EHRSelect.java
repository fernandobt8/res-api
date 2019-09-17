package br.ufsc.bridge.res.dab.reader;

import java.util.ArrayList;
import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import com.jayway.jsonpath.Criteria;

@RequiredArgsConstructor
public class EHRSelect<T> {

	@NonNull
	String path;

	@NonNull
	private EHRRoot<T> root;

	List<Criteria> filters = new ArrayList<>();

	String property;

	public EHRRoot<T> as(String property) {
		this.property = property;
		return this.root;
	}

	public EHRSelect<T> where(String condition) {
		this.filters.add(Criteria.where(condition));
		return this;
	}
}
