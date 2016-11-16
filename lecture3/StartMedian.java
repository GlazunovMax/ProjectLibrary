package by.homework.lecture3;

import java.util.Arrays;

/**
 1.	Написать утилитарный класс Median который содержит перегруженные методы для расчета
 медианы массива целых числе и массива чисел типа double.
 Класс не должен допускать создание своего экземпляра.

 */
public class StartMedian {
    public static void main(String[] args) {

       ExMedian sm1 = new ExMedian();
           sm1.resultMedianArray(new int[7]);

            ExMedian sm2 = new ExMedian();
                sm2.resultMedianArray(new double[4]);




    }


}
