package by.homework.lecture2;

/**
 Написать программу, выводящую на экран первые N строк Треугольника Паскаля.
 1 < N < 10

 */
public class PascalTriangle{


    public int fact(int num){
        int val = 1;
        for (int i = 2; i <= num; i++) {
            val = val*i;
        }
        return val;
    }
}
