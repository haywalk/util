# Hayden's Utility Library

[![Java CI with Maven](https://github.com/haywalk/util/actions/workflows/maven.yml/badge.svg)](https://github.com/haywalk/util/actions/workflows/maven.yml)

This is a utility library for Java programs, that I'm writing for practice and fun.

So far, the library includes:

- Collections (`ca.haywalk.util.collection`)
  - List (interface and `ArrayList`)
  - Stack (interface and `ArrayStack`)
  - Queue (interface and `LinkedQueue`)
- String utilities (`ca.haywalk.util.string`)
  - `StringConcatenator` (like `java.lang.StringBuilder`)
  
I'd like to add a set, a priority queue, array utilities, and maybe some math tools.

To use the library, put the `jar` file wherever you keep your libraries, and import `ca.haywalk.util.[class/subpackage]`.
