package aula04.exercicio02;

public class Main {
    public static void main(String[] args) {
        String classe = "MinhaClasse";
        String classeInvalida = "minhaClasse";

        String metodo = "meuMetodo";
        String metodoInvalido = "MeuMetodo";

        String variavel = "minhaVariavel";
        String variavelInvalida = "MINHA_VARIAVEL";

        String constante = "MINHA_CONSTANTE";
        String constanteInvalida = "minhaConstante";


        System.out.printf("Classe:    %-15s | Convenção Válida: %s%n", classe, NamingConventions.isFollowingConvention(classe, Convention.CLASS));
        System.out.printf("Classe:    %-15s | Convenção Válida: %s%n", classeInvalida, NamingConventions.isFollowingConvention(classeInvalida, Convention.CLASS));

        System.out.printf("Método:    %-15s | Convenção Válida: %s%n", metodo, NamingConventions.isFollowingConvention(metodo, Convention.METHOD));
        System.out.printf("Método:    %-15s | Convenção Válida: %s%n", metodoInvalido, NamingConventions.isFollowingConvention(metodoInvalido, Convention.METHOD));

        System.out.printf("Variável:  %-15s | Convenção Válida: %s%n", variavel, NamingConventions.isFollowingConvention(variavel, Convention.VARIABLE));
        System.out.printf("Variável:  %-15s | Convenção Válida: %s%n", variavelInvalida, NamingConventions.isFollowingConvention(variavelInvalida, Convention.VARIABLE));

        System.out.printf("Constante: %-15s | Convenção Válida: %s%n", constante, NamingConventions.isFollowingConvention(constante, Convention.CONSTANT));
        System.out.printf("Constante: %-15s | Convenção Válida: %s%n", constanteInvalida, NamingConventions.isFollowingConvention(constanteInvalida, Convention.CONSTANT));

        System.out.printf("\nVariável para Constante: %s -> %s%n", variavel, NamingConventions.fromVariableToConst(variavel));
        System.out.printf("\nConstante para Variável: %s -> %s%n", constante, NamingConventions.fromConstToVariable(constante));
    }
}
