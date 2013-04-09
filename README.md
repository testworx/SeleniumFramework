# Selenium Framework

## Introduction
This framework is intended to provide a simple way of writing tests in Selenium 2 by providing access to common Selenium features in wrapped functions.

## Overview

### Controls Package
The intention here is to model UI controls and provide access to methods that interact with these controls.  The idea is to offer a sub set of the WebElement API in encapsulated methods to make UI interaction easier.

A number of classes have been created to model common elements.  The intention is for all common types of UI control to be modelled along with more complex custom controls such as calendars etc.  Some of these classes make use of various interface classes.

The interface classes have been created (with more to follow) to allow controls to be modelled based on how we would want to interact with them.  E.g. we might want to click on a particular control.  Therefore we would make use of the **Clickable** interface.  Likewise we might want to read the text of a control.  Therefore we would use the **Readable** interface.

In this way it is hoped that we can quickly create UI controls with standard ways if interaction by plugging in the interfaces we want to use.


### Utilities Package
The intention here is to provide useful functionality that can be used at the "test" level.

**TestHelper** class that facilitates switching browser windows and will shortly facilitate switching frames and DB connectivity.

**BaseTest** class enables instantiation of both local and remote driver instances.  It will shortly include screenshot functionality to take local and remote screenshots and to write them to a directory.

**ObjectMap** class allows WebElement locators to be retrieved from a properties file.  This enables locator information to be stored externally to the tests.  This is based on a recipe from The Selenium Cookbook.


### Tests Package
The code in this package is not intended for production use as it contains test to verify framework functionality.  As new functionality is added to the framework, the tests will be expanded.  The tests can also serve as an example of how each Control can be used as well as the methods they offer.

HTML pages containing controls are included in the "resources" folder within the package.  
 

