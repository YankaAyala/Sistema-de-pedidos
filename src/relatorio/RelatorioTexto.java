package relatorio;

import model.Pedido;
import model.ItemPedido;

public class RelatorioTexto {
    public void gerar(Pedido pedido) {
        System.out.println("Cliente: " + pedido.getCliente().getNome());
        System.out.println("Produtos:");
        for (ItemPedido item : pedido.getItens()) {
            System.out.println("- " + item.getProduto().getNome() + " (" + item.getQuantidade() + "x) - R$ " + item.getSubtotal());
        }
        System.out.printf("Total: R$ %.2f\n", pedido.getTotal());
        System.out.printf("Frete: R$ %.2f\n", pedido.getFrete());
        System.out.printf("Total com frete: R$ %.2f\n", pedido.getTotalComFrete());
    }
}
