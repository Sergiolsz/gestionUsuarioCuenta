public class Ingreso extends Dinero {

  public Ingreso(double dinero, String description) {
    super(dinero, description);
  }

  @Override
  public String toString() {
    return "Ingreso" + "\n" +
        "Dinero: " + String.valueOf(Math.round(dinero * 100.0)/100.0).replace(".", ",") + "€" + "\n" +
        "Descripción: " + description + "\n";
  }
}