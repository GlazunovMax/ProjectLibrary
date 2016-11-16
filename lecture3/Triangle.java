package by.homework.lecture3;
/*
2.	Написать класс Triangle, содержащий перегруженные конструторы для равносторонних,
равнобедренных и обычных треугольников, а также метод, вычисляющий площадь треугольника.
В тестере получить площади треугольников каждого типа.

*/


public class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle(double a) {
        this.a = a;
    }

    public Triangle(double a, double b) {
        this.a = a;
       this.b = b;
    }

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    void squareTriangle(){

           if(a > 0 && b > 0 && c > 0) {
               double p = (a + b + c) / 2;
               double s = p * (p - a) * (p - b) * (p - c);
               double square = Math.sqrt(s);
               System.out.printf("Площадь обычного треугольника = %.2f \n", square);

           }else if (a > 0 && b > 0 && c == 0){
                double square = (b/4) * Math.sqrt((4*Math.pow(a,2) - Math.pow(b,2)));
                System.out.printf("Площадь равнобедренного треугольника = %.2f \n" ,square);

           }else if(a > 0 && b == 0 && c == 0){
                double square = (Math.sqrt(3)*Math.pow(a,2))/4;
                System.out.printf("Площадь равносторонего треугольника = %.2f \n" ,square);

           }else{
               System.out.println("Что-то здесь не так!");
           }
    }

}
