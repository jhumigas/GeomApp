package com.geom.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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
   * @see com.geom.database.GeomAppConnection
   */
  public DAO(Connection conn){
    this.connect = conn;
  }

  /**
   * Prints a whole table in the console.
   * @param table name of the table, one of {@link DAOFactory#TABLES} (table names cannot be bound as SQL parameters)
   */
  protected void printTable(String table){
    DAOFactory.checkTable(table);
    try (PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM " + table);
         ResultSet resultSet = statement.executeQuery()) {
      ResultSetMetaData metaData = resultSet.getMetaData();
      for(int i = 1; i <= metaData.getColumnCount(); i++)
        System.out.print("\t" + metaData.getColumnName(i).toUpperCase() + "\t *");
      System.out.println("\n**************************************************************************************");
      while(resultSet.next()){
        for(int i = 1; i <= metaData.getColumnCount(); i++)
          System.out.print("\t" + resultSet.getObject(i) + "\t |");
        System.out.println("\n----------------------------------------------------------------------------------");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
