public class Loja {
    private final String nome;
    private final Conta conta;
    private final Banco banco;

    public Loja(String nome, Banco banco) {
        this.nome = nome;
        this.banco = banco;
        this.conta = new Conta(nome);
    }

    public Conta getConta() {
        return conta;
    }

    public String getNome() {
        return nome;
    }

    public Banco getBanco() {
        return banco;
    }
}
