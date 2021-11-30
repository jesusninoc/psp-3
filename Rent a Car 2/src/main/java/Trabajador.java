import Database.SchemaDB;
import Hilos.Hilo;
import com.mysql.cj.jdbc.StatementImpl;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public final class Trabajador extends Usuario {

    Statement statementCreate = null;
    String connectionURL = "jdbc:mysql://127.0.0.1/rentacar";
    Connection connection;
    Hilo hilo=new Hilo();

    {
        try {
            connection = connection = DriverManager.getConnection(connectionURL
                    , "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private String idTrabajdor;


    public Trabajador(String idTrabajdor, String nombre, String apellido, String email, String contraseña) {

        super(nombre, apellido, email, contraseña);
        this.idTrabajdor = idTrabajdor;

    }

    public Trabajador() {

    }

    public String getIdTrabajdor() {
        return idTrabajdor;
    }

    public void setIdTrabajdor(String idTrabajdor) {
        this.idTrabajdor = idTrabajdor;
    }

    @Override
    public void verDatos() {
        System.out.println("ID del trabajador: " + idTrabajdor);
        super.verDatos();
    }

    @Override
    public void verCochesAlquilados() {
        super.verCochesAlquilados();
    }

    @Override
    public void verCochesEnVenta() {
        super.verCochesEnVenta();
    }

    @Override
    public void verCochesDisponiblesAlquiler() {
        super.verCochesDisponiblesAlquiler();
    }

    @Override
    public void verTodosAlquilables() {
        super.verTodosAlquilables();
    }

    public void agregarCliente() {
        System.out.println("Dime el DNI del cliente");
        String dni = teclado.next();
        System.out.println("Dime el nombre del cliente");
        String nombre = teclado.next();
        System.out.println("Dime el apellido del cliente");
        String apellido = teclado.next();
        System.out.println("Dime el emial del cliente");
        String email = teclado.next();

        String queryCreatePat = "INSERT INTO %s (%s,%s,%s,%s) VALUES ('%s','%s','%s','%s')";
        String queryCreate = String.format(queryCreatePat,
                SchemaDB.DB_TAB_CLIENTES,
                SchemaDB.COL_DNI_TAB_CLIENTES, SchemaDB.COL_NOMBRE_TAB_CLIENTES, SchemaDB.COL_APELLIDOS_TAB_CLIENTES, SchemaDB.COL_EMAIL_TAB_CLIENTES,
                dni, nombre, apellido, email);
        try {
            statementCreate = connection.createStatement();
            statementCreate.execute(queryCreate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean comprobarCocheAlquiler(String matricula){
        boolean encontrado = false;
        Statement statementSelect = null;
        String querySelectPat = "SELECT * FROM %s WHERE %s='%s'";


        try {
            statementSelect = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            try {
                ResultSet resultadoQuery = statementSelect.executeQuery(String.format(querySelectPat, SchemaDB.DB_TAB_COCHEALQUILER, SchemaDB.COL_MATRICULA_TAB_COCHESALQUILER, matricula));


                if (resultadoQuery.next()) {

                    encontrado = true;

                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return encontrado;
    }

    public boolean comprobarCocheVenta(String matricula){
        boolean encontrado = false;
        Statement statementSelect = null;
        String querySelectPat = "SELECT * FROM %s WHERE %s='%s'";


        try {
            statementSelect = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            try {
                ResultSet resultadoQuery = statementSelect.executeQuery(String.format(querySelectPat, SchemaDB.DB_TAB_COCHEVENTA, SchemaDB.COL_MATRICULA_TAB_COCHEVENTA, matricula));


                if (resultadoQuery.next()) {

                    encontrado = true;

                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return encontrado;
    }
    public boolean comprobarCliente(String dni) {
        boolean encontrado = false;
        Statement statementSelect = null;
        String querySelectPat = "SELECT * FROM %s WHERE %s='%s'";


        try {
            statementSelect = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            try {
                ResultSet resultadoQuery = statementSelect.executeQuery(String.format(querySelectPat, SchemaDB.DB_TAB_CLIENTES, SchemaDB.COL_DNI_TAB_CLIENTES, dni));


                if (resultadoQuery.next()) {
                    System.out.println("Usuario encontrado");
                    encontrado = true;

                } else {
                    System.out.println("no se ha encontrado el usuario");


                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return encontrado;
    }

    public int AlquilarCoche() {


        int precioalquiler=0;
        verCochesDisponiblesAlquiler();
        System.out.println("Dime la matricula el coche que desea alquilar el cliente");
        String matricula = teclado.next();
        if (!comprobarCocheAlquiler(matricula)){
            System.out.println("Matricula no encontrada");
        }else {

            System.out.println("Dime el DNI del cliente");
            String dni = teclado.next();
            Statement statementSelect = null;
            if (comprobarCliente(dni)) {
                System.out.println("el Cliente esta dado de alta en el sistema");
            } else {
                System.out.println("Cliente no encontrado, procedmos a crear su ficha");
                agregarCliente();
            }

            String queryUpdatePat = "UPDATE %s SET %s = '%s' WHERE %s = '%s'";
            String queryUpdate = String.format(queryUpdatePat, SchemaDB.DB_TAB_COCHEALQUILER,
                    SchemaDB.COL_ALQUILADO_TAB_COCHESALQUILER, 1,
                    SchemaDB.COL_MATRICULA_TAB_COCHESALQUILER, matricula);
            Statement statementUpdate = null;
            try {
                statementUpdate = connection.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                System.out.println(statementUpdate.executeUpdate(queryUpdate));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            System.out.println("Dime el dia de incio del alquiler, con el siguiente formato yyyy-mm-dd");
            String fec_inicio = teclado.next();
            System.out.println("Dime el dia de finalizacion del alquiler, con el siguiente formato yyyy-mm-dd");
            String fec_fin = teclado.next();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date fechaInicial = null;
            try {
                fechaInicial = dateFormat.parse(fec_inicio);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date fechaFinal = null;
            try {
                fechaFinal = dateFormat.parse(fec_fin);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
            int precioDia = 0;

            String querydiasPATH = "SELECT precio_dia from coches_alquiler where matricula='%s' ";
            try {
                statementSelect = connection.createStatement();
                ResultSet resultadoQuery = statementSelect.executeQuery(String.format(querydiasPATH, matricula));
                while (resultadoQuery.next()) {
                    precioDia = resultadoQuery.getInt(SchemaDB.COL_PRECIODIA_TAB_COCHESALQUILER);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            precioalquiler = dias * precioDia;
            System.out.println("El precio total del alquiler es: " + precioalquiler);

            String queryAlquileresPATH = "INSERT INTO %s (%s,%s,%s,%s,%s) VALUES ('%s','%s','%s','%s',%d)";
            String queryCreate = String.format(queryAlquileresPATH,
                    SchemaDB.DB_TAB_ALQUILERES,
                    SchemaDB.COL_DNI_ALQUILERES, SchemaDB.COL_MATRICULA_ALQUILERES, SchemaDB.COL_FECINICIO_ALQUILERES, SchemaDB.COL_FECFINAL, SchemaDB.COL_PRECIO_ALQUILER,
                    dni, matricula, fec_inicio, fec_fin, precioalquiler);


            try {
                statementCreate = connection.createStatement();
                statementCreate.execute(queryCreate);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("¿El cliente quiere Comprobante de Alquiler?-1.Si-2.NO");
            int factura=teclado.nextInt();
            switch (factura){

                case 1:
                    File f = new File("src/main/java/facturas/comprbanteAlquiler.txt");
                    if (!f.exists()) {
                        try {
                            f.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    FileWriter fw = null;
                    try {
                        fw = new FileWriter(f);
                        fw.write("Comprobante de compra");
                        fw.write("\n");
                        fw.write("El vehiculo con matricula"+matricula);
                        fw.write("\n");
                        fw.write("Se ha alquiola  a el cliente con DNI"+dni);
                        fw.write("\n");
                        fw.write("Por el precio de "+precioalquiler);
                        System.out.print("Registro creado : ");hilo.run();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            fw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    System.out.println("no se crea Comprobante, datos añadidos a la base de datos");
                    break;
            }


            }

            return precioalquiler;


        }

    public void darDeBajaAlquiler() {

        verCochesAlquilados();
        System.out.println("-----");
        System.out.println("Dime la matricula del coche que vamos a Poner como disponible");

        String matricula = teclado.next();

        if (!comprobarCocheAlquiler(matricula)){
            System.out.println("Matricula no encontrada");
        }else {
            String queryUpdatePat = "UPDATE %s SET %s = '%s' WHERE %s = '%s'";
            String queryUpdate = String.format(queryUpdatePat, SchemaDB.DB_TAB_COCHEALQUILER,
                    SchemaDB.COL_ALQUILADO_TAB_COCHESALQUILER, 0,
                    SchemaDB.COL_MATRICULA_TAB_COCHESALQUILER, matricula);
            Statement statementUpdate = null;
            try {
                statementUpdate = connection.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
               statementUpdate.executeUpdate(queryUpdate);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("Alquiler dado de baja de forma exitosa");
        }

    }

    public int venderCoche() {

        int precio = 0;
        verCochesEnVenta();
        System.out.println("Dime la matricula del coche a vender");
        String matricula = teclado.next();
        if (!comprobarCocheVenta(matricula)){
            System.out.println("Matricula no encontrada");
        }else {
            System.out.println("Dime el DNI del cliente");
            String dni = teclado.next();
            Statement statementSelect = null;
            if (comprobarCliente(dni)) {
                System.out.println("el Cliente esta dado de alta en el sistema");
            } else {
                System.out.println("Cliente no encontrado, procedmos a crear su ficha");
                agregarCliente();
            }
            String queryUpdatePat = "UPDATE %s SET %s = '%s' WHERE %s = '%s'";
            String queryUpdate = String.format(queryUpdatePat, SchemaDB.DB_TAB_COCHEVENTA,
                    SchemaDB.COL_VENDIDO_TAB_COCHEVENTA, 1,
                    SchemaDB.COL_MATRICULA_TAB_COCHEVENTA, matricula);
            Statement statementUpdate = null;
            try {
                statementUpdate = connection.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                System.out.println(statementUpdate.executeUpdate(queryUpdate));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String queryPrecioPATH = "SELECT precio_venta from coches_venta where matricula='%s' ";
            try {
                statementSelect = connection.createStatement();
                ResultSet resultadoQuery = statementSelect.executeQuery(String.format(queryPrecioPATH, matricula));
                while (resultadoQuery.next()) {
                    precio = resultadoQuery.getInt(SchemaDB.COL_PRECIVENTA_TAB_COCHEVENTA);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            System.out.println("El precio total de la venta es: " + precio);
            String queryAlquileresPATH = "INSERT INTO %s (%s,%s,%s) VALUES ('%s','%s',%d)";
            String queryCreate = String.format(queryAlquileresPATH,
                    SchemaDB.DB_TAB_VENTAS,
                    SchemaDB.COL_DNI_VENTAS, SchemaDB.COL_MATRICULA_VENTAS, SchemaDB.COL_PRECIO_VENTAS,
                    dni, matricula, precio);
            try {
                statementCreate = connection.createStatement();
                statementCreate.execute(queryCreate);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("¿El cliente quiere Comprobante de Compra?-1.Si-2.NO");
            int factura=teclado.nextInt();
            switch (factura){

                case 1:
                    File f = new File("src/facturas/comprbanteAlquiler.txt");
                    if (!f.exists()) {
                        try {
                            f.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    FileWriter fw = null;
                    try {
                        fw = new FileWriter(f);
                        fw.write("Comprobante de compra");
                        fw.write("\n");
                        fw.write("El vehiculo con matricula:"+matricula);
                        fw.write("\n");
                        fw.write("Se ha vendido  a el cliente con DNI: "+dni);
                        fw.write("\n");
                        fw.write("Por el precio de "+precio);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            fw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    System.out.println("no se crea Comprobante, datos añadidos a la base de datos");
                    break;
            }
            return precio;
        }
        return precio;
    }


}


