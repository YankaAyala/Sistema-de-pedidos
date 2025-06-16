package servico;

import model.Cliente;

public class NotificadorSMS {
    public void notificar(Cliente cliente) {
        System.out.println("Seu pedido foi confirmado! Enviando SMS para " + cliente.getNome());
    }
}