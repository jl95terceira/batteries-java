Batteries for Java, in complement of the standard library

Java >= 17

#Building

This project is built using Apache Maven (`mvn`).

```
mvn test
mvn install
mvn source:jar javadoc:jar deploy
...
```

##Source code generation

This project contains classes that are generated from Python scripts that are located in directory `python-generators`.
- variadic-like classes
- classes to emulate implementations of generic classes for primitive types
`_util.py` is a helper module with project constants (paths, etc).

To run them all at once, run script `gen.py` at the root of the project.

```
python gen.py
```
