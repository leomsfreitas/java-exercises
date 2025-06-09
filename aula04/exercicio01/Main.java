package aula04.exercicio01;

public class Main {
    public static void main(String[] args) {
        WordCounter wc = new WordCounter();
        String texto = "java é divertido e java é poderoso";
        String palavra = "java";
        int contagem = wc.countSubstring(texto, palavra);
        System.out.printf("A palavra '%s' aparece %d vezes em '%s'.%n", palavra, contagem, texto);

        EmailValidator validator = new EmailValidator("exemplo.com");
        String email1 = "usuario@exemplo.com";
        String email2 = "usuario@xxx.com";
        System.out.printf("O email '%s' é válido? %s%n%n", email1, validator.isValidEmail(email1));
        System.out.printf("O email '%s' é válido? %s%n", email2, validator.isValidEmail(email2));
    }
}