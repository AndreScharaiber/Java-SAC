import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {

    private static String obterDataHoraAtual() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        return sdf.format(new Date());
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HistoricoSolicitacoes pilhaHistorico = new HistoricoSolicitacoes();
        FilaAtendimento filaClientes = new FilaAtendimento();

        System.out.println("- Gerênciamento do SAC -");
        int opcao = -1;

        while (opcao != 9) {
            System.out.println("1. Adc. Solicitação");
            System.out.println("2. Rmv. Solicitação");
            System.out.println("3. Adc. Cliente");
            System.out.println("4. Atender Cliente");
            System.out.println("5. Visualizar Histórico");
            System.out.println("6. Visualizar Fila de Atendimento");
            System.out.println("9. Sair");
            System.out.print("Opção: ");
            String input = scanner.nextLine();
            opcao = Integer.parseInt(input);

            if (opcao == 1) {
                System.out.print("ID: ");
                String idSolicitacao = scanner.nextLine();
                System.out.print("Descrição: ");
                String descricaoSolicitacao = scanner.nextLine();

                Solicitacao novaSol = new Solicitacao(
                        idSolicitacao,
                        descricaoSolicitacao,
                        obterDataHoraAtual()
                );
                pilhaHistorico.Adicionar(novaSol);
                System.out.println("Solicitação " + idSolicitacao);
            } else if (opcao == 2) {
                pilhaHistorico.remover();
            } else if (opcao == 3) {
                System.out.print("Nome do Cliente: ");
                String nomeCliente = scanner.nextLine();
                System.out.print("Identificação: ");
                String idCliente = scanner.nextLine();
                System.out.print("Motivo do Atendimento: ");
                String motivoCliente = scanner.nextLine();
                Cliente novoCli = new Cliente(nomeCliente, idCliente, motivoCliente);
                filaClientes.ColocarFila(novoCli);
                System.out.println("Adicionado: " + nomeCliente);
            } else if (opcao == 4) {
                filaClientes.TirarFila();
            } else if (opcao == 5) {
                System.out.println("- Histórico de Sol. -");
                pilhaHistorico.Visualizar();
            } else if (opcao == 6) {
                System.out.println("- Fila de Atendimento - ");
                filaClientes.visualizar();
            } else if (opcao == 9) {
                System.out.println("Bye-bye!");
            } else {
                System.out.println("Inválido");
            }
        } scanner.close();
    }
}
class No {
    Object dado;
    No proximo;

    public No(Object dado) {
        this.dado = dado;
        this.proximo = null;
    }
}

class Solicitacao {
    String id;
    String descricao;
    String dataHora;

    public Solicitacao(String id, String descricao, String dataHora) {
        this.id = id;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    public String toString() {
        return "ID: " + id + " / Descrição: " + descricao + "/ Data: " + dataHora;
    }
}

class Cliente {
    String nome;
    String identificacao;
    String motivo;

    public Cliente(String nome, String identificacao, String motivo) {
        this.nome = nome;
        this.identificacao = identificacao;
        this.motivo = motivo;
    }

    public String toString() {
        return "Nome: " + nome + " /  ID: " + identificacao + " /  Motivo: " + motivo;
    }
}

class HistoricoSolicitacoes {
    private No topo;

    public HistoricoSolicitacoes() {
        this.topo = null;
    }

    public boolean EstaVazia() {
        return topo == null;
    }

    public void Adicionar(Solicitacao novaSolicitacao) {
        No novoNo = new No(novaSolicitacao);
        novoNo.proximo = topo;
        topo = novoNo;
    }

    public Solicitacao remover() {
        if (EstaVazia()) {
            System.out.println("Vazio.");
            return null;
        }

        Solicitacao solicitacaoRemovida = (Solicitacao)topo.dado;
        topo = topo.proximo;

        System.out.println("Removida: " + solicitacaoRemovida.id);
        return solicitacaoRemovida;
    }

    public void Visualizar() {
        if (EstaVazia()) {
            System.out.println("Vazio");
            return;
        }

        No atual = topo;
        int contador = 1;
        while (atual != null) {
            System.out.println(contador + "," + atual.dado.toString());
            atual = atual.proximo;
            contador++;
        }
    }
}

class FilaAtendimento {
    private No frente;
    private No tras;

    public FilaAtendimento() {
        this.frente = null;
        this.tras = null;
    }

    public boolean estaVazia() {
        return frente == null;
    }

    public void ColocarFila(Cliente novoCliente) {
        No novoNo = new No(novoCliente);

        if (estaVazia()) {
            frente = novoNo;
            tras = novoNo;
        } else {
            tras.proximo = novoNo;
            tras = novoNo;
        }
    }

    public Cliente TirarFila() {
        if (estaVazia()) {
            System.out.println("Vazia");
            return null;
        }

        Cliente clienteAtendido = (Cliente)frente.dado;
        frente = frente.proximo;

        if (frente == null) {
            tras = null;
        }

        System.out.println("Cliente Atendido: " + clienteAtendido.nome);
        return clienteAtendido;
    }

    public void visualizar() {
        if (estaVazia()) {
            System.out.println("Vazia");
            return;
        }

        No atual = frente;
        int contador = 1;
        while (atual != null) {
            System.out.println("Posição " + contador + ": " + atual.dado.toString());
            atual = atual.proximo;
            contador++;
        }
    }
}