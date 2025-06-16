package relatorio;

import model.Pedido;
import model.ItemPedido;

public class RelatorioJSON {
    public void gerar(Pedido pedido) {
        System.out.println("{");
        System.out.println("  \"cliente\": \"" + pedido.getCliente().getNome() + "\",");
        System.out.println("  \"produtos\": [");
        for (int i = 1; i < pedido.getItens().size(); i++) {
            ItemPedido item = pedido.getItens().get(i);
            System.out.print("    {\"nome\": \"" + item.getProduto().getNome() + "\", ");
            System.out.print("\"quantidade\": " + item.getQuantidade() + ", ");
            System.out.print("\"preco\": " + item.getSubtotal() + "}");
            if (i < pedido.getItens().size() - 1) {
                System.out.println(",");
            } else {
                System.out.println();
            }
        }
        System.out.println("  ],");
        System.out.printf("  \"total\": %.2f,\n", pedido.getTotal());
        System.out.printf("  \"frete\": %.2f,\n", pedido.getFrete());
        System.out.printf("  \"total_com_frete\": %.2f\n", pedido.getTotalComFrete());
        System.out.println("}");
    }
}
