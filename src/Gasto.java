public class Gasto extends Dinero {

  public Gasto(double gasto, String description) {
    super(gasto, description);
  }

  @Override
  public String toString() {
    return "Gasto" + "\n" +
        "Dinero: " + String.valueOf(Math.round(dinero * 100.0)/100.0).replace(".", ",") + "€" + "\n" +
        "Descripción: " + description + "\n";
  }
}
