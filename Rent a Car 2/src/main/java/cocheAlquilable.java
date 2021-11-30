import java.util.Date;

public final class cocheAlquilable extends Coche {

    private double precioPorDía;
    boolean disponioble;

    public cocheAlquilable(String marca, String modelo, String matricula, int cc, int cv, double precioPorDía) {
        super(marca, modelo, matricula, cc, cv);
        this.precioPorDía = precioPorDía;
        this.disponioble = disponioble;
    }

    public cocheAlquilable() {
    }

    public double getPrecioPorDía() {
        return precioPorDía;
    }

    public void setPrecioPorDía(double precioPorDía) {
        this.precioPorDía = precioPorDía;
    }

    public boolean isDisponioble() {
        return disponioble;
    }

    public void setDisponioble(boolean disponioble) {
        this.disponioble = disponioble;
    }

    @Override
    public void verDatos() {
        System.out.println("****Coche de alquiler****");
        super.verDatos();
        System.out.println("Precio por día: "+getPrecioPorDía());
        if (disponioble){
            System.out.println("El coche esta disponible para alquilarlo");
        }else {
            System.out.println("El coche no está disponible para alquilarlo");
        }
    }

}
