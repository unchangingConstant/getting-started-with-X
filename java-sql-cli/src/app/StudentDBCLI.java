package app;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.cli.StudentDBView;
import app.dao.StudentDBAccess;
import app.model.StudentDBModel;

/**
 * Builds the app
 */
public class StudentDBCLI {
    /**
     * Builds SQLCLI application
     */
    public static void main(String[] args) {
        System.out.println("This application will succeed to open a SQLite database!");

        // checks that first argument corresponds to valid student tracker database

        StudentDBAccess dao = new StudentDBAccess(args[0]);
        StudentDBModel svc = new StudentDBModel(dao);
        StudentDBView view = new StudentDBView(svc, System.out, System.in);

        view.run();
    }

    public static void setupDB(String dbpath) {
        // checks if database exists first
        String cwd = System.getProperty("user.dir");
        File folder = new File(cwd);
        FilenameFilter filter = (dir, name) -> name.equals(dbpath); // huh? figure out how this works
        String[] files = folder.list(filter);

        // validates database schema
        boolean validDB;
        try {
            Connection conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", dbpath));
            validDB = validateSchema(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // if not valid, creates new db with valid schema TODO
        if (validDB) {
            return;
        }

    }

    public static boolean validateSchema(Connection conn) {

        return false;
    }

}
