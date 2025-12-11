# java-sql-cli

I practice creating a CLI for a Java SQL database

## Contributing:

All you need to develop for this project is Java21 and SQLite installed on your machine. To run this project, follow the instructions below:

### Windows

Run the `build.bat` to run the project

### Linux

Any time you want to run this project, run the following series of commands starting from the `src` directory

- `javac -d ..\target app\SQLCLI.java`
- `cd ..\target`
- `java -cp ".;..\lib\*" app.SQLCLI ..\database.db`

## Project Structure

The source code is found in the `src` directory. All packages for the project begin at `app`. The `target` directory is where all files should be compiled to. `target\lib` is where all 3rd-party `.jar` files will be stored.

The series of commands specified in the "contributing" section of the `README.md` essentially compile the project to the `target` directory then runs the project from the `target` directory. Additionally, both `lib` and `target` are set as the class path.

```
java-sql-cli
-   lib
-   src\app
    -   cli
    -   dao
    -   service
    SQLCLI.java
-   target
    -   app
```

## TODO

Add following functionalities:

- ~~Display all students~~
- ~~Insert a student~~
- ~~Delete a student~~
- Insert a visit
- Delete a visit
- Display all of a student's visits
- Start a visit
- End a visit
- Display all ongoing visits
  - In chronological order (ending soonest)
  - Display student name next to it as well
- Display all students currently in the center
- Display all students enrolled in a certain subject
