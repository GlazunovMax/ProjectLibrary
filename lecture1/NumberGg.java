package by.homework;

/**
 *Написать программу, которая принимает на вход четыре числа:
 1.	int a;
 2.	int p;
 3.	double m1;
 4.	double m2.

 Вывести на экран значение числа G, вычисляемого по формуле


 Для получения значения «пи» используйте константу: java.lang.Math.PI;

 Java Test 3 5 6 6
 ….main (String[] a)

 int a = Integer.valueOf(a[0]);

 System.out.print(val);

 */
public class NumberGg {

        public int getA() {
            return a;
        }

        public int getP() {
            return p;
        }

        public double getM() {
            return m;
        }

        public double getM1() {
            return m1;
        }



        private int a;
        private int p;
        private double m;
        private double m1;

        public NumberGg(int a, int p, double m, double m1) {
            this.a = a;
            this.p = p;
            this.m = m;
            this.m1 = m1;
        }

        void resultG(){
            double PI = Math.PI;
            double sum = getM() + getM1();
            double numerator = 4*Math.pow(PI,2)*Math.pow(getA(),3);
            double denominator = Math.pow(getP(),2)* sum;
            double G = numerator/denominator;

            System.out.println("Результат значения числа G = " + G);
        }



}
