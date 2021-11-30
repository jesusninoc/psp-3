import java.util.Scanner;

public class Entrada {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);



        System.out.println("****Bienvenido a Rent a Car****");

        int opcionPrincipal=0;
        do {
            System.out.println("Dime que quieres hacer");
            System.out.println("1.Acceder como Administrador");
            System.out.println("2.Acceder como Trbajador");
            System.out.println("3.Salir");
            opcionPrincipal=teclado.nextInt();
            switch (opcionPrincipal){
                case 1:
                    int opcionAdmin=0;
                    Login login=new Login();

                    if (login.logingAdmin()){
                        Administrador administrador=new Administrador();

                        System.out.println("¿Que desea hacer?");
                        do {
                            System.out.println("1.Administrar Trbajadores");
                            System.out.println("2.Administrar vehiculos");
                            System.out.println("3.Datos de  Facturación");
                            System.out.println("4.Salir del sistema");
                            opcionAdmin=teclado.nextInt();

                            switch (opcionAdmin){
                                case 1:
                                    int opcionAdminTrabajadores=0;
                                    do {
                                        System.out.println("Dime que quieres hacer");
                                        System.out.println("1. Ver los trabajadores Actuales");
                                        System.out.println("2.Agregar nuevo trabajador");
                                        System.out.println("3.Dar de baja Trabajador");
                                        System.out.println("4.Volver atras");

                                        opcionAdminTrabajadores = teclado.nextInt();

                                        switch (opcionAdminTrabajadores) {
                                            case 1:
                                                administrador.listarTrabajadores();

                                                break;
                                            case 2:
                                                administrador.agregarTrbajador();

                                                break;
                                            case 3:
                                                administrador.borrarTrabajdor();

                                                break;
                                            case 4:

                                                break;
                                        }
                                    }while (opcionAdminTrabajadores!=4);

                                    break;
                                case 2:
                                    int opcionAdminVehiculos=0;
                                    int opcionAdminvehiculosVenta=0;
                                    int opcionAdminvehiculosAlquiler=0;
                                    do {
                                        System.out.println("Dime sobre el tipo de Vehiculo que quieres trabajar");
                                        System.out.println("1.Vehiculos - Venta");
                                        System.out.println("2.Vehiculos - Alquiler");
                                        opcionAdminVehiculos= teclado.nextInt();

                                        switch (opcionAdminVehiculos){
                                            case 1:
                                                do {
                                                    System.out.println("****Venta****");
                                                    System.out.println("1.Ver historico de Vehiculos Vendidos o en Venta");
                                                    System.out.println("2.Ver Vehiculos en Venta ");
                                                    System.out.println("3.Ver Vehiculos vendidos");
                                                    System.out.println("4.Agregar Coche en Venta");
                                                    System.out.println("5.Eliminar coche en venta");
                                                    System.out.println("6.Volver atras");
                                                    opcionAdminvehiculosVenta=teclado.nextInt();



                                                    switch (opcionAdminvehiculosVenta){
                                                        case 1:
                                                            administrador.verTodosCocheVenta();
                                                            break;
                                                        case 2:
                                                            administrador.verCochesEnVenta();
                                                            break;
                                                        case 3:
                                                            administrador.verCochesVendidos();
                                                            break;
                                                        case 4:
                                                            administrador.agregarCocheVenta();
                                                            break;
                                                        case 5:
                                                            administrador.borrarCocheEnVenta();
                                                            break;
                                                        case 6:
                                                            break;
                                                    }

                                                }while (opcionAdminvehiculosVenta!=6);
                                                break;
                                            case 2:
                                                do {
                                                    System.out.println("1.Listar todos los coches Alquilables");
                                                    System.out.println("2.Ver coches en alquiler disponibles");
                                                    System.out.println("3.Agregar coche en Alquiler");
                                                    System.out.println("4.Borrar coche alquiler");
                                                    System.out.println("5.Volver atras");
                                                    opcionAdminvehiculosAlquiler= teclado.nextInt();
                                                    switch (opcionAdminvehiculosAlquiler){

                                                        case 1:
                                                            administrador.verTodosAlquilables();
                                                            break;
                                                        case 2:
                                                            administrador.verCochesDisponiblesAlquiler();
                                                            break;
                                                        case 3:
                                                            administrador.agregarCocheAlquiler();
                                                            break;
                                                        case 4:
                                                            administrador.borrarCocheAlquiler();
                                                            break;
                                                        case 5:
                                                            break;
                                                    }

                                                }while (opcionAdminvehiculosAlquiler!=5);
                                                break;
                                            case 3:
                                                break;
                                        }

                                    }while (opcionAdminVehiculos!=3);

                                    break;
                                case 3:
                                    int opcionAdminFacturacion=0;
                                    do {
                                        System.out.println("1.Ver todas las ventas realizadas");
                                        System.out.println("2.Ver todos los alquileres realizados");
                                        System.out.println("3.Ver Facturacion total de alquileres");
                                        System.out.println("4. Ver Facturacion Total de Ventas");
                                        System.out.println("5.Ver facturacion total");
                                        System.out.println("6.Volver atras");
                                        opcionAdminFacturacion=teclado.nextInt();
                                        switch (opcionAdminFacturacion){
                                            case 1:
                                                administrador.verVentasTotales();
                                                break;
                                            case 2:
                                                administrador.verAlquileresTotales();
                                                break;
                                            case 3:
                                                administrador.verCajaVAlquiler();
                                                break;
                                            case 4:
                                                administrador.verCajaVentas();
                                                break;
                                            case 5:
                                                administrador.verCajaTotal();
                                                break;
                                        }


                                    }while (opcionAdminFacturacion!=6);
                                    break;

                                case 4:
                                    opcionPrincipal=4;
                                    break;

                            }

                        }while (opcionAdmin!=4);
                    }else {
                        System.out.println("Credenciales no válidas vuelve a intentarlo");
                    }
                    break;
                case 2:
                    Login login1=new Login();
                    if (login1.logingTrabjador()){
                        Trabajador trabajador= new Trabajador();
                        int opcionTrabajador=0;

                        do {
                            System.out.println("Dime que quieres hacer");
                            System.out.println("1.Ver listado de coches Disponibles para Venta");
                            System.out.println("2.Ver Todos los coches alquilables");
                            System.out.println("3.Ver listado de coches disponibles para Alquilar");
                            System.out.println("4.Vender Coche");
                            System.out.println("5.Alquilar coche");
                            System.out.println("6.Dar de baja Alquiler");
                            System.out.println("7.Salir");
                            opcionTrabajador=teclado.nextInt();

                            switch (opcionTrabajador){
                                case 1:
                                    trabajador.verCochesEnVenta();
                                    break;
                                case 2:
                                    trabajador.verTodosAlquilables();
                                    break;
                                case 3:
                                    trabajador.verCochesDisponiblesAlquiler();
                                    break;
                                case 4:
                                    trabajador.venderCoche();
                                    break;
                                case 5:
                                    trabajador.AlquilarCoche();
                                    break;
                                case 6:
                                    trabajador.darDeBajaAlquiler();
                                    break;
                                case 7:
                                    opcionPrincipal=3;
                                    break;
                            }

                        }while (opcionTrabajador!=7);

                    }else {
                        System.out.println("Credenciales no validas, vuelve a intertarlo.");
                    }



                    break;
                case 3:
                    opcionPrincipal=3;

                    break;

            }
        }while (opcionPrincipal!=3);



        }
    }