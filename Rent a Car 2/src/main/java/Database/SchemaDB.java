package Database;

public interface SchemaDB {

    String DB_NAME = "rentacar";

    //tabla trabajadores
    String DB_TAB_TRABAJADORES = "Trabajadores";
    String COL_ID_TAB_TRABAJADORES = "ID";
    String COL_NOMBRE_TAB_TRABAJADORES = "Nombre";
    String COL_APELLIDOS_TAB_TRABAJADORES = "Apellidos";
    String COL_EMAIL_TAB_TRABAJADORES = "Email";
    String COL_CONTRASEÑA_TAB_TRABAJADORES = "Contraseña";

    // tabla clientes
    String DB_TAB_CLIENTES = "clientes";
    String COL_DNI_TAB_CLIENTES = "DNI";
    String COL_NOMBRE_TAB_CLIENTES = "Nombre";
    String COL_APELLIDOS_TAB_CLIENTES = "Apellidos";
    String COL_EMAIL_TAB_CLIENTES = "Email";


    // tabla coche venta

    String DB_TAB_COCHEVENTA = "coches_venta";
    String COL_MATRICULA_TAB_COCHEVENTA = "Matricula";
    String COL_MARCA_TAB_COCHEVENTA = "Marca";
    String COL_MODELO_TAB_COCHEVENTA = "Modelo";
    String COL_CC_TAB_COCHEVENTA = "CC";
    String COL_CV_TAB_COCHEVENTA = "CV";
    String COL_PRECIVENTA_TAB_COCHEVENTA = "precio_venta";
    String COL_VENDIDO_TAB_COCHEVENTA = "Vendido";

    //tabla coche alquiler

    String DB_TAB_COCHEALQUILER = "coches_alquiler";
    String COL_MATRICULA_TAB_COCHESALQUILER = "Matricula";
    String COL_MARCA_TAB_COCHESALQUILER = "Marca";
    String COL_MODELO_TAB_COCHESALQUILER = "Modelo";
    String COL_CC_TAB_COCHESALQUILER = "CC";
    String COL_CV_TAB_COCHESALQUILER = "CV";
    String COL_PRECIODIA_TAB_COCHESALQUILER = "precio_dia";
    String COL_ALQUILADO_TAB_COCHESALQUILER = "Alquilado";


    // tabla alquileres
    String DB_TAB_ALQUILERES = "Alquileres";
    String COL_DNI_ALQUILERES = "dni_cliente";
    String COL_MATRICULA_ALQUILERES = "matricula_coche";
    String COL_FECINICIO_ALQUILERES = "fec_inicio";
    String COL_FECFINAL = "fec_final";
    String COL_PRECIO_ALQUILER = "precio_alquiler";

    //tabla ventas
    String DB_TAB_VENTAS = "ventas";
    String COL_MATRICULA_VENTAS = "matricula_coche";
    String COL_DNI_VENTAS = "dni_cliente";
    String COL_PRECIO_VENTAS = "precio_venta";

    //tabla administrador

    String DB_TAB_ADMIN="Administrador";
    String COL_USUARIO_ADMIN="Usuario";
    String COL_EMAIL_ADMIN="Email";
    String COL_CONTRASEÑA_ADMIN="Contraseña";


}
