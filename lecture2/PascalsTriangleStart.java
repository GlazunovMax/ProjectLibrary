package by.homework.lecture2;

import java.util.Arrays;

/**
 Написать программу, выводящую на экран первые N строк Треугольника Паскаля.
 1 < N < 10

 */
public class PascalsTriangleStart {
    public static void main(String[] args) {

     int n =0;
        int k = n + 1;


       PascalTriangle d = new PascalTriangle();
        int tr = d.fact(n)/(d.fact(k)*d.fact(n-k));

        for (int i = 1; i <= 10 ; i++) {
            for (int j = 1; j <= 10; j++) {
                if(j<=i)
                System.out.print(tr);
            }
            System.out.println();

        }



    }

}
