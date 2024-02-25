package org.example;

import org.example.service.ProcessarBoletosService;
import org.example.strategy.LeituraRetornoBancoBrasil;
import org.example.strategy.LeituraRetornoBradesco;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws URISyntaxException {
        var processarBB = new ProcessarBoletosService(new LeituraRetornoBancoBrasil());
        var processarBradesco = new ProcessarBoletosService(new LeituraRetornoBradesco());

        URI caminhoArquivoBB = Paths.get("banco-brasil-1.csv").toUri();
        URI caminhoArquivoBradesco = Paths.get("bradesco-1.csv").toUri();

        Scanner scanner = new Scanner(System.in);

        boolean condicao = true;

        while (condicao) {
            menu();
            System.out.print("Informe uma opção: ");

            if (scanner.hasNextInt()) {
                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println("Lendo arquivo BB:" + caminhoArquivoBB + "\n");
                        processarBB.processar(caminhoArquivoBB);
                        break;
                    case 2:
                        System.out.println("\nLendo arquivo Bradesco:" + caminhoArquivoBradesco + "\n");
                        processarBradesco.processar(caminhoArquivoBradesco);
                        break;
                    case 3:
                        System.out.printf("\nLendo arquivo BB %s\nBradesco: %s", caminhoArquivoBB , caminhoArquivoBradesco, "\n");
                        processarBB.processar(caminhoArquivoBB);
                        processarBradesco.processar(caminhoArquivoBradesco);
                        break;
                    case 0:
                        condicao = false;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } else {
                System.out.println("Entrada inválida! Por favor, digite um número.");
                scanner.next(); // Limpa o buffer do scanner
            }
        }
    }//main

    private static void menu(){
        System.out.println("\n------- Buscador de boletos ----------");
        System.out.println("1 - Boletos Banco do Brasil");
        System.out.println("2 - Boletos Banco do Bradesco");
        System.out.println("3 - Todos os Boletos Banco do Brasil + Bradesco");
        System.out.println("0 - Sair");
    }
}//class