package com.geom.database;

/**
 * Collection of the CRUD methods performed against the database
 * @author Dasha
 */
public interface DAOInterface<T> {

    boolean create(T obj);
    /**
     * Deletes an object in the database
     * @param obj object to be deleted
     * @return true if the creation was successful
     */
    boolean delete(T obj);
    /**
     * Updates an object in the database
     * @param obj object to be updated
     * @return true if the object was indeed deleted
     */
    boolean update(T obj);
    /**
     * Retrieves an object given its id
     * @param id key of the object to be retrieved
     * @return true if the update is successful
     */
    T find(int id);
    /**
     * Fetch all the data from the database
     */
    void show();
    /**
     * Saves an object the database
     * @param obj
     * @return true if the object is successfully saved
     */
    boolean save(T obj);
    boolean save(T obj, int id_figure, int num);

}
