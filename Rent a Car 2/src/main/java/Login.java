import Database.SchemaDB;

import java.sql.*;
import java.sql.DriverManager;
import java.util.Scanner;

public class Login {
    public Login() {
    }

    Scanner teclado=new Scanner(System.in);


    public boolean logingAdmin(){
        Connection connection = null;
        String connectionURL = "jdbc:mysql://127.0.0.1/rentacar";
        try {
            connection = DriverManager.getConnection(connectionURL
                    , "root", "");
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            System.out.println("Fallo en la conexion con la base de datos");
        }

        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce usuario");
        String usuario = teclado.next();
        System.out.println("Introduce Contraseña");
        String contraseña = teclado.next();
        try {

            Statement statement = connection.createStatement();
            String selectPat = "SELECT * FROM %s WHERE %s='%s' AND %s='%s'";

            ResultSet resultSet = statement.executeQuery(String.format(selectPat, SchemaDB.DB_TAB_ADMIN,SchemaDB.COL_USUARIO_ADMIN,usuario,SchemaDB.COL_CONTRASEÑA_ADMIN,contraseña));
            if (resultSet.next()){
                System.out.println("login correcto");
                String correoLogin = resultSet.getString(SchemaDB.COL_USUARIO_ADMIN);
                String passLogin = resultSet.getString(SchemaDB.COL_CONTRASEÑA_ADMIN);

                return true;
            }else {
                System.out.println("login incorrecto");
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }


    }
    public boolean logingTrabjador(){
        Connection connection = null;
        String connectionURL = "jdbc:mysql://127.0.0.1/rentacar";
        try {
            connection = DriverManager.getConnection(connectionURL
                    , "root", "");
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            System.out.println("Fallo en la conexion con la base de datos");
        }

        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce tu Id de trabajador");
        String id = teclado.next();
        System.out.println("Introduce Contraseña");
        String contraseña = teclado.next();
        try {

            Statement statement = connection.createStatement();
            String selectPat = "SELECT * FROM %s WHERE %s='%s' AND %s='%s'";

            ResultSet resultSet = statement.executeQuery(String.format(selectPat, SchemaDB.DB_TAB_TRABAJADORES,SchemaDB.COL_ID_TAB_TRABAJADORES,id,SchemaDB.COL_CONTRASEÑA_TAB_TRABAJADORES,contraseña));
            if (resultSet.next()){
                System.out.println("login correcto");
                String correoLogin = resultSet.getString(SchemaDB.COL_ID_TAB_TRABAJADORES);
                String passLogin = resultSet.getString(SchemaDB.COL_CONTRASEÑA_TAB_TRABAJADORES);

                return true;
            }else {
                System.out.println("login incorrecto");
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }


    }
}
