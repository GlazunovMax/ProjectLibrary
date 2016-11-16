package by.homework;

import by.homework.NumberGg;

import java.util.Scanner;

/**
 * Created by ANTONY on 14.10.2016.
 */
public class StartNumbersGg {
    public static void main(String[] args) {

       Scanner sc = new Scanner(System.in);

        System.out.println("Введите целое число а: ");
        int a1 = sc.nextInt();
        System.out.println("Введите целое число p: ");
        int p1 = sc.nextInt();
        System.out.println("Введите дробное число m: ");
        double m1 = sc.nextDouble();
        System.out.println("Введите дробное число m1: ");
       double m11 = sc.nextDouble();

        NumberGg G = new NumberGg(a1, p1, m1, m11);
        G.resultG();

    }
}

