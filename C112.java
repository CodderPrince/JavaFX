public class C112 {

  static class StaticBlock {
    static int id;
    static String name;

    // create static block
    static {
      id = 101;
      name = "Prince";
    }

    // method
    static void dis() {
      System.out.println("ID : " + id);
      System.out.println("Name : " + name);
    }
  }

  public static void main(String[] args) {
    StaticBlock.dis();
  }
}
