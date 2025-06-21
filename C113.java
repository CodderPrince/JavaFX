public class C113 {

  static class variableTypes {
    String name;
    int id;
    static String uni = "BRUR";

    // constructor: a special type method
    variableTypes(String n, int i) {
      //static int x; this is error
      name = n;
      id = i;
    }

    // method
    static void dis(String name, int id) {
      System.out.println("Name : " + name);
      System.out.println("Id : " + id);
      System.out.println("University : " + uni);
    }
  }

  public static void main(String[] args) {

    variableTypes.dis("Prince", 12105007);
  }
}
