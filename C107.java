public class C107 {

  // create class
  static class Student {
    String name;
    int id;
    static String Uni = "BRUR";

    // constructor
    Student(String name, int id) {
      this.name = name;
      this.id = id;
    }

    // method
    void dis() {
      System.out.println("Name : " + name);
      System.out.println("ID : " + id);
      System.out.println("University : " + Uni);
      System.out.println();
    }
  }

  public static void main(String[] args) {
    // create object
    Student student1 = new Student("Prince", 667);
    student1.dis();

    Student student2 = new Student("Princess", 666);
    student2.dis();
  }
}

/*
static variable
static method
static block

static variable: when common variables are used multiple time
then convert this variable as static variable

without static variables that variable is called instance variables
 */
