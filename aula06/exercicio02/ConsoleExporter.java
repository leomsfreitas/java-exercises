package aula06.exercicio02;

public class ConsoleExporter implements Exporter{

    @Override
    public void export(String content, String destination) {
        System.out.println(content);
    }
}