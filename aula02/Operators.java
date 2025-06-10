package aula02;

public class Operators {
    public static void main(String[] args) {
        int x = 10 + 1;
        long y = x - 6;
        long z = x * y;
        float r = (float) x / y;
        double w = x % r;
        System.out.println("w = " + w);

        int num = 20;
        num += 20;
        num -= 10;
        num *= 2;
        num /= 6;
        num %= 3;

        System.out.println("num = " + num);

        int num1 = 1;
        int num2 = 2;
        if(num1 == num2){ System.out.println("num1 and num2 are equal"); }
        if(num1 != num2){System.out.println("num1 and num2 are not equal");}
        if(num1 < num2){System.out.println("num1 is less than num2");}
        if(num1 > num2){System.out.println("num1 is grater than num2");}
        if(num1 <= num2){System.out.println("num1 is less than or equal to num2");}
        if(num1 >= num2){System.out.println("num1 is greater than or equal to num2");}

        boolean a = true;  boolean b = false;
        if(a || b){System.out.println("num1 is true or num2 is true or both are true");}
        if(a && b){System.out.println("num1 and num2 are true");}
    }
}
