package prova02.prova.service;

import prova02.prova.model.*;
import prova02.prova.util.Boletim;
import prova02.prova.util.Utilitario;

import java.util.*;

public class UrnaImpl implements Urna {

    private final Map<Integer, Partido> partidos = new HashMap<>();
    private final Map<Cargo, Map<Integer, Candidato>> candidatos = new EnumMap<>(Cargo.class);
    private final Map<Cargo, Integer> votosBrancos = new EnumMap<>(Cargo.class);
    private final Map<Cargo, Integer> votosNulos = new EnumMap<>(Cargo.class);
    private final Scanner scanner = new Scanner(System.in);

    public UrnaImpl() {
        carregar();
    }

    @Override
    public void carregar() {
        List<String> linhas = Utilitario.carregarCandidatos();

        for (int i = 1; i < linhas.size(); i++) {
            String linha = linhas.get(i);

            String[] dados = linha.split(";");
            if (dados.length < 5) continue;

            String nome = dados[0].replace("\"", "").trim();
            String nomePartido = dados[4].replace("\"", "").trim();
            int numero = Integer.parseInt(dados[3].trim());
            Cargo cargo = Cargo.fromDescricao(dados[2].replace("\"", "").trim());

            int numeroPartido = Integer.parseInt(String.valueOf(numero).substring(0, 2));
            Partido partido = partidos.computeIfAbsent(numeroPartido, n -> new Partido(numeroPartido, nomePartido));
            Candidato candidato = new Candidato(nome, numero, cargo, partido);

            partido.addCandidato(candidato);
            candidatos.computeIfAbsent(cargo, k -> new HashMap<>()).put(numero, candidato);
        }

        imprimirPartidos();
        imprimirCandidatos();
    }

    private void imprimirPartidos() {
        System.out.println("\n--------------------------PARTIDOS--------------------------");
        System.out.println("Número  Nome");
        partidos.values().forEach(p -> System.out.printf("%-8d%s%n", p.getNumero(), p.getNome()));
    }

    private void imprimirCandidatos() {
        for (Cargo cargo : Cargo.values()) {
            System.out.printf("%n-------------------- CANDIDATOS A %s --------------------%n", cargo);
            for (Partido partido : partidos.values()) {
                for (Candidato candidato : partido.getCandidatos(cargo)) {
                    System.out.printf("%-30s %-20s %-10d %s%n",
                            candidato.getNome(),
                            cargo,
                            candidato.getNumero(),
                            partido.getNome());
                }
            }
        }
    }

    @Override
    public void votar() {
        for (Cargo cargo : Cargo.values()) {
            boolean confirmado = false;
            while (!confirmado) {
                System.out.printf("%n%s [número | BRANCO]: ", cargo.name().replace("_", " "));
                String entrada = scanner.nextLine().trim();

                if (entrada.equalsIgnoreCase("BRANCO")) {
                    System.out.println("VOTO EM BRANCO");
                    confirmado = confirmar();
                    if (confirmado) {
                        votosBrancos.merge(cargo, 1, Integer::sum);
                    }
                } else {
                    try {
                        int numero = Integer.parseInt(entrada);
                        Candidato candidato = candidatos.getOrDefault(cargo, Map.of()).get(numero);
                        if (candidato != null) {
                            System.out.printf("Nome: %-25s Partido: %s (%d)%n",
                                    candidato.getNome(), candidato.getPartido().getNome(), candidato.getPartido().getNumero());
                            confirmado = confirmar();
                            if (confirmado) candidato.registrarVoto();
                        } else {
                            System.out.println("VOTO NULO");
                            confirmado = confirmar();
                            if (confirmado) {
                                votosNulos.merge(cargo, 1, Integer::sum);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida.");
                    }
                }
            }
        }
    }

    private boolean confirmar() {
        System.out.print("[CORRIGE | CONFIRMA]: ");
        String opcao = scanner.nextLine().trim().toUpperCase();
        return opcao.equals("CONFIRMA");
    }

    @Override
    public void imprimirBoletim() {
        for (Cargo cargo : Cargo.values()) {
            StringBuilder sb = new StringBuilder();

            sb.append("=".repeat(50)).append("\n")
                    .append(String.format("%40s%n%n", "Boletim de Urna"))
                    .append(String.format("%s%n", "-".repeat(30) + cargo.name().replace("_", " ") + "-".repeat(30)))
                    .append(String.format("%-30s %-10s %s%n", "Nome", "Número", "Votos"));

            int votosNominais = 0;
            Map<Integer, Candidato> mapa = candidatos.getOrDefault(cargo, Map.of());

            for (Candidato c : mapa.values()) {
                if (c.getVotos() > 0) {
                    sb.append(String.format("%-30s %-10d %d%n", c.getNome(), c.getNumero(), c.getVotos()));
                    votosNominais += c.getVotos();
                }
            }

            int brancos = votosBrancos.getOrDefault(cargo, 0);
            int nulos = votosNulos.getOrDefault(cargo, 0);
            int total = votosNominais + brancos + nulos;

            sb.append("-".repeat(66)).append("\n")
                    .append("Total de votos Nominais: ").append(votosNominais).append("\n")
                    .append("Brancos: ").append(brancos).append("\n")
                    .append("Nulos: ").append(nulos).append("\n")
                    .append("Total apurado: ").append(total).append("\n")
                    .append("-".repeat(66)).append("\n");

            Boletim.emitir(sb.toString());
        }
    }
}
