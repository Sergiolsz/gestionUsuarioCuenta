import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {

      // Creamos un nuevo usuario
      Usuario usuario = crearUsuario(scanner);

      // Asociamos a una cuenta el usuario
      Cuenta cuenta = new Cuenta(usuario);

      int operativa;
      // Se ejecutará el siguiente bloque hasta que el usuario introduzca la operativa 0
      do {
        // Lanzamos el menu de operativas
        menu();
        // Número de operativa a ejecutar
        operativa = scanner.nextInt();
        // Operativas disponibles. Se ejecutará la operativa introducida
        String salidaMenu = operativaMenu(operativa, scanner, cuenta);
        // Salida del mensaje de la operativa
        System.out.println(salidaMenu);
      } while (operativa != 0);
    }

    System.out.println("Gracias por utilizar la aplicación de MO3B en el curso 2s2223");
  }

  /**
   * Método público para la creación de un Usuario. Se usa la clase Scanner como parámetro para el
   * registro de los datos de entrada
   *
   * @param scanner Escáner para introducir un dato
   * @return Usuario
   */
  public static Usuario crearUsuario(Scanner scanner) {
    Usuario usuario = new Usuario();

    System.out.println("Introduzca el nombre del usuario");
    usuario.setNombre(scanner.nextLine());

    System.out.println("Introduzca la edad del usuario");
    usuario.setEdad(Integer.parseInt(scanner.nextLine()));

    System.out.println("Introduzca el DNI del Usuario");
    usuario.setDNI(scanner.nextLine());

    while (usuario.getDNI().isEmpty()) {
      System.out.println("Introduzca un DNI correcto del Usuario");
      usuario.setDNI(scanner.nextLine());
    }

    System.out.println(usuario);
    return usuario;
  }

  /**
   * Método para visualizar el Menu Principal
   */
  public static void menu() {
    System.out.println("Realiza una nueva acción");
    System.out.println("1 Introduce un nuevo gasto");
    System.out.println("2 Introduce un nuevo ingreso");
    System.out.println("3 Mostrar gastos");
    System.out.println("4 Mostrar ingresos");
    System.out.println("5 Mostrar saldo");
    System.out.println("0 Salir");
  }

  /**
   * Método para realizar la operativa seleccionada por el usuario en el Menú Principal
   *
   * @param eleccion Número de operativa
   * @param scanner  Escáner para introducir un dato
   * @param cuenta   Cuenta del usuario
   * @return Mensaje de la operativa realizada
   */
  public static String operativaMenu(int eleccion, Scanner scanner, Cuenta cuenta) {
    double saldo = cuenta.getSaldo();

    switch (eleccion) {
      case 1:

        scanner.nextLine();

        System.out.println("Introduzca la descripción del gasto");
        String descripcionGasto = scanner.nextLine();

        System.out.println("Introduzca la cantidad del gasto");
        double nuevoGasto = scanner.nextDouble();

        try {
          // Comprobamos el saldo de la cuenta para ver si tenemos suficiente dinero para afrontar el gasto
          double saldoNegativoActualizado = checkGasto(saldo, nuevoGasto);

          // Añadimos a la cuenta el nuevo gasto
          cuenta.addGasto(descripcionGasto, nuevoGasto);

          // Actualizamos el saldo de la cuenta
          cuenta.setSaldo(saldoNegativoActualizado);

          return "Nuevo saldo: " + formatSaldo(cuenta.getSaldo());
        } catch (GastoException e) {
          // En caso de que no tengamos saldo, lanzará la excepción con el mensaje introducido en la excepcion
          return e.getMessage();
        }

      case 2:

        scanner.nextLine();

        System.out.println("Introduzca la descripción del ingreso");
        String descripcionIngreso = scanner.nextLine();

        System.out.println("Introduzca la cantidad del ingreso");
        double nuevoIngreso = scanner.nextDouble();

        // Añadimos el nuevo ingreso a la cuenta
        cuenta.addIngresos(descripcionIngreso, nuevoIngreso);

        // Realizamos la operativa de suma del ingreso con el saldo
        double saldoPositivoActualizado = saldo + nuevoIngreso;

        // Actualizamos el nuevo saldo
        cuenta.setSaldo(saldoPositivoActualizado);

        return "Nuevo saldo: " + formatSaldo(cuenta.getSaldo());
      case 3:
        return "Gastos: " + cuenta.getGastos();
      case 4:
        return "Ingresos: " + cuenta.getIngresos();
      case 5:
        return "Saldo disponible: " + formatSaldo(cuenta.getSaldo());
      default:
        break;
    }
    return "Seleccione alguna acción disponible";
  }

  /**
   * Método para comprobar si el saldo es superior al gasto introducido. Si es inferior, lanzará una
   * excepción avisando de que no hay saldo suficiente.
   *
   * @param saldo      Saldo actual en la cuenta
   * @param nuevoGasto Cantidad de un nuevo gasto
   * @return Nuevo saldo habiendo restado el gasto
   * @throws GastoException
   */
  public static double checkGasto(double saldo, double nuevoGasto) throws GastoException {
    if (saldo < nuevoGasto) {
      throw new GastoException();
    }

    return saldo - nuevoGasto;
  }

  /**
   * Método para dar formato de salida al saldo a formato: xx,xx€
   *
   * @param saldo Saldo de la cuenta
   * @return Saldo
   */
  public static String formatSaldo(double saldo) {
    return String.valueOf(Math.round(saldo * 100.0) / 100.0)
        .replace(".", ",") + "€";
  }
}
