import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transacao {
    private String tipo;  
    private double valor;
    private String descricao;

    public Transacao(String tipo, double valor, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return tipo + ": " + valor + " - " + descricao;
    }
}


class ControleFinanceiro {
    private List<Transacao> transacoes;


    public ControleFinanceiro() {
        this.transacoes = new ArrayList<>();
    }


    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }


    public void editarTransacao(int index, Transacao transacao) {
        if (index >= 0 && index < transacoes.size()) {
            transacoes.set(index, transacao);
        } else {
            System.out.println("Índice inválido.");
        }
    }


    public void excluirTransacao(int index) {
        if (index >= 0 && index < transacoes.size()) {
            transacoes.remove(index);
        } else {
            System.out.println("Índice inválido.");
        }
    }


    public void exibirTransacoes() {
        for (int i = 0; i < transacoes.size(); i++) {
            System.out.println(i + " - " + transacoes.get(i));
        }
    }

    // Método para salvar as transações em um arquivo
    public void salvarTransacoesEmArquivo(String nomeArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Transacao transacao : transacoes) {
                writer.write(transacao.toString());
                writer.newLine();
            }
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ControleFinanceiro controleFinanceiro = new ControleFinanceiro();

        while (true) {
            System.out.println("Escolha uma opção: [1] Adicionar Transação [2] Editar Transação [3] Excluir Transação [4] Exibir Transações [5] Salvar em Arquivo [6] Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 6) {
                break;
            }

            switch (opcao) {
                case 1:
                    System.out.println("Digite o tipo de transação (Receita/Despesa):");
                    String tipo = scanner.nextLine();

                    System.out.println("Digite o valor da transação:");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();  // Consumir a nova linha

                    System.out.println("Digite a descrição da transação:");
                    String descricao = scanner.nextLine();

                    Transacao transacao = new Transacao(tipo, valor, descricao);
                    controleFinanceiro.adicionarTransacao(transacao);
                    break;

                case 2:
                    System.out.println("Digite o índice da transação que deseja editar:");
                    int indexEditar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha

                    System.out.println("Digite o novo tipo de transação (Receita/Despesa):");
                    String novoTipo = scanner.nextLine();

                    System.out.println("Digite o novo valor da transação:");
                    double novoValor = scanner.nextDouble();
                    scanner.nextLine();  // Consumir a nova linha

                    System.out.println("Digite a nova descrição da transação:");
                    String novaDescricao = scanner.nextLine();

                    Transacao novaTransacao = new Transacao(novoTipo, novoValor, novaDescricao);
                    controleFinanceiro.editarTransacao(indexEditar, novaTransacao);
                    break;

                case 3:
                    System.out.println("Digite o índice da transação que deseja excluir:");
                    int indexExcluir = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha
                    controleFinanceiro.excluirTransacao(indexExcluir);
                    break;

                case 4:
                    controleFinanceiro.exibirTransacoes();
                    break;

                case 5:
                    System.out.println("Digite o nome do arquivo para salvar as transações:");
                    String nomeArquivo = scanner.nextLine();

                    try {
                        controleFinanceiro.salvarTransacoesEmArquivo(nomeArquivo);
                        System.out.println("Transações salvas com sucesso no arquivo " + nomeArquivo);
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar as transações no arquivo: " + e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        scanner.close();
    }
}
