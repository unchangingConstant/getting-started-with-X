package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Data access object handles all logic for SQL data management
 */
public class StudentDBAccess {

    private final String dbpath;

    public StudentDBAccess(String dbpath) {
        this.dbpath = String.format("jdbc:sqlite:%s", dbpath);
        try (Connection conn = DriverManager.getConnection(this.dbpath)) {
            // Validates that database works and stuff
            // should check that required tables exist and stuff

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertStudent(String firstName, String middleName, String lastName) {
        Connection conn = connection();
        Statement stmt = statement(conn);
        try {
            stmt.addBatch(
                    String.format("INSERT INTO students (firstName, middleName, lastName) VALUES ('%s', %s, '%s');",
                            firstName,
                            middleName != null ? "'" + middleName + "'" : "NULL",
                            lastName));
            stmt.executeBatch();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // refactor to return Object[] list
    public List<String> selectStudents() {
        Connection conn = connection();
        Statement stmt = statement(conn);
        ArrayList<String> students = new ArrayList<>();

        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                String middleName = rs.getString(3);
                String student = String.format("%d %s %s%s",
                        rs.getInt(1),
                        rs.getString(2),
                        middleName != null ? middleName + " " : "",
                        rs.getString(4));
                students.add(student);
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public void deleteStudent(String firstName, String middleName, String lastName) {
        Connection conn = connection();
        int rows_affected;
        try {
            /*
             * first, creates set with id, row_count, where id is the id of the student and
             * row_count is the number of times a student with a name identical to it
             * occurs. Call it 'matching_rows'
             * 
             * Ex: Say we have two Ethans with ids one and two
             * matching_rows would be:
             * 1, 2
             * 2, 2
             * 
             * Seems a bit heavy. See if you can shorten it?
             */
            PreparedStatement stmt = conn.prepareStatement("""
                    WITH matching_rows AS (
                        SELECT studentId, COUNT(*) OVER() as row_count
                        FROM students
                        WHERE firstName = ? AND (middleName = ? OR (middleName IS NULL AND ? IS NULL)) AND lastName = ?
                    )
                    DELETE FROM students
                    WHERE studentId IN (
                        SELECT studentId
                        FROM matching_rows
                        WHERE row_count = 1
                    );
                    """);
            stmt.setString(1, firstName);
            stmt.setString(2, middleName);
            stmt.setString(3, middleName);
            stmt.setString(4, lastName);
            rows_affected = stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (rows_affected == 0) {
            throw new NoSuchElementException();
        }
    }

    private Connection connection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(dbpath);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

    // You don't need this
    private Statement statement(Connection conn) {
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return stmt;
    }
}
