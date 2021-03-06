README
======

This project contains the code for the homework assignment 4 of the CS333
Computer Graphics class. In the rest of the document, the structure of the
project will be described

**NOTICE: An HTML version of this document can be viewed on github at the
following url:
[https://github.com/yiq/raytracer](https://github.com/yiq/raytracer)**


Application Design
------------------

The application is developed with a Model/View/Controller paradigm.

### Model

These classes describes objects and their properties, such as a sphere along
with its center location and radius, a material with its color and shininess, a
ray with the two points it passes through, etc... These classes should not have
any knowledge how they are being rendered, or how they are related with other
objects in the scene

### Controller

These classes coordinate the model classes which collectively defines the state
of the application. Its responsibility would include managing all the objects
in a scene, deciding how an object is rendered, etc... It should not have any
knowledge regarding the window system with which the application is presented.

### View  

These classes provide functionalities to deal with the presentation layer of
the application. They should be responsible for managing windows, widgets,
etc... Minimal knowledge should be present in these classes regarding the
scene, or objects, that are being drawn.

In this application, the model classes are the geometry objects, tracing rays,
or any mathematical entities (e.g. a generic vector) used to construct the
scene; Controller classes are the scenes being rendered, which would implement
the actual ray tracing technique; View classes are the driver class that,
originally derived from the gasket demo, handles opengl events


Package Structure
-----------------

Everything is contained in the umbrella package "teamk.hw4". The base package
contains the entry point of the application 'TKGasketDriver'. All the classes
are prefixed with 'TK', which stands for 'TeamK', to prevent namespace
collision. Also, any package suffixed with .tests contains unity test cases
for classes found in their parent package.

- teamk.hw4.controller  
  Contains the scene controllers.
  
- teamk.hw4.utils.math  
  Contains utility classes for mathematics.
 
- teamk.hw4.model  
  Contains all the model classes. 
  
- teamk.hw4.model.geometry  
  Contains basic geometries supported by the project. 

- teamk.hw4.model.material  
  Contains material classes that defines the color of the object it attached to

- teamk.hw4.model.uvmapper  
  Contains classes that transforms a 3d point in scene space into 2d point in
  texture space


Requirements
------------

- Java >= 1.6  
- JoGL >= 2.0  
- JUint 4     (only for running test cases)


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

### Views

#### TKGasketDriver

This class is adopted from the gasket demo introduced early in the class. It
contains mostly boiler plate codes for handling OpenGL drawing events. Since it
proxy all the drawing and updating logics to the actual scene it renders,
minimal modifications need to be done to it. 

### Controllers

#### TKScene

The abstract controller class that manages a drawable scene. It defines two
essential APIs, render() and updateAnimation() for the actual drawing logic and
animation states updating. It also provides a default implementation of the
KeyEventListener interface for handling keyboard events. 

#### TKRayTraceScene

The concrete class that renders the scene needed for homework 4. It will be
responsible for creating the objects in the scene, setting the light location,
perform the actual ray tracing, and drawing out the pixels resulted from the
ray tracing.

### Model

#### TKRay

A line in 3d space. In this project it will be representing the tracing rays.

#### TKITraceable

An interface with method needed for all ray traceable objects. 

#### TKVector3

Encapsulation of 3d vector arithmetics.

#### TKIParametricEquation

An interface for any object to implement that represents a set of parametric
equation. This allows such equations to be returned, almost like a higher order
function, in the form of objects, and evaluated at any given parameter 't'.

#### Geometries

##### TKAbstractGeometryObject

This abstract base class provides functions to set the material and uv mapper.
They together will be able to answer the following question: At any given point
on the surface of an object, what type of material (color / mirror) it is and 
what the color values are (in rgba)

#### UV Mappers

##### TKSphereLongLatUVMapper

A uv mapper that will transform a point on the surface of a sphere from 3d 
coordinate system to 2d longitude / latitude system. The longitude ranges from
0 to 360, and the latitude ranges from -90 (south pole) to +90 (north pole)

#### Materials

##### TKSimpleColorMaterial and TKSimpleMirrorMaterial

These two materials return the same value at all uv points. The first one 
returns a color, whereas the second one report MIRROR as the material type

##### TKImageTextureMaterial

This class, upon creation, opens an image file and read its content into
memory. All the subsequent queries about color will be answered based on values
in the image file. The UV coordinate dimension will needs to be configured 
before it can be reliably used. See the javadoc of 
TKImageTextureMaterial::setUVDimension for details
