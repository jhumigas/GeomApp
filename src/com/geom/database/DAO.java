package com.geom.database;
import java.sql.Connection;

/**
 * This class implements the CRUD operations.
 * It is an abstract generic class inherited by FigureDAO, LengthDAO and Point2DDAO
 * @author Dasha
 * @param <T>
 */
public abstract class DAO<T> implements DAOInterface<T>{
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
}