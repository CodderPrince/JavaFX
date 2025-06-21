public class C106 {
  static class ReturningValue {
    int val;

    // create constructor
    ReturningValue(int val) {
      this.val = val;
    }

    // create a return type method
    int square() {
      return val * val;
    }

    // display method
    void dis() {
      System.out.println("Area: " + square());
    }
  }

  public static void main(String[] args) {

    // create object with constructor
    ReturningValue ob1 = new ReturningValue(5);
    ob1.dis();
  }
}
