package aula04.exercicio02;

public class NamingConventions {
    private NamingConventions() {
    }

    // camelCase, PascalCase, UPPER_SNAKE_CASE
    public static boolean isFollowingConvention(String str, Convention convention) {
        switch (convention) {
            case CLASS -> {
                if (str == null) return false;
                return Character.isUpperCase(str.charAt(0)) && !str.contains("_");

            }
            case METHOD, VARIABLE -> {
                if (str == null) return false;
                return Character.isLowerCase(str.charAt(0)) && !str.contains("_");
            }
            case CONSTANT -> {
                if (str == null) return false;
                if (str.startsWith("_") || str.endsWith("_")) return false;
                for (char c : str.toCharArray()) {
                    if (!(Character.isUpperCase(c) || c == '_')) return false;
                }
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    // UPPER_SNAKE_CASE -> camelCase
    public static String fromConstToVariable(String str){
        if (str == null) return null;

        String[] parts = str.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder(parts[0]);

        for (int i = 1; i < parts.length; i++) {
            sb.append(parts[i].substring(0, 1).toUpperCase())
                    .append(parts[i].substring(1));
        }
        return sb.toString();
    }

    // camelCase -> UPPER_SNAKE_CASE
    public static String fromVariableToConst(String str) {
        if (str == null) return null;

        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) sb.append("_");
            sb.append(Character.toUpperCase(c));
        }
        return sb.toString();
    }

    // Pode ser Letra, Dígito, _ ou $
    public static boolean isValidJavaIdentifier(String str) {
        if (str == null) return false;

        char first = str.charAt(0);
        if (!(Character.isLetter(first) || first == '_' || first == '$')) return false;

        for (char c : str.toCharArray()) {
            if (!(Character.isDigit(c) || Character.isLetter(c) || c == '_' || c == '$')) return false;
        }
        return true;
    }
}
