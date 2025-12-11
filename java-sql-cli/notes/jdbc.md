## Java JDBC (Java Database Connectivity)

A Java API that allows access to any tabular data.

### API Components

JDBC consists of 4 components. For now, we'll cover two:

#### JDBC Driver Manager

Defines objects which connect Java programs to a JDBC driver. Drivers allow a connection to occur between some tabular database and your program. For example, a driver could connect to a MySQL database or an SQLite database or some other remote/local database.

#### JDBC API

Provides the tools to execute SQL statements, retrieve results, and propagate changes back to an underlying data source.

## Connecting to a database:

You can connect to a database via a `DriverManager` object or a `DataSource` object. Apparently, `DataSource` objects are better, but we'll learn with `DriverManager` objects for now.

You can create a connection to a database using a `DriverManager` like so:

```java
private Connection connection() {
    Connection conn = null;
    String dbpath = "jdbc:sqlite:path/to/database.db"

    try {
        conn = DriverManager.getConnection(dbpath);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return conn;
}
```

The first part of the dbpath string (`jdbc:sqlite`) specifies the driver you'd like to use. The second part (`path/to/databse.db`) specifies the path to the database. The path could represent something else depending on the driver used, like a URL instead of a file path. In this example, the driver accesses a local SQLite database so a file path is sufficient.

Next, I'll go over Statements and ResultSets. These allow you to access data after a connection is established. IMPORTANT NOTE: These are connected to the Connection object that made them. If the connection object closes, the associated objects close as well.

## Basic SQL

SQL is declarative. Meaning you tell the database what you want, not how to get it.

Here's some basics on SQL syntax. SQL has what's called an order of execution. SQL syntax is not executed sequentially, but rather certain keywords are given priority over others and run first. Here's what the hierarchy looks like:

```
1. FROM          ← Identifies source tables
2. WHERE         ← Filters rows
3. GROUP BY      ← Groups remaining rows
4. Aggregates    ← Regular aggregate functions (COUNT, SUM, AVG, etc.)
5. HAVING        ← Filters groups (can use aggregate results)
6. WINDOW        ← Window functions (COUNT() OVER(), ROW_NUMBER(), etc.)
7. SELECT        ← Projects columns
8. ORDER BY      ← Sorts final result
9. LIMIT/OFFSET  ← Limits final result
```

PRAGMA table_info(table_name);

SELECT \* FROM Products
ORDER BY Price;

SELECT \* FROM Customers
WHERE NOT Country = 'Spain';

INSERT INTO table_name (column1, column2, column3, ...)
VALUES (value1, value2, value3, ...);

UPDATE Customers
SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
WHERE CustomerID = 1;
