# ggutils

Java 1.8 required.

This project contains classes that are useful utility classes for use in
other libraries. 

## logging

The logging package provides a stub logging service for use within a library 
package or set of packages.  Use the default logger in your packages and testing
code to stdout, or inject an alternate implementation to bridge logging statements
in the library to any logging system at runtime.  This includes a high performance 
API to delay construction of log messages until after a log level has been checked.

## objectpool

The objectpool package provides an implementation of an object pool with initial 
and maximum sizes.  Pool objects are wrapped in a class that notifies the pool 
on return of an object.
