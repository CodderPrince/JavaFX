// method with return and parameters

public class C102 {

  static class Teacher {
    // properties
    String name;
    int age;
    double cgpa;

    // create another method to set info
    void setInfo(String name, int age, double cgpa) {

      // without this keyword
      // name = name;
      // age = age;
      /*
      this is not practical method
      NetBeans maybe accept without this keyword
      but it is good practice to use this keyword

      this is standard way to set value
       */

      // with this keyword
      this.name = name;
      this.age = age;
      this.cgpa = cgpa;
    }

    // create a method
    void displayInfo() {
      System.out.println("Name : " + name);
      System.out.println("Age : " + age);
      System.out.println("CGPA : " + cgpa);
      System.out.println();
    }
  }

  public static void main(String[] args) {

    // create object
    Teacher teacher1 = new Teacher();

    // send data by arguments
    teacher1.setInfo("Prince", 23, 3.18);

    System.out.println("\nTeacher 1 details:");
    teacher1.displayInfo();

    // create object
    Teacher teacher2 = new Teacher();

    // send data by arguments
    teacher2.setInfo("Princess", 21, 3.85);

    System.out.println("Teacher 2 details:");
    teacher2.displayInfo();
  }
}
