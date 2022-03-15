package com.sinensia.dao.contracts;

import java.util.List;

public interface IDao <T>{
	public Integer save(T instance);
	public List<T> findAll();
	public boolean delete(Integer id);
	public T merge(Integer id);
	public T findById(Integer id);
}
