README
======

This project contains the code for the homework assignment 4 of the CS333 
Computer Graphics class. The the rest of the document, the structure of the
project will be described


Application Design
------------------

The application is developed with a Model/View/Controller paradigm.

* Model: These classes describes objects and their properties, such as a 
         sphere along with its center location and radius, a material with
         its color and shininess, a ray with the two points it passes through,
         etc... These classes should not have any knowledge how they are being
         rendered, or how they are related with other objects in the scene
* Controller: These classes coordinate the model classes which collectively 
              defines the state of the application. Its responsibility would
              include managing all the objects in a scene, deciding how an
              object is rendered, etc... It should not have any knowledge 
              regarding the window system with which the application is
              presented.
* View: These classes provide functionalities to deal with the presentation
        layer of the application. They should be responsible for managing
        windows, widgets, etc... Minimal knowledge should be present in these
        classes regarding the scene, or objects, that are being drawn.
        
In this application, the model classes are the geometry objects, tracing rays,
or any mathematical entities (e.g. a generic vector) used to construct the
scene; Controller classes are the scenes being rendered, which would implement
the actual ray tracing technique; View classes are the driver class that,
originally derived from the gasket demo, handles opengl events


Package Structure
-----------------

Everything is contained in the umbrella package "teamk.hw4". The base package
contains the entry point of the application 'TKGasketDriver'. All the classes
are prefixed with 'TK', which stands for 'TeamK', to prevent namespace collision.

- teamk.hw4.model            Contains all the model classes. 
- teamk.hw4.model.tests      Contains Unit test cases for the model classes.
- teamk.hw4.controller       Contains the scene controllers.
- teamk.hw4.utils.math       Contains utility classes for mathematics.
- teamk.hw4.utils.math.tests Contains unit test cases for mathematics utilities


Requirements
------------

Java >= 1.6
JoGL >= 2.0
JUint 4     (only for running test cases)


Development Notes
-----------------

In order to run the application, jogamp library is required. Since the library
was added as a user library, with each person potentially naming it
differently, I did not put the library inside the .classpath file. Instead one
should configure the library on a per local repository basis. However, make
sure you don't accidentally push your changes to the .classpath file to the
shared repository by performing 'git update-index --assume-unchanged' on it. 
In order for eclipse to show the file, click on the small downward triangle in
the toolbar of the 'Package Explorer' window, click on "Filters...", and 
deselect '.* resources'. Then, right click on the .classpath file, and go to
Team->Advanced->Assume Unchanged. From now on, you can add your version of
jogamp to the project, without affecting other people's build path

The project utilize unit test extensively to ensure code correctness. In order
to run unit test cases, select the project in the Package Explorer, and right
click -> Run As -> JUnit Test (or shortcut Cmd+Alt+X T on mac). If you make
changes to any of the tested classes (usually the model classes), ensuring that
all test cases pass before pushing to the shared repository would be a good
practice.


Description and Usage of some key classes
-----------------------------------------

### TKGasketDriver

This class is adopted from the gasket demo introduced early in the class. It
contains mostly boiler plate codes for handling OpenGL drawing events. Since
it proxy all the drawing and updating logics to the actual scene it renders, 
minimal modifications need to be done to it. 

### TKScene

The abstract controller class that manages a drawable scene. It defines two
essential APIs, render() and updateAnimation() for the actual drawing logic
and animation states updating. It also provides a default implementation of 
the KeyEventListener interface for handling keyboard events. 

### TKRayTraceScene

The concrete class that renders the scene needed for homework 4. It will be
responsible for creating the objects in the scene, setting the light location,
perform the actual ray tracing, and drawing out the pixels resulted from the
ray tracing.

### TKRay

A line in 3d space. In this project it will be representing the tracing rays.

### TKITraceable

An interface with method needed for all ray traceable objects. 

### TKVector3

Encapsulation of 3d vector arithmetics.

### TKIParametricEquation

An interface for any object to implement that represents a set of parametric
equation. This allows such equations to be returned, almost like a higher order
function, in the form of objects, and evaluated at any given parameter 't'.