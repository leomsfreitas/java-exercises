package aula02;

public class DataTypes {
    public static void main(String[] args) {
        int i;
        i = 10;

        String name = "Alan Turing";

        System.out.println("i = " + i);
        System.out.println("name = " + name);

        double d = i;
        d = 3.5;
        i = (int) d;

        System.out.println("i = " + i);

        byte varByte = -128;
        varByte = 127;
        short varShort = -32768;
        varShort = 32767;
        int varInt = -2147483648;
        varInt = 2147483647;

        long varLong = -9223372036854775808L;
        varLong = 9223372036854775807L;

        float varFloat = -100.4345F;
        varFloat = 123243.4345f;

        double varDouble = -3123.434354;
        varDouble = 321321.3123435;
        boolean varBoolean = false;
        varBoolean = true;

        char varChar = 'a';
        varChar = 90;

        System.out.println("varInt = " + varInt);
        System.out.println("varShort = " + varShort);
        System.out.println("varLong = " + varLong);
        System.out.println("varChar = " + varChar);
        System.out.println("varByte = " + varByte);
        System.out.println("varFloat = " + varFloat);
        System.out.println("varDouble = " + varDouble);
        System.out.println("varBoolean = " + varBoolean);

        final boolean CONST_1;
        final boolean CONST_2 = true;
        CONST_1 = false;

        System.out.println("CONST_1 = " + CONST_1);
        System.out.println("CONST_2 = " + CONST_2);

    }
}
