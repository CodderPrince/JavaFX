public class C108 {
  // create class
  static class StaticVariable {
    String uni = "BRUR";

    static String Uni = "BRUR";
  }

  public static void main(String[] args) {

    StaticVariable ob = new StaticVariable();
    System.out.println("Non-Static variable : " + ob.uni);

    // static variables are related to class
    // so directly access this
    System.out.println("Static variable : " + StaticVariable.Uni);
  }
}
