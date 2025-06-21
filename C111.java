public class C111 {

  // create two method

  static class Student {
    int x = 14;

    // non-static method
    void dis1() {
      /*
      if we try to called static method from non static method
      that is work perfectly
      cause there is no restrictions here
       */

      System.out.print("Called from non-static method -> ");
      dis2();
      System.out.println();

      System.out.println("X : " + x + "\n");

      System.out.println("I am a non-static method!\n" + "Directly related to Objects!\n");
    }

    // static method
    static void dis2() {
      /*
      Cannot called non static method from here
      this is the restriction

      cannot called non static method inside the static method
      if wer try to called it then show errors

       */
      // dis1();//this is errors

      // System.out.println("X : " + x + "\n");//this is also errors

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
