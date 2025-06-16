package servico;

public class FreteCalculadoraDistancia implements FreteCalculadora {
    private double distanciaKm;

    public FreteCalculadoraDistancia(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public double calcular(double ignorado) {
        return distanciaKm * 0.5;
    }
}
