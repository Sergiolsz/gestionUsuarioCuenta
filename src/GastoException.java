public class GastoException extends Exception {

  public GastoException() {
    super("La cuenta no tiene saldo suficiente para afrontar ese gasto");
  }
}
