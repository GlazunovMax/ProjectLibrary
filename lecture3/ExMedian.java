package by.homework.lecture3;


import java.util.Arrays;
import java.util.Scanner;

/**
 1.	Написать утилитарный класс Median который содержит перегруженные методы для расчета
 медианы массива целых числе и массива чисел типа double.
 Класс не должен допускать создание своего экземпляра.

 */
public class ExMedian extends Median {

    @Override
    void resultMedianArray(int[] massifs) {
        System.out.println("Введите размерность массива");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        massifs = new int[n];
        System.out.print("Массив до сортировки = ");
        for (int i = 0; i < massifs.length ; i++) {
            massifs[i] = (int)(Math.random()*10);
            System.out.print(massifs[i] + " ");
        }
                   Arrays.sort(massifs);
                   System.out.println("\nМассив после сортировки = " + Arrays.toString(massifs));

        if (n % 2 != 0){
            int med = (massifs.length - 1) / 2;
            System.out.println("Значение индекса медианы массива = " + med);
            System.out.println("Значение медианы массива = " + massifs[med]);
        }else{
            int med1 = (massifs.length - 2) / 2;
            int med2 = (massifs.length - 2) / 2 + 1;
            System.out.println("Значение центральных индексов медианы массива = " + med1 + " " + med2);
            double med = (double) (massifs[med1] + massifs[med2]) / 2;
            System.out.println("Значение медианы массива = " + med);
        }

    }

    @Override
    void resultMedianArray(double[] doubles) {

       System.out.println("\n Введите размерность массива с дробными значениями");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        doubles = new double[n];
        System.out.print("Массив до сортировки = ");
        for (int i = 0; i < doubles.length ; i++) {
            doubles[i] = Math.random()*10;
            System.out.printf("%.1f ", doubles[i]);
        }


        Arrays.sort(doubles);
      //  System.out.println("\nМассив после сортировки = " + Arrays.toString(massdouble));
        System.out.print("\nМассив после сортировки = ");
        for (double aMassdouble : doubles) {
            System.out.printf(" %.1f", +aMassdouble);
        }


        if (n % 2 != 0){
            int med = (doubles.length - 1) / 2;
            System.out.println("\nЗначение индекса медианы массива = " + med);
            System.out.println("Значение медианы массива = " + doubles[med]);
        }else{
            int med1 = (doubles.length - 2) / 2;
            int med2 = (doubles.length - 2) / 2 + 1;
            System.out.println("\nЗначение центральных индексов медианы массива = " + med1 + " " + med2);
            double med = (doubles[med1] + doubles[med2]) / 2;
            System.out.println("Значение медианы массива = " + med);    // сократить цифры после запятой??
        }

    }
}
