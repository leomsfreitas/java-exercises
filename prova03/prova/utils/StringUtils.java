package prova03.prova.utils;

import java.util.Objects;

public class StringUtils {
    private StringUtils(){}

    public static void validateString(String field){
        if(Objects.requireNonNull(field, "Field cannot be null").isBlank()){
            throw new IllegalArgumentException("Field cannot be blank");
        }

    }
}
