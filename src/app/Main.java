package app;

import model.*;
import servico.*;
import relatorio.*;
import util.Entrada;

import java.util.*;

public class Main {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Produto> produtos = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        boolean executando = true;

        while (executando) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar produto");
            System.out.println("3. Criar pedido");
            System.out.println("4. Gerar relatório");
            System.out.println("5. Sair");
            System.out.println();
            int opcao = Entrada.lerInt("Escolha uma opção");

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> cadastrarProduto();
                case 3 -> criarPedido();
                case 4 -> gerarRelatorio();
                case 5 -> {
                    System.out.println("Encerrando o sistema.");
                    executando = false;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarCliente() {
        String nome = Entrada.lerTexto("Nome");
        String cpf = Entrada.lerTexto("CPF");
        String email = Entrada.lerTexto("Email");
        clientes.add(new Cliente(nome, cpf, email));
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void cadastrarProduto() {
        String nome = Entrada.lerTexto("Nome do produto");
        double preco = Entrada.lerDouble("Preço");
        double peso = Entrada.lerDouble("Peso (kg)");
        produtos.add(new Produto(nome, preco, peso));
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void criarPedido() {
        if (clientes.isEmpty() || produtos.isEmpty()) {
            System.out.println("Cadastre ao menos um cliente e um produto antes de criar pedidos.");
            return;
        }

        System.out.println("Clientes cadastrados:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNome());
        }

        int clienteIndex;
        while (true) {
            clienteIndex = Entrada.lerInt("Escolha o índice do cliente") - 1;
            System.out.println();
            if (clienteIndex == -1) {
                System.out.println("Criação do pedido cancelada.");
                return;
            }
            if (clienteIndex >= 0 && clienteIndex < clientes.size()) break;
            System.out.println("Índice de cliente inválido. Tente novamente.");
        }

        Cliente cliente = clientes.get(clienteIndex);
        Pedido pedido = new Pedido(cliente);

        boolean adicionando = true;
        while (adicionando) {
            System.out.println("Produtos cadastrados:");
            for (int i = 0; i < produtos.size(); i++) {
                System.out.println((i + 1) + ". " + produtos.get(i).getNome());
            }

            int prodIndex;
            while (true) {
                prodIndex = Entrada.lerInt("Escolha o índice do produto") - 1;
                if (prodIndex == -1) {
                    System.out.println("Criação do pedido cancelada.");
                    return;
                }
                if (prodIndex >= 0 && prodIndex < produtos.size()) break;
                System.out.println("Índice de produto inválido. Tente novamente.");
            }

            int quantidade = Entrada.lerInt("Quantidade");
            System.out.println();
            if (quantidade == 0) {
                System.out.println("Criação do pedido cancelada.");
                return;
            }

            pedido.adicionarItem(new ItemPedido(produtos.get(prodIndex), quantidade));

            String addProduto;
            while (true) {
                addProduto = Entrada.lerTexto("Deseja adicionar outro produto? (s/n)");
                if (addProduto.equalsIgnoreCase("s")) {
                    adicionando = true;
                    break;
                } else if (addProduto.equalsIgnoreCase("n")) {
                    System.out.println();
                    adicionando = false;
                    break;
                } else {
                    System.out.println("Opção inválida. Digite 's' para sim ou 'n' para não.");
                }
            }

        }

        int tipoFrete;
        while (true) {
            System.out.println("Escolha a forma de cálculo de frete:");
            System.out.println("1. Por peso (R$5,00 por kg)");
            System.out.println("2. Por distância (R$0,50 por km)");
            System.out.println("0. Para cancelar");
            tipoFrete = Entrada.lerInt("Opção");
            System.out.println();

            if (tipoFrete == 0) {
                System.out.println("Criação do pedido cancelada.");
                return;
            } else if (tipoFrete == 1) {
                pedido.setFreteCalculadora(new FreteCalculadoraPeso());
                break;
            } else if (tipoFrete == 2) {
                double distancia = Entrada.lerDouble("Informe a distância (km)");
                pedido.setFreteCalculadora(new FreteCalculadoraDistancia(distancia));
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        pedido.calcularTotais();

        int tipoNotificacao;
        while (true) {
            System.out.println("Escolha o tipo de notificação:");
            System.out.println("1. E-mail");
            System.out.println("2. SMS");
            System.out.println("3. WhatsApp");
            System.out.println("0. Para cancelar)");
            System.out.println();
            tipoNotificacao = Entrada.lerInt("Opção");

            if (tipoNotificacao == 0) {
                System.out.println("Criação do pedido cancelada.");
                return;
            }

            switch (tipoNotificacao) {
                case 1 -> {
                    new NotificadorEmail().notificar(cliente);
                    break;
                }
                case 2 -> {
                    new NotificadorSMS().notificar(cliente);
                    break;
                }
                case 3 -> {
                    new NotificadorWhatsApp().notificar(cliente);
                    break;
                }
                default -> System.out.println("Tipo de notificação inválido. Tente novamente.");
            }

            if (tipoNotificacao >= 1 && tipoNotificacao <= 3) break;
        }

        pedidos.add(pedido);
        System.out.println("Pedido criado com sucesso!");
    }

    private static void gerarRelatorio() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido foi feito ainda.");
            return;
        }

        for (int i = 0; i < pedidos.size(); i++) {
            System.out.println((i + 1) + ". Pedido de " + pedidos.get(i).getCliente().getNome());
        }

        int indice;
        do {
            indice = Entrada.lerInt("Escolha o índice do pedido para gerar relatório") - 1;
            if (indice < 0 || indice >= pedidos.size()) {
                System.out.println("Índice de pedido inválido. Tente novamente.");
            }
        } while (indice < 0 || indice >= pedidos.size());

        int tipo;
        do {
            System.out.println("Escolha o formato do relatório:");
            System.out.println("1. Texto");
            System.out.println("2. JSON");
            tipo = Entrada.lerInt("Opção");
            System.out.println();

            if (tipo == 1) {
                new RelatorioTexto().gerar(pedidos.get(indice));
            } else if (tipo == 2) {
                new RelatorioJSON().gerar(pedidos.get(indice));
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while (tipo != 1 && tipo != 2);
    }
}
