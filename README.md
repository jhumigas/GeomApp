# GeomApp

A Java application to draw basic geometric shapes. Using the interface, the user is able to precise points coordinates and the app will get the distance, surface and type of figure.

This project was coded using Eclispe IDE. In order to run the source code, open with eclipse and run src/Main.java. 

## Overview of the architecture

No framework was used to build this project, but the MVC architecture was obviously implemented. There three main lays corresponding to :
* Persistence
* Business
* Presentation.

### Persistence 

Perform create, read, update and delete [CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete). This layer is divided into multiple classes.
Several packages are used to implement this layer : 
* Database : implements the basic layer to perform interact with the database


### Business 

All logic which is tied to the GeomApp is located in this layer.
The packages to refer to are :
* **model** package which implements basic types of geometric shapes (Rectangular, Trapezoid, Square, Rhombus ,etc)

### Presentation 

The presentation layer is in charge of the presentation. It is responsible of the user interface and shows information to the user.

* **MVCModel** contains the models used by the views, and links to the business layer
* **MVCviews** implements the User Interface. It relies on the Swing Library
* **MVCController** links the interface to the models 

## Database

This apps runs on a MySQL database. 
You can set the connection in GeomAppConnection.java by providing url, username, and password.
Here the name of the database is FiguresGeometriques and you can seed is using the .sql file
The Relational model is described here : 

* **Figure** :  A figure is a geometric shape defined by 
  * id : Primary key
  * type : Type of the figure (e.g Trapezoid)
  * perimeter  : value of the perimeter
  * surface : value of the Surface area
* **Longueur** : A length or a distance between two points
  * id : Primary key
  * valeur : Value of the length
  * id_figure : id of the figure to with the corresponding segment belongs to
  * num_longueur : Number of the segment
* **Point**
  * id : Primary key 
  * x : absissa of the point (int)
  * y : ordinate of the point (int)
  * id_figure : id of the figure to which the figure belongs too (int)
  * num_point : Number of the point (int)
