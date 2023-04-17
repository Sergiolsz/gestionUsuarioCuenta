import java.util.ArrayList;
import java.util.List;

public class Cuenta {

  private double saldo;
  private Usuario usuario;
  private List<Gasto> gastos;
  private List<Ingreso> ingresos;

  public Cuenta(Usuario usuario) {
    this.usuario = usuario;
    this.saldo = 0;
    gastos = new ArrayList<>();
    ingresos = new ArrayList<>();
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public double addIngresos(String description, double cantidad) {
    Ingreso nuevoIngreso = new Ingreso(cantidad, description);
    ingresos.add(nuevoIngreso);
    return cantidad;
  }

  public double addGasto(String description, double cantidad) {
    Gasto nuevoGasto = new Gasto(cantidad, description);
    gastos.add(nuevoGasto);
    return cantidad;
  }

  public List<Ingreso> getIngresos() {
    return ingresos;
  }

  public List<Gasto> getGastos() {
    return gastos;
  }

  @Override
  public String toString() {
    return "Cuenta " + "\n" +
        "Saldo: " + String.valueOf(Math.round(saldo * 100.0)/100.0).replace(".", ",") + "€" + "\n" +
        "Usuario: " + usuario + "\n" +
        "Gastos: " + gastos + "\n" +
        "Ingresos: " + ingresos;
  }
}
