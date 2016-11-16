package by.homework.lecture2;

import java.util.Scanner;

/**
 Написать программу, которая принимает на вход три целых числа:
 1.	algorithmId - тип алгоритма (1 - вычисление ряда чисел Фибоначчи; 2 - вычисление факториала);
 2.	loopType - тип циклов, которые нужно использовать (1 - цикл while; 2 - цикл do-while; 3 - цикл for);
 3.	n - параметр, передаваемый в алгоритм.

 К примеру, если передаются числа “1 3 5”, программе необходимо вывести на экран первые 5 чисел Фибоначчи и при вычислении использовать цикл for.
 К примеру, если передаются числа “2 1 7”, программе необходимо вывести на экран факториал числа 7 и при его вычислении использовать цикл while.

 */
public class FibonacciNumbers {
    public static void main(String[] args) {
       int[] number = new int[3];
        for (int i = 0; i < number.length ; i++) {
            number[i] = (int)(Math.random()*10);
            System.out.println(number[i] + " ");
        }
        for (int i = 1; i < 6; i++) {
            int feb = number[1] + number[2];
            System.out.println(i + "-ое(-тье) число Фибоначчи" + " " + feb);
           number[1] = number[2];
            number[2] = feb;

        }

    }
}
