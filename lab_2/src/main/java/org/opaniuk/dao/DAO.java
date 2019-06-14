package org.opaniuk.dao;

import java.util.List;

public interface DAO<T, ID> {
  T save(T t);

  T findById(ID id);

  List<T> findAll();
}
