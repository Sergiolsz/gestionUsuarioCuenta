public class Usuario {

  private String nombre;
  private int edad;
  private String DNI;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public String getDNI() {
    return DNI;
  }

  public void setDNI(String DNI) {
    boolean patternDNI = DNI.matches("[0-9]{8}-?[A-Z]");
    if (patternDNI) {
      this.DNI = DNI;
    } else {
      this.DNI = "";
    }
  }

  @Override
  public String toString() {
    return
        "Nombre: " + nombre + "\n" +
            "Edad: " + edad + "\n" +
            "DNI: " + DNI;
  }
}
