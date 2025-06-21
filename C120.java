public class C120 {

  static class CallBV {
    int y; // Instance variable

    // Method to modify primitive value (this won't affect the original variable)
    void change(int i) {
      i = 20;
    }

    // Method to modify object reference
    void change(CallBV obj) {
      obj.y = 20;
    }
  }

  public static void main(String[] args) {
    CallBV ob1 = new CallBV();
    int x = 10; // Primitive variable
    System.out.println("Before call x: " + x);

    // Pass primitive type (value won't change)
    ob1.change(x);
    System.out.println("After change x: " + x);

    // Object reference test
    CallBV ob2 = new CallBV();
    ob2.y = 100; // Initialize instance variable
    System.out.println("Before call y: " + ob2.y);

    // Pass object reference (value will change)
    ob1.change(ob2);
    System.out.println("After change y: " + ob2.y);
  }
}
