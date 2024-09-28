public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // criar lojas
        Loja loja1 = new Loja("Loja 1", banco);
        Loja loja2 = new Loja("Loja 2", banco);

        // criar funcionários
        Funcionario f1 = new Funcionario(loja1, "Funcionário 1");
        Funcionario f2 = new Funcionario(loja1, "Funcionário 2");
        Funcionario f3 = new Funcionario(loja2, "Funcionário 3");
        Funcionario f4 = new Funcionario(loja2, "Funcionário 4");

        // iniciar funcionários
        f1.start();
        f2.start();
        f3.start();
        f4.start();

        // criar clientes
        Cliente[] clientes = new Cliente[10];
        for (int i = 0; i < 10; i++) {
            clientes[i] = new Cliente(banco, "Cliente " + (i + 1), new Loja[]{loja1, loja2});
            clientes[i].start();
        }

        // espera todos os clientes terminarem
        for (Cliente cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // espera todos os funcionários terminarem
        for (Funcionario funcionario : new Funcionario[]{f1, f2, f3, f4}) {
            try {
                funcionario.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // mostra saldos finais
        for (Cliente cliente : clientes) {
            System.out.println(cliente.getName() + " - Saldo final: R$ " + cliente.conta.getSaldo());
        }

        for (Funcionario funcionario : new Funcionario[]{f1, f2, f3, f4}) {
            System.out.println(funcionario.getName() + " - Saldo Salário: R$ " + funcionario.getContaSalario().getSaldo());
            System.out.println(funcionario.getName() + " - Saldo Investimento: R$ " + funcionario.getContaInvestimento().getSaldo());
        }

        System.out.println("Saldo final da " + loja1.getNome() + ": R$ " + loja1.getConta().getSaldo());
        System.out.println("Saldo final da " + loja2.getNome() + ": R$ " + loja2.getConta().getSaldo());
    }
}
