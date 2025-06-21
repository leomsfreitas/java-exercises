package prova02.prova.util;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Utilitario {
    private static final String ARQUIVO = "src/main/java/prova02/prova/candidatos.csv";

    public static List<String> carregarCandidatos() {
        try {
            return Files.readAllLines(Paths.get(ARQUIVO));
        } catch (IOException e) {
            System.err.println("Arquivo de candidatos não encontrado: " + ARQUIVO);
            return Collections.emptyList();
        }
    }
}