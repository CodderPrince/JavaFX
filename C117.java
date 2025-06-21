public class C117 {
  static class Overload {
    // no arguments
    static void add() {
      System.out.println("No arguments!\n");
    }

    // single arguments
    static void add(int a) {
      System.out.println("Single integer : " + a);
    }

    // singe arguments with different data types
    static void add(double a) {
      System.out.println("Single double : " + a);
    }

    // triple variables
    static void add(int a, double b, int c) {
      System.out.println("Add : " + (a + b + c));
    }
  }

  public static void main(String[] args) {
      Overload.add();//no arguments
      Overload.add(5);//int
      Overload.add(3.18);//double
      Overload.add(5,6.36652,7);//triple
  }
}
