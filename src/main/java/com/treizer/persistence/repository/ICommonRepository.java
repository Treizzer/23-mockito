package com.treizer.persistence.repository;

import java.util.Optional;

public interface ICommonRepository<TEntity> {

    Iterable<TEntity> findAll();

    Optional<TEntity> findById(Long id);

    TEntity save(TEntity palyer);

    void saveVoid(TEntity player);

    void deleteById(Long id);

    void deleteByIdVoid(Long id);

    Long size();

}
