package servico;

import model.Cliente;

public class NotificadorWhatsApp {
    public void notificar(Cliente cliente) {
        System.out.println("Seu pedido foi confirmado! Enviando WhatsApp para " + cliente.getNome());
    }
}
