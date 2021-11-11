import Database.*;
import Database.SchemaDB;
import com.mysql.cj.xdevapi.Schema;

import java.sql.*;
import java.util.Scanner;

public final class Administrador extends Usuario {
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

    ;
    private int idADmin;
    Scanner teclado = new Scanner(System.in);


    public Administrador(String nombre, String apellido, String email, String contraseña) {
        super(nombre, apellido, email, contraseña);
    }

    public Administrador() {
        super();
    }

    @Override
    public void verDatos() {
        System.out.println("Id de Aministrador");
        super.verDatos();
    }

    public Trabajador agregarTrbajador() {


        try {
            try {
                connection = DriverManager.getConnection(connectionURL
                        , "root", "");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("****AGREGANDO NUEVO TRABAJADOR****");
        System.out.println("Dime el nombre del trabajador");
        String nombre = teclado.next();
        System.out.println("Dime los apellidos de el trabajador");
        String apellidos = teclado.next();
        System.out.println("Dime el ID (unico) del trabajador");
        String id = teclado.next();

        System.out.println("Dime el email del trabajador");
        String email = teclado.next();
        System.out.println("Dime la contraseña del trabajador ");
        String contraseña = teclado.next();
        String queryCreatePat = "INSERT INTO %s (%s,%s,%s,%s,%s) VALUES ('%s','%s','%s','%s','%s')";
        String queryCreate = String.format(queryCreatePat,
                SchemaDB.DB_TAB_TRABAJADORES,
                SchemaDB.COL_ID_TAB_TRABAJADORES, SchemaDB.COL_NOMBRE_TAB_TRABAJADORES, SchemaDB.COL_APELLIDOS_TAB_TRABAJADORES, SchemaDB.COL_EMAIL_TAB_TRABAJADORES, SchemaDB.COL_CONTRASEÑA_TAB_TRABAJADORES,
                id, nombre, apellidos, email, contraseña);


        try {
            statementCreate = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        try {

            statementCreate.execute(queryCreate);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        System.out.println("Trabajador Creado Correctamente");

        return new Trabajador(id, nombre, apellidos, email, contraseña);
    }

    @Override
    public void verCochesAlquilados() {
        super.verCochesAlquilados();
    }

    @Override
    public void verCochesDisponiblesAlquiler() {
        super.verCochesDisponiblesAlquiler();
    }

    @Override
    public void verTodosAlquilables() {
        super.verTodosAlquilables();
    }

    public void listarTrabajadores() {
        String querySelectPath = "SELECT * FROM %S";
        Statement statementSelect = null;
        try {
            statementSelect = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            ResultSet resultadoQuery = statementSelect.executeQuery(String.format(querySelectPath, SchemaDB.DB_TAB_TRABAJADORES));
            while (resultadoQuery.next()) {
                String id = resultadoQuery.getString(SchemaDB.COL_ID_TAB_TRABAJADORES);
                String nombre = resultadoQuery.getString(SchemaDB.COL_NOMBRE_TAB_TRABAJADORES);
                String apellido = resultadoQuery.getString(SchemaDB.COL_APELLIDOS_TAB_TRABAJADORES);
                String email = resultadoQuery.getString(SchemaDB.COL_EMAIL_TAB_TRABAJADORES);

                System.out.println("-------");
                System.out.println("ID: " + id);
                System.out.printf("Nombre: %s %s %n", nombre, apellido);
                System.out.println("Email: " + email);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void borrarTrabajdor() {
        System.out.println("La lista de Todos los trabajadores del sistema es:");
        listarTrabajadores();
        System.out.println("Dime el ID del trabajador a borrar");
        String idBorrar = teclado.next();
        String queryDeletePat = "DELETE FROM %s WHERE %s = '%s'";
        String queryDelete = String.format(queryDeletePat, SchemaDB.DB_TAB_TRABAJADORES, SchemaDB.COL_ID_TAB_TRABAJADORES, idBorrar);
        Statement statementDelete = null;
        try {
            statementDelete = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            int comprobar = statementDelete.executeUpdate(queryDelete);

            if (comprobar == 1) {
                System.out.println("El usuario ha sido eliminado correctamente");

            } else {
                System.out.println("No se ha encontrado el id del usuario a borrar");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void verCochesEnVenta() {
        super.verCochesEnVenta();
    }

    public cocheVenta agregarCocheVenta() {

        System.out.println("****Agregando Nuevo Coche en Venta****");
        System.out.println("Dime la marca");
        String marca = teclado.next();
        System.out.println("Dime el modelo del coche");
        String modelo = teclado.next();
        System.out.println("Dime la matricula del coche");
        String matricula = teclado.next();
        System.out.println("Dime los CC del coche");
        int CC = teclado.nextInt();
        System.out.println("Dime los CV del coche");
        int CV = teclado.nextInt();
        System.out.println("dime el precio de venta del coche");
        int precioVenta = teclado.nextInt();
        String queryCreatePat = "INSERT INTO %s (%s,%s,%s,%s,%s,%s) VALUES ('%s','%s','%s',%d,%d,%d)";
        String queryCreate = String.format(queryCreatePat,
                SchemaDB.DB_TAB_COCHEVENTA,
                SchemaDB.COL_MATRICULA_TAB_COCHEVENTA, SchemaDB.COL_MARCA_TAB_COCHEVENTA, SchemaDB.COL_MODELO_TAB_COCHEVENTA, SchemaDB.COL_CC_TAB_COCHEVENTA, SchemaDB.COL_CV_TAB_COCHEVENTA, SchemaDB.COL_PRECIVENTA_TAB_COCHEVENTA
                , matricula, marca, modelo, CC, CV, precioVenta);
        try {
            statementCreate = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        try {
            System.out.println(queryCreate);
            statementCreate.execute(queryCreate);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




        return new cocheVenta(marca, modelo, matricula, CC, CV, precioVenta);


    }

    public cocheAlquilable agregarCocheAlquiler() {
        System.out.println("****Agregando Nuevo Coche en Alquiler****");
        System.out.println("Dime la marca");
        String marca = teclado.next();
        System.out.println("Dime el modelo del coche");
        String modelo = teclado.next();
        System.out.println("Dime la matricula del coche");
        String matricula = teclado.next();
        System.out.println("Dime los CC del coche");
        int CC = teclado.nextInt();
        System.out.println("Dime los CV del coche");
        int CV = teclado.nextInt();
        System.out.println("dime el precio por día del coche");
        int precioDia = teclado.nextInt();
        String queryCreatePat = "INSERT INTO %s (%s,%s,%s,%s,%s,%s) VALUES ('%s','%s','%s',%d,%d,%d)";
        String queryCreate = String.format(queryCreatePat,
                SchemaDB.DB_TAB_COCHEALQUILER,
                SchemaDB.COL_MATRICULA_TAB_COCHESALQUILER, SchemaDB.COL_MARCA_TAB_COCHESALQUILER, SchemaDB.COL_MODELO_TAB_COCHESALQUILER, SchemaDB.COL_CC_TAB_COCHESALQUILER, SchemaDB.COL_CV_TAB_COCHESALQUILER, SchemaDB.COL_PRECIODIA_TAB_COCHESALQUILER
                , matricula, marca, modelo, CC, CV, precioDia);
        try {
            statementCreate = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            System.out.println(queryCreate);
            statementCreate.execute(queryCreate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new cocheAlquilable(marca, modelo, matricula, CC, CV, precioDia);

    }

    public void verCochesVendidos() {
        System.out.println("**** Lista de Coches Vendidos***");
        String querySelectPat = "SELECT * FROM %s WHERE %s='%s'";
        Statement statementSelect = null;
        try {
            statementSelect = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet resultadoQuery = statementSelect.executeQuery(String.format(querySelectPat, SchemaDB.DB_TAB_COCHEVENTA, SchemaDB.COL_VENDIDO_TAB_COCHEVENTA, "1"));
            while (resultadoQuery.next()) {
                System.out.println("--------");
                String Marca = resultadoQuery.getString(SchemaDB.COL_MARCA_TAB_COCHEVENTA);
                String Modelo = resultadoQuery.getString(SchemaDB.COL_MODELO_TAB_COCHEVENTA);
                String Matricula = resultadoQuery.getString(SchemaDB.COL_MATRICULA_TAB_COCHEVENTA);
                String PrecioVenta = resultadoQuery.getString(SchemaDB.COL_PRECIVENTA_TAB_COCHEVENTA);

                System.out.println("Marca:" + Marca);
                System.out.println("Modelo: " + Modelo);
                System.out.println("Matricula: " + Matricula);
                System.out.println("Se vendió en el precio de: " + PrecioVenta);
                System.out.println("---------");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    public void verTodosCocheVenta() {
        System.out.println("**** Lista de Coches Vendibles***");
        String querySelectPat = "SELECT * FROM %s ";
        Statement statementSelect = null;
        try {
            statementSelect = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet resultadoQuery = statementSelect.executeQuery(String.format(querySelectPat, SchemaDB.DB_TAB_COCHEVENTA));
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
                if (resultadoQuery.getBoolean(SchemaDB.COL_VENDIDO_TAB_COCHEVENTA)) {
                    System.out.println("El Coche ya se ha vendido");
                } else {
                    System.out.println("El coche sigue en venta");
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    public void borrarCocheEnVenta() {
        System.out.println("La lista de Todos los copches Vendibles del sistema es:");
        verTodosCocheVenta();
        System.out.println("Dime la matricula del coche a borrar");
        String matriculaBorrar = teclado.next();
        String queryDeletePat = "DELETE FROM %s WHERE %s = '%s'";
        String queryDelete = String.format(queryDeletePat, SchemaDB.DB_TAB_COCHEVENTA, SchemaDB.COL_MATRICULA_TAB_COCHEVENTA, matriculaBorrar);
        Statement statementDelete = null;
        try {
            statementDelete = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            int comprobar = statementDelete.executeUpdate(queryDelete);
            System.out.println(comprobar);
            if (comprobar == 1) {
                System.out.printf("El coche con Matricula %s se ha borrado correctemente %n", matriculaBorrar);

            } else {
                System.out.println("No se ha encontrado la matricula a borrar");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void borrarCocheAlquiler() {
        System.out.println("La lista de Todos los copches Alquilables del sistema es:");
        verTodosAlquilables();
        System.out.println("Dime la matricula del coche a borrar");
        String matriculaBorrar = teclado.next();
        String queryDeletePat = "DELETE FROM %s WHERE %s = '%s'";
        String queryDelete = String.format(queryDeletePat, SchemaDB.DB_TAB_COCHEALQUILER, SchemaDB.COL_MATRICULA_TAB_COCHESALQUILER, matriculaBorrar);
        Statement statementDelete = null;
        try {
            statementDelete = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            int comprobar = statementDelete.executeUpdate(queryDelete);
            System.out.println(comprobar);
            if (comprobar == 1) {
                System.out.printf("El coche con Matricula %s se ha borrado correctemente", matriculaBorrar);

            } else {
                System.out.println("No se ha encontrado la matricula a borrar");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public int verCajaVentas(){
        int cajaVentas=0;
        String query="SELECT precio_venta from ventas";
        Statement statementSelect=null;
        try {
            statementSelect=connection.createStatement();
            ResultSet resultSetPrecio=statementSelect.executeQuery(query);
            while (resultSetPrecio.next()){
                int precioCoche=resultSetPrecio.getInt(SchemaDB.COL_PRECIVENTA_TAB_COCHEVENTA);
                cajaVentas+=precioCoche;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("El total en ventas: "+cajaVentas);
        return cajaVentas;


    }
    public int verCajaVAlquiler(){
        int cajaAlquiler=0;
        String query="SELECT precio_alquiler from Alquileres";
        Statement statementSelect=null;
        try {
            statementSelect=connection.createStatement();
            ResultSet resultSetPrecio=statementSelect.executeQuery(query);
            while (resultSetPrecio.next()){
                int precioCoche=resultSetPrecio.getInt(SchemaDB.COL_PRECIO_ALQUILER);
                cajaAlquiler+=precioCoche;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("El total en alquileres es: "+cajaAlquiler);

        return cajaAlquiler;


    }
    public void verCajaTotal(){
        int cajaTotal=verCajaVAlquiler()+verCajaVentas();

    System.out.println("El total facturado es: "+cajaTotal);

}

public void verVentasTotales(){
        String query="Select * from ventas";
        Statement statementSlect=null;
    try {
        statementSlect=connection.createStatement();
        ResultSet resultado=statementSlect.executeQuery(query);
        while (resultado.next()){
            String matricula=resultado.getString(SchemaDB.COL_MATRICULA_VENTAS);
            String dni=resultado.getString(SchemaDB.COL_DNI_VENTAS);
            int precioVenta=resultado.getInt(SchemaDB.COL_PRECIO_VENTAS);
            System.out.printf("El vehiculo con matricula %s fué comprado por el cliente %s por el precio de %d %n",matricula,dni,precioVenta);
            verCajaVentas();
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }

}
public void verAlquileresTotales(){
        String query="SELECT * FROM Alquileres";
        Statement statementSelect=null;
    try {
        statementSelect=connection.createStatement();
        ResultSet resultado=statementSelect.executeQuery(query);
        while (resultado.next()){
            String matricula=resultado.getString(SchemaDB.COL_MATRICULA_ALQUILERES);
            String dni=resultado.getString(SchemaDB.COL_DNI_ALQUILERES);
            String fec_inicio=resultado.getString(SchemaDB.COL_FECINICIO_ALQUILERES);
            String fec_final=resultado.getString(SchemaDB.COL_FECFINAL);
            int precioAlquiler=resultado.getInt(SchemaDB.COL_PRECIO_ALQUILER);
            System.out.println("-------");
            System.out.printf("El coche con matricula %s fué alquilado por el cliente con DNI %s del día %s al día %s con el coste de %d %n",matricula,dni,fec_inicio,fec_final,precioAlquiler);
        }
        System.out.println("*****");
        verCajaVAlquiler();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }

}
}
