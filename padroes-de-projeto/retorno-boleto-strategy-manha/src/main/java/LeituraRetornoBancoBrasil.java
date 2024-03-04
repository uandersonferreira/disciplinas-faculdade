import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LeituraRetornoBancoBrasil {
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @SneakyThrows
    public static List<Boleto> lerArquivo(String nomeArquivo) {
        var boletos = new ArrayList<Boleto>();  // adicionar
        var linhas = Files.readAllLines(Path.of(nomeArquivo));
        for (String linha : linhas) {
            String[] vetor = linha.split(";");
            var boleto = new Boleto();
            boleto.setId(Integer.parseInt(vetor[0]));
            boleto.setCodBanco(vetor[1]);
            boleto.setDataVencimento(LocalDate.parse(vetor[2], FORMATO_DATA));
            boleto.setDataPagamento(LocalDate.parse(vetor[3], FORMATO_DATA).atStartOfDay()); // adicionar
            // TODO: incluir os outros campos
            boletos.add(boleto); // adicionar
        }

        return boletos;  // adicionar
    }
}



















