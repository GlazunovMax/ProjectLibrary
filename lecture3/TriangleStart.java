package by.homework.lecture3;

/**
 2.	Написать класс Triangle, содержащий перегруженные конструторы для равносторонних,
 равнобедренных и обычных треугольников, а также метод, вычисляющий площадь треугольника.
 В тестере получить площади треугольников каждого типа.

 */
public class TriangleStart {
    public static void main(String[] args) {
       Triangle triangle = new Triangle(4);
       Triangle triangle1 = new Triangle(-4, 1);
       Triangle triangle2 = new Triangle(2.7, 1, 2);

      triangle.squareTriangle();
        triangle1.squareTriangle();
       triangle2.squareTriangle();

    }
}
