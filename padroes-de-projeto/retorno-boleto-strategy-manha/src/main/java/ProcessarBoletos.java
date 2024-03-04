import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

/**
 * @author Manoel Campos
 */
@Setter
public class ProcessarBoletos {

    static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Function<String, List<Boleto>> leituraRetorno;

    public ProcessarBoletos(Function<String, List<Boleto>> leituraRetorno) {
        this.leituraRetorno = leituraRetorno;
    }

    void processar(String nomeArquivo){
        var lista = leituraRetorno.apply(nomeArquivo);
        System.out.println(lista);
    }
}
