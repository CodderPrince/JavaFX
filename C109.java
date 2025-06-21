public class C109 {
  static class Student {
    int cnt = 0;
    static int count = 0;

    // constructor
    Student() {
      cnt++;
      count++;
    }

    // method
    void dis() {
      System.out.println("Total Student(Non-Static): " + cnt);
      System.out.println("Total Student(Static): " + count);
    }
  }

  public static void main(String[] args) {
    Student s1 = new Student();
    Student s2 = new Student();
    Student s3 = new Student();
    s3.dis();
  }
}
