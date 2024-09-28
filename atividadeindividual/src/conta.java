public class Conta {
    private final String titular;
    private double saldo;

    public Conta(String titular) {
        this.titular = titular;
        this.saldo = 0;
    }

    public synchronized void debitar(double valor) {
        this.saldo -= valor;
    }

    public synchronized void creditar(double valor) {
        this.saldo += valor;
    }

    public synchronized double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }
}
