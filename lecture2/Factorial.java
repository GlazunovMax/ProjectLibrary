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
public class Factorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите число а ");
        int a = sc.nextInt();
            System.out.println("Введите число b ");
            int b = sc.nextInt();
                System.out.println("Введите число c ");
                int c = sc.nextInt();

        int max;
        if(a > b && a > c){
           max = a;
        }else if(b > a && b > c){
            max = b;
        }else if(c > a && c > b){
            max = c;
        }else{
            max = a;
        }
        System.out.println("Максимальное число " + max);


           int fact = 1;
           int j = max;
        while (j>0){
            fact *= max;
            max--;
            j--;
        }
        System.out.println("И его факториал равен "+ " "+ fact);
       }

}
