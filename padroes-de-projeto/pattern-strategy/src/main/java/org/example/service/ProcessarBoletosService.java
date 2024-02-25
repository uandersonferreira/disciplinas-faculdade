package org.example.service;

import lombok.AllArgsConstructor;
import org.example.context.Boleto;
import org.example.interfaces.LeituraRetorno;

import java.net.URI;
import java.util.List;
@AllArgsConstructor

public class ProcessarBoletosService {
    private final LeituraRetorno leituraRetorno;

    public void processar(URI nomeArquivo){
        System.out.println("Boletos");
        System.out.println("----------------------------------------------------------------------------------");
        List<Boleto> boletos = leituraRetorno.lerArquivo(nomeArquivo);
        if (!boletos.isEmpty()){
            for (Boleto boleto : boletos) {
                System.out.println(boleto);
            }
        }else {
            System.out.println("Não há boletos para serem processados...");
        }


    }


}//class
