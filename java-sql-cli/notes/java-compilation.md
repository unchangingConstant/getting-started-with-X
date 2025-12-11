## Java Packages and how to import them

**TODO Rewrite this section. Read up on single-file mode versus default for Java 11+. Look at "Java Documentation Accuracy Evaluation and Corrections" Deepseek chat for revision guidance**

At runtime, when the JVM executes a program with `java <filepath>`, it needs to find all of the imports found within the given `.java` file. To do this, it needs to know where to look. (NOTE: `java` command does NOT compile anything)
By default, at runtime the JVM looks in the current working directory for imports. Additionally, the JVM can only see `.class` files when looking for imported classes. So, say you have the following file structure:

```
project
-   helpers
    -   Helper.java
    HelloWorld.java
```

Say `HelloWorld.java` imports `helpers.Helper` and you run `java HelloWorld.java` in the `project` directory. `Helper` would fail to import because it is not a class file and because it's not in the `project` directory. So, how do you create `.class` files you can import from other directories?

## A simple guide to `javac`

The `javac` command can compile your `.java` files into importable `.class` files. Simply type `javac <javafilepath>` and it will create a `.class` file in a directory decided by the current working directory and package name. For example, if the package declaration in `Helper.java` file is `package app.tools;` and the current working directory is `project`, it will create a directory `project\app\tools` and place `Helper.class` inside of it.

- **`-d` parameter:** `-d` allows you to specify a destination for all of your compiled `.class` files! The argument passed to `-d` will replace your current working directory for the purposes placing your generated `.class` file somewhere.

- **`-cp` parameter:** You may pass an argument to the `-cp` (or `-classpath`) parameter when using either `java` or `javac`. (Though I will not cover its usage for `javac`) This tells the JVM where to look for compiled packages at runtime.

Here's an example of how you could compile a project. Say you have the following project file structure:

```
src
-   app
    -   tools
        -   Wrench.java
    App.java
target
```

The package declaration in `Wrench.java` is `package app.tools;`. The package declaration for `App.java` is `package app;` and it imports the `Wrench` class with `import app.tools.Wrench;` We'd like to compile the entire project into `target`.

From the `src` directory, you can compile it all with `javac -d target app/App.java`. Here's a walkthrough of what this command will do:

- As it begins to compile `App.java`, it will note that it's in the `app` package. Since the target directory was set to `target`, it knows that the `App.class` file should to the `target\app` directory when it finishes compiling.
- It runs into the `import app.tools.Wrench` package. Since `javac` was run from the `src` directory, it searches in `src\app\tools` for a `Wrench.java` file to compile.
- The compiler notes that `Wrench.java` file is in the `app.tools` package. It compiles it into a `Wrench.class` file and puts it into the `target\app\tools` directory.
- The compiler has resolved the import, and now finishes compiling the `App.java` file and places the compiled `App.class` file into the `target\app` directory.

After this command is run, the new file structure should look like this:

```
src
-   app
    -   tools
        -   Wrench.java
    App.java
target
-   app
    -   tools
        -   Wrench.class
    App.class
```

Now, we run it!

## Running your project

You've compiled all of your files to `.class` files, great! How can you run your project from here? Take the file directory from the last example.

```
target
-   app
    -   tools
        -   Wrench.class
    App.class
```

We've discussed how the `java` command can be used to run `.java` files. But they can also be used to run `.class` files! Simply navigate to the directory above the `.class` you want to run and refer to it by its package name.

In this example, simply run `java app.App` from the `target` directory.
