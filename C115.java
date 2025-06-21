public class C115 {

  static class Box {
    double hei, wid, dep; // instance variables

    // constructor
    Box(double hei, double wid, double dep) // local variables
        {
      /// this is called instance variable hiding
      // now instance and local variables are same
      this.hei = hei;
      this.wid = wid;
      this.dep = dep;
    }

    // method
    static void dis(double x, double y, double z) {
      System.out.println("Height : " + x);
      System.out.println("Weight : " + y);
      System.out.println("Depth : " + z);
      double volume = x * y * z;
      System.out.println("Volume : " + volume);
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Box.dis(10, 10, 10);
    Box.dis(20, 30, 10);
  }
}
