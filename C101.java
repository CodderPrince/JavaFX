// method creation
public class C101 {

  static class Teacher {
    // properties
    String name;
    int age;

    // create a method
    void displayInfo() {
      System.out.println("Name : " + name);
      System.out.println("Age : " + age);
      System.out.println();
    }
  }

  public static void main(String[] args) {

    // create object
    Teacher teacher1 = new Teacher();

    // use properties by using dot operators
    teacher1.name = "Prince";
    teacher1.age = 24;
    System.out.println("Teacher 1 details:");
    teacher1.displayInfo();
    // without method

    // System.out.println("Name : " + teacher1.name);
    // System.out.println("Age : " + teacher1.age);

    // create object
    Teacher teacher2 = new Teacher();

    // use properties by using dot operators
    teacher2.name = "Princess";
    teacher2.age = 20;

    System.out.println("Teacher 2 details:");
    teacher2.displayInfo();

    // without method
    // System.out.println("\nTeacher 2 details:");
    // System.out.println("Name : " + teacher2.name);
    // System.out.println("Age : " + teacher2.age);
  }
}
