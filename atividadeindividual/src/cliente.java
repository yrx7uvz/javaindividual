import java.util.Random;

public class Cliente extends Thread {
    private final Banco banco;
    private final Conta conta;
    private final Loja[] lojas;

    public Cliente(Banco banco, String nome, Loja[] lojas) {
        this.banco = banco;
        this.conta = new Conta(nome);
        this.lojas = lojas;
        this.conta.creditar(2000.00); // saldo inicial de 2000
    }

    @Override
    public void run() {
        try {
            for (Loja loja : lojas) {
                realizarCompras(loja);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void realizarCompras(Loja loja) throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 2; i++) { // 2 compras por loja
            double valorCompra = 200 + random.nextDouble() * 300; // Valor entre 200 e 500
            banco.transferir(conta, loja.getConta(), valorCompra);
            System.out.println(getName() + " comprou na " + loja.getNome() + " por R$ " + valorCompra);
            Thread.sleep(500); // simula o tempo da compra
        }
    }
}
