import Database.SchemaDB;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.util.Scanner;

public abstract class Usuario {

    Statement statementCreate = null;

    String connectionURL = "jdbc:mysql://127.0.0.1/rentacar";
    Connection connection;

    {
        try {
            connection = connection = DriverManager.getConnection(connectionURL
                    , "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    Scanner teclado = new Scanner(System.in);
    private String nombre, apellido, email, contraseña;

    public Usuario(String nombre, String apellido, String email, String contraseña) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contraseña = contraseña;
    }

    public Usuario() {
    }

    public void verCochesAlquilados() {
        System.out.println("**** Lista de Coches Alquilados***");
        String querySelectPat = "SELECT * FROM %s WHERE %s='%s'";
        Statement statementSelect = null;
        try {
            statementSelect = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet resultadoQuery = statementSelect.executeQuery(String.format(querySelectPat, SchemaDB.DB_TAB_COCHEALQUILER, SchemaDB.COL_ALQUILADO_TAB_COCHESALQUILER, "1"));
            while (resultadoQuery.next()) {
                System.out.println("--------");
                String Marca = resultadoQuery.getString(SchemaDB.COL_MARCA_TAB_COCHESALQUILER);
                String Modelo = resultadoQuery.getString(SchemaDB.COL_MODELO_TAB_COCHESALQUILER);
                String Matricula = resultadoQuery.getString(SchemaDB.COL_MATRICULA_TAB_COCHESALQUILER);
                String PrecioDia = resultadoQuery.getString(SchemaDB.COL_PRECIODIA_TAB_COCHESALQUILER);

                System.out.println("Marca:" + Marca);
                System.out.println("Modelo: " + Modelo);
                System.out.println("Matricula: " + Matricula);
                System.out.println("El precio por dia es : " + PrecioDia);


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void verCochesEnVenta() {
        System.out.println("**** Lista de Coches Disponibles para la venta***");
        String querySelectPat = "SELECT * FROM %s WHERE %s='%s'";
        Statement statementSelect = null;
        try {
            statementSelect = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet resultadoQuery = statementSelect.executeQuery(String.format(querySelectPat, SchemaDB.DB_TAB_COCHEVENTA, SchemaDB.COL_VENDIDO_TAB_COCHEVENTA, "0"));
            while (resultadoQuery.next()) {
                System.out.println("--------");
                String Marca = resultadoQuery.getString(SchemaDB.COL_MARCA_TAB_COCHEVENTA);
                String Modelo = resultadoQuery.getString(SchemaDB.COL_MODELO_TAB_COCHEVENTA);
                String Matricula = resultadoQuery.getString(SchemaDB.COL_MATRICULA_TAB_COCHEVENTA);
                String PrecioVenta = resultadoQuery.getString(SchemaDB.COL_PRECIVENTA_TAB_COCHEVENTA);

                System.out.println("Marca:" + Marca);
                System.out.println("Modelo: " + Modelo);
                System.out.println("Matricula: " + Matricula);
                System.out.println("El precio de Venta: " + PrecioVenta);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void verCochesDisponiblesAlquiler() {
        System.out.println("**** Lista de Coches Disponibles Para Alquilar***");
       /* String querySelectPat = "SELECT * FROM %s WHERE %s='%s'";
        Statement statementSelect = null;
        try {
            statementSelect = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet resultadoQuery = statementSelect.executeQuery(String.format(querySelectPat, SchemaDB.DB_TAB_COCHEALQUILER, SchemaDB.COL_ALQUILADO_TAB_COCHESALQUILER, "0"));
            while (resultadoQuery.next()) {
                System.out.println("--------");
                String Marca = resultadoQuery.getString(SchemaDB.COL_MARCA_TAB_COCHESALQUILER);
                String Modelo = resultadoQuery.getString(SchemaDB.COL_MODELO_TAB_COCHESALQUILER);
                String Matricula = resultadoQuery.getString(SchemaDB.COL_MATRICULA_TAB_COCHESALQUILER);
                String PrecioDia = resultadoQuery.getString(SchemaDB.COL_PRECIODIA_TAB_COCHESALQUILER);

                System.out.println("Marca:" + Marca);
                System.out.println("Modelo: " + Modelo);
                System.out.println("Matricula: " + Matricula);
                System.out.println("El precio por dia es : " + PrecioDia);


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        String url="https://vpic.nhtsa.dot.gov/api/vehicles/getallmakes?format=json";

        InputStream input = null;
        try {
            input= new URL(url).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bis = new BufferedReader(new InputStreamReader(input));
        String respuesta = null;
        try {
            respuesta = bis.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonGeneral = new JSONObject(respuesta);
        JSONArray arrayCoches = jsonGeneral.getJSONArray("Results");
        for (int i = 0; i < 12; i++) {

            JSONObject coche=arrayCoches.getJSONObject(i);
            String nombre= coche.getString("Make_Name");
            System.out.println(nombre);

        }
    }



    public void verTodosAlquilables() {
        System.out.println("**** Lista de Coches Alquilados***");
        String querySelectPat = "SELECT * FROM %s ";
        Statement statementSelect = null;
        try {
            statementSelect = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet resultadoQuery = statementSelect.executeQuery(String.format(querySelectPat, SchemaDB.DB_TAB_COCHEALQUILER));
            while (resultadoQuery.next()) {
                System.out.println("--------");
                String Marca = resultadoQuery.getString(SchemaDB.COL_MARCA_TAB_COCHESALQUILER);
                String Modelo = resultadoQuery.getString(SchemaDB.COL_MODELO_TAB_COCHESALQUILER);
                String Matricula = resultadoQuery.getString(SchemaDB.COL_MATRICULA_TAB_COCHESALQUILER);
                String PrecioDia = resultadoQuery.getString(SchemaDB.COL_PRECIODIA_TAB_COCHESALQUILER);

                System.out.println("Marca:" + Marca);
                System.out.println("Modelo: " + Modelo);
                System.out.println("Matricula: " + Matricula);
                System.out.println("El precio por dia es : " + PrecioDia);
                if (resultadoQuery.getBoolean(SchemaDB.COL_ALQUILADO_TAB_COCHESALQUILER)) {
                    System.out.println("El coche se encuentra alquilado por un cliente");
                } else {
                    System.out.println("El coche se encuentras disponible para Alquilarlo");
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void verDatos() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("Apellido: " + getApellido());
        System.out.println("Email: " + getEmail());


    }
}
