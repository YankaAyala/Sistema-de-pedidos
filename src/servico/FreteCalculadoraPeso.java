package servico;

public class FreteCalculadoraPeso implements FreteCalculadora {
    public double calcular(double pesoTotal) {
        return pesoTotal * 5.0;
    }
}
