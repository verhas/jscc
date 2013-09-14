jscc
====

A very simple Java source compiler code based on the javac provided by the
JDK. The library can be used to compile sources that do not depend on any
other source code (but can depend on classes already loaded) and 
thus can be compiled alone. The class is compiled from the
code given in a `String`. The library generates the 
bytecode in memory and loads
the generated class, so that it can directly be instantiated and invoked
after the compiler returns.

Usage
-----

```
// create a new compiler
Compiler compiler = new Compiler();
// set optionally the classloader
compiler.setClassLoader(classLoader);
// compile and load the class providing source code and
// fully qualified class name
Class<?> newClass = compiler.compile(sourceCode, className);
```