## Introduction
This framework is intended to provide a simple way of writing tests in Selenium 2 by providing access to common Selenium features in wrapped functions.

## Overview

### [Controls Package](https://github.com/nvonop/SeleniumFramework/wiki/Controls-Package)
This package provides interfaces for interacting with UI controls in a consistent manner.  Additionally it contains a set of standard controls that utilise the interfaces provided. 


### [Utilities Package](https://github.com/nvonop/SeleniumFramework/wiki/Utilities-Package)
The intention here is to provide useful functionality that can be used at the "test" level.

**TestHelper** class that facilitates switching browser windows and will shortly facilitate switching frames and DB connectivity.

**BaseTest** class enables instantiation of both local and remote driver instances.  It will shortly include screenshot functionality to take local and remote screenshots and to write them to a directory.

**ObjectMap** class allows WebElement locators to be retrieved from a properties file.  This enables locator information to be stored externally to the tests.  This is based on a recipe from The Selenium Cookbook.


###[Tests Package](https://github.com/nvonop/SeleniumFramework/wiki/Tests-Package)
The code in this package is not intended for production use as it contains test to verify framework functionality.  As new functionality is added to the framework, the tests will be expanded.  The tests can also serve as an example of how each Control can be used as well as the methods they offer.

HTML pages containing controls are included within the package.  
 
