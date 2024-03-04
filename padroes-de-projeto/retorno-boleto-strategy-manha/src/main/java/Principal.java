/**
 * @author Manoel Campos
 */
public class Principal {
    public static void main(String[] args) {
        final var processarBoletos =
            new ProcessarBoletos(LeituraRetornoBancoBrasil::lerArquivo);
        processarBoletos.processar("banco-brasil-1.csv");
    }
}











