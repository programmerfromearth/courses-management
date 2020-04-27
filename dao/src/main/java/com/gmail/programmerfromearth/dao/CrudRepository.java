package com.gmail.programmerfromearth.dao;

import java.util.List;

/**
 *  Generic CRUD repository
 *
 * @param <T> the domain type the repository manages
 */
public interface CrudRepository<T> {

    /**
     * Add entity to the store
     *
     * @param entity added entity
     * @return an unique id of added entity
     */
    Integer add(T entity);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    Iterable<T> get();

    /**
     * Update current entity
     *
     * @param entity updated entity
     */
    void update(T entity);

    /**
     * Deletes a given entity.
     *
     * @param entity deleted entity
     */
    void delete(T entity);
}
