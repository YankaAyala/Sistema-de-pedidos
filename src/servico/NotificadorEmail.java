package servico;

import model.Cliente;

public class NotificadorEmail {
    public void notificar(Cliente cliente) {
        System.out.println("Seu pedido foi confirmado! Enviando e-mail para " + cliente.getEmail());
    }
}
