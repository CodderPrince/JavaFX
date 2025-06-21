public class C110 {
  // create two method

  static class Student {
    // non-static method
    void dis1() {
      System.out.println("I am a non-static method!\n" + "Directly related to Objects!\n");
    }

    // static method
    static void dis2() {
      System.out.println("I am a static method!\n" + "Directly related to Class!");
    }
  }

  public static void main(String[] args) {
    // create objects for called non static method
    Student ob1 = new Student();
    ob1.dis1();

    // directly called static method by using Class
    Student.dis2();
  }
}
