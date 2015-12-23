package com.geom.database;
import java.sql.Connection;


public abstract class DAO<T>{
  protected Connection connect = null;
   
  public DAO(Connection conn){
    this.connect = conn;
  }
  public abstract boolean create(T obj);
  //creation de donnees
  public abstract boolean delete(T obj);
 //suppression de donneess
  public abstract boolean update(T obj);
 // mise a jour 
  public abstract T find(int id);
  // recherche de donnees
  public abstract void afficher();
  //afficher les données dans la BD
  public abstract boolean save(T obj);
  //Sauvegarder dans la BD
  public abstract String [][]sortir ();
}