Batteries for Java, in complement of the standard library

Java >= 17

#Building

This project uses Apache Maven (`mvn`).

```
mvn test
mvn install
mvn source:jar javadoc:jar deploy
...
```

##Source code generation

The (pseudo-)variadic Java classes in this project (Tuples and Functions / Methods) are generated from Python scripts that are located in directory `python-generators`. `_util.py` is a helping module with project constants (paths, etc).

To run them all at once, run script `gen.py` at the root of the project.

```
python gen.py
```
