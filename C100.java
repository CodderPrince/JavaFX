public class C100 {

  static class Teacher {
    // all properties
    String name;
    int age;
  }

  public static void main(String[] args) {

    // declare object
    Teacher teacher1;

    // create object
    teacher1 = new Teacher();

    // use properties by using dot operators
    teacher1.name = "Prince";
    teacher1.age = 24;

    System.out.println("Teacher 1 details:");
    System.out.println("Name : " + teacher1.name);
    System.out.println("Age : " + teacher1.age);

    // declare object
    Teacher teacher2;

    // create object
    teacher2 = new Teacher();

    // use properties by using dot operators
    teacher2.name = "Princess";
    teacher2.age = 20;

    System.out.println("\nTeacher 2 details:");
    System.out.println("Name : " + teacher2.name);
    System.out.println("Age : " + teacher2.age);
  }
}
