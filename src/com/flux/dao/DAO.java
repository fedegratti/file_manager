package com.flux.dao;

import java.util.List;

public interface DAO<T> {
	public void save(T domain);

	public List<T> list();

	public T getById(Long id);

	public void delete(Long id);
}
