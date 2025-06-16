package util;

import java.util.Scanner;

public class Entrada {
    private static Scanner scanner = new Scanner(System.in);

    public static String lerTexto(String mensagem) {
        System.out.print(mensagem + ": ");
        return scanner.nextLine();
    }

    public static int lerInt(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem + ": ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    public static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem + ": ");
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }
}
