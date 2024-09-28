public class Funcionario extends Thread {
    private final Loja loja;
    private final Conta contaSalario;
    private final Conta contaInvestimento;

    public Funcionario(Loja loja, String nome) {
        this.loja = loja;
        this.contaSalario = new Conta(nome + " Salário");
        this.contaInvestimento = new Conta(nome + " Investimento");
    }

    @Override
    public void run() {
        try {
            
            synchronized (loja) {
                while (loja.getConta().getSaldo() < 1400) {
                    loja.wait(); // espera até receber o salário
                }
                loja.getBanco().transferir(loja.getConta(), contaSalario, 1400);
                double investimento = contaSalario.getSaldo() * 0.2;
                contaSalario.debitar(investimento); // 20% para investimento
                contaInvestimento.creditar(investimento);
                System.out.println(getName() + " recebeu salário e investiu R$ " + investimento);
                loja.notifyAll(); // notifica que o salário foi recebido
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Conta getContaSalario() {
        return contaSalario;
    }

    public Conta getContaInvestimento() {
        return contaInvestimento;
    }
}
