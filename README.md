# Sistema-de-pedidos
Projeto da primeira entrega da disciplina Análise e Projeto de Sistemas (ADS - IFPE). Simula uma loja virtual via terminal, com foco no funcionamento básico. Será refatorado futuramente com a aplicação de padrões de projeto estudados na disciplina.

Sistema de Pedidos

Este é um sistema simples de pedidos desenvolvido em Java com foco em orientação a objetos. Ele simula uma loja virtual via terminal, permitindo o cadastro de clientes e produtos, criação de pedidos com cálculo de frete e envio de notificações. O sistema também gera relatórios em formato de texto ou JSON.

Como Executar

1. Requisitos

- Java 17 ou superior
- IDE ou terminal com suporte a projetos Java (como IntelliJ, VS Code ou Eclipse)

2. Estrutura do Projeto

projeto/
├── app/
│ └── Main.java
├── model/
│ ├── Cliente.java
│ ├── Produto.java
│ ├── Pedido.java
│ └── ItemPedido.java
├── servico/
│ ├── FreteCalculadora.java
│ ├── FreteCalculadoraPeso.java
│ ├── FreteCalculadoraDistancia.java
│ ├── Notificador.java
│ ├── NotificadorEmail.java
│ ├── NotificadorSMS.java
│ └── NotificadorWhatsApp.java
├── relatorio/
│ ├── Relatorio.java
│ ├── RelatorioTexto.java
│ └── RelatorioJSON.java
└── util/
└── Entrada.java

3. Compilar e Executar

Se estiver usando terminal:
'''bash
javac app/Main.java
java app.Main
Ou, se estiver usando uma IDE, execute a classe Main.java.

Navegação no Menu

--- Menu Principal ---
1. Cadastrar cliente
2. Cadastrar produto
3. Criar pedido
4. Gerar relatório
5. Sair
   
Durante o pedido:

- Selecione um cliente.
- Adicione produtos e quantidades.
- Escolha o tipo de frete:
  Por peso (R$5,00 por kg)
  Por distância (R$0,50 por km)
- Escolha o tipo de notificação:
  E-mail
  SMS
  WhatsApp
- Digitar 0 em qualquer etapa cancela o processo atual.

Funcionalidades

-Cadastro de clientes
-Cadastro de produtos
-Criação de pedidos
-Escolha de forma de frete (por peso ou distância)
-Escolha de tipo de notificação (e-mail, SMS ou WhatsApp)
-Geração de relatórios em texto ou JSON
-Sistema totalmente interativo via terminal

Validações

-Entradas inválidas exibem mensagens apropriadas e solicitam a entrada novamente.
-É possível o cancelamento da criação do pedido a qualquer momento digitando `0`.

Tecnologias Utilizadas
-Java 17
-Programação Orientada a Objetos (POO)
-Entrada de dados via terminal (scanner personalizado)
-Polimorfismo e interfaces (para cálculo de frete e notificações)

Desafios encontrados

-Controle de fluxo com menus e repetição de escolhas, onde foi necessário implementar menus que:
  Repetem a pergunta quando o usuário digita algo inválido;
  Permitem cancelar o processo de criação de pedido a qualquer momento;
  Fazem validações contínuas sem travar o programa.
-Porque exige lógica de repetição (while), uso de flags e verificação de condições para cada etapa do processo (cliente, produto, frete, notificação).
