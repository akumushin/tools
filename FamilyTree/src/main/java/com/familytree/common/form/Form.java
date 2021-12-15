package com.familytree.common.form;

public interface Form<T, F> {
	public T toEntity();
	public T toEntity(T entity);
	public F toFilter();
}
