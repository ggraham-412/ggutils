# ggutils

Java 1.8 required.

This project contains classes that are useful utility classes for use in
other libraries, including utility networking classes intended for use in 
pentaho transformation step projects.  

This code is provided for educational purposes only.  No warranty is expressed or implied.  Use at your own risk.

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

## message

This package contains classes for handling messages that may come in network packets.  
Includes methods to create a list of fields, and encode/decode fields in sequence
between a java.nio.ByteBuffer and an Object[].  The API is suggested by the Pentaho 
transformation getRow/putRow API.

## network

This package contains classes for sending/receiving UDP packets.  The UDP receiver 
listens for packets on a localhost port and passes them to an injected message 
handler.  The UDP sender sends packets to a remote or local host at given port.
Both implementations use java.nio.DatagramChannel.
  
