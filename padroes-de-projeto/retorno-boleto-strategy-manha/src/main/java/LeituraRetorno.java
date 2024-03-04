import java.util.List;

/**
 * @author Manoel Campos
 */
public interface LeituraRetorno {
    List<Boleto> lerArquivo(String nomeArquivo);
}
