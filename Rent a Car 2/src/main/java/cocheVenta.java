public final class cocheVenta extends Coche {

    private int precioVenta;

    public cocheVenta(String marca, String modelo, String matricula, int cc, int cv, int precioVenta) {
        super(marca, modelo, matricula, cc, cv);
        this.precioVenta = precioVenta;
    }

    public cocheVenta() {

    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    @Override
    public void verDatos() {
        System.out.println("****Coche en venta****");
        super.verDatos();
        System.out.println("Precio de venta: "+getPrecioVenta());
    }
}
