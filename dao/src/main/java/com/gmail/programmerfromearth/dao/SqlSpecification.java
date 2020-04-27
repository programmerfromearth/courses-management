package com.gmail.programmerfromearth.dao;

/**
 * Interface to declare custom sql queries
 */
public interface SqlSpecification {

    /**
     * Get custom sql
     *
     * @return sql query as {@link String}
     */
    String getSql();
}
