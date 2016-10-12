package com.geom.database;
import java.sql.Connection;

/**
 * This class implements the CRUD operations.
 * It is an abstract generic class inherited by FigureDAO, LengthDAO and Point2DDAO
 * @author Dasha
 * @param <T>
 */
public abstract class DAO<T>{
  /**
   * Connection to be set	
   */
  protected Connection connect = null;
  /**
   * Creates and Sets the connection to the database 
   * @param conn the connection to the database
   * @see com.geom.database.GeomAppConnection.java
   */
  public DAO(Connection conn){
    this.connect = conn;
  }
  /**
   * Creates an object the database
   * @param obj object to create in the database
   * @return
   */
  public abstract boolean create(T obj);
  /**
   * Deletes an object in the database
   * @param obj object to be deleted
   * @return true if the creation was successful 
   */
  public abstract boolean delete(T obj);
 /**
  * Updates an object in the database
  * @param obj object to be updated
  * @return true if the object was indeed deleted
  */
  public abstract boolean update(T obj);
  /**
   * Retrieves an object given its id
   * @param id key of the object to be retrieved
   * @return true if the update is successful
   */
  public abstract T find(int id);
  /**
   * Fetch all the data from the database
   */
  public abstract void afficher();
  /**
   * Saves an object the database
   * @param obj
   * @return true if the object is successfully saved
   */
  public abstract boolean save(T obj);
  /**
   * 
   * @return
   */
  public abstract String [][]sortir ();
}