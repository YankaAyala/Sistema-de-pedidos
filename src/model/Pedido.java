package model;

import servico.*;
import java.util.*;

public class Pedido {
    private Cliente cliente;
    private List<ItemPedido> itens = new ArrayList<>();
    private double total;
    private double frete;
    private double totalComFrete;
    private FreteCalculadora freteCalculadora;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public void setFreteCalculadora(FreteCalculadora calculadora) {
        this.freteCalculadora = calculadora;
    }

    public void calcularTotais() {
        total = itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
        double pesoTotal = itens.stream().mapToDouble(ItemPedido::getPesoTotal).sum();
        frete = freteCalculadora.calcular(pesoTotal);
        totalComFrete = total + frete;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public double getTotal() {
        return total;
    }

    public double getFrete() {
        return frete;
    }

    public double getTotalComFrete() {
        return totalComFrete;
    }
}
