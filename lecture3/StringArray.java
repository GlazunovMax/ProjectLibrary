package by.homework.lecture3;

/**
 1.	создать строковый массив из 7 англ. слов обозначающих дни недели  "Sun", "Mon", "Tue","Wen", "Thr", "Fri", "Sat".
 Написать код с использованием switch, который проходит по массиву и выводит на экран день недели по-русски.
 */
public class StringArray {
    public static void main(String[] args) {
        String[] DaysOfWeek = {"Sun", "Mon", "Tue","Wen", "Thr", "Fri", "Sat"};
        for (String dayOnRussian : DaysOfWeek) {

            switch (dayOnRussian){
                case "Sun":
                    System.out.println("Воскресенье");
                    break;
                case "Mon":
                    System.out.println("Понедельник");
                    break;
                case "Tue":
                    System.out.println("Вторник");
                    break;
                case "Wen":
                    System.out.println("Среда");
                    break;
                case "Thr":
                    System.out.println("Четверг");
                    break;
                case "Fri":
                    System.out.println("Пятница");
                    break;
                case "Sat":
                    System.out.println("Суббота");
                    break;
                default:
                    System.out.println("Нет такого дня недели");
            }
        }
    }
}


