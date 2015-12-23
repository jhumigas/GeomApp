# GeomApp

A Java application to draw basic geometric shapes. Using the interface, the user is able to precise points coordinates and the app will get the distance, surface and type of figure.

This project was coded using Eclispe IDE. In order to run the source code, open with eclipse and run src/Main.java. 

## Overview of the architecture

No framework was used to build, but the MVC logic was obviously implemented. There three main lays corresponding to :
* Persistence
* Business
* Presentation.

### Persistence 

Perform create, read, update and delete [CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete). This layer is divided into multiple classes.

### Business 

All logic which is tied to the GeomApp is located in this layer.

### Presentation 

The presentation layer is in chagre of the presentation. It si responsible of the user interface and shows information to the user.
