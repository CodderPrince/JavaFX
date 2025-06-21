public class C114 {
  static class Box {
    double hei, wid, dep;//instance variables

    // constructor
    Box(double x, double y, double z)//local variables
    {
      this.hei = x;
      this.wid = y;
      this.dep = z;
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
