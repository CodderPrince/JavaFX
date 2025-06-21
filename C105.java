public class C105 {

  // create a class
  static class Teacher {
    String name, gender;
    int phone;
    double cgpa;

    // default constructor
    Teacher() {
      System.out.println("Default Constructor -- no parameters!\n");
    }

    // single parameter string
    Teacher(String name) {
      this.name = name;
    }

    // single parameter int
    Teacher(int phone) {
      this.phone = phone;
    }

    // 2 parameterized constructor
    Teacher(String name, String gender) {
      this.name = name;
      this.gender = gender;
    }

    // another 3  par. constructor
    Teacher(String name, String gender, int phone, double cgpa) {
      this.name = name;
      this.gender = gender;
      this.phone = phone;
      this.cgpa = cgpa;
    }

    // create method for print
    void displayInfo() {
      System.out.println("Name : " + name);
      System.out.println("Gender : " + gender);
      System.out.println("Phone : " + phone);
      System.out.println("CGPA : " + cgpa);
      System.out.println();
    }
  }

  public static void main(String[] args) {

    // create objects now with constructor

    // default constructor
    Teacher teacher1 = new Teacher();

    // 2 parameter const
    Teacher teacher2 = new Teacher("Mr. Nazmul", "Male");
    teacher2.displayInfo();

    // 3 parameter const
    Teacher teacher3 = new Teacher("Miss. Princess", "Female", 1234, 3.95);
    teacher3.displayInfo();

    Teacher teacher4 = new Teacher("Prince");
    teacher4.displayInfo();

    Teacher teacher5 = new Teacher(17);
    teacher5.displayInfo();
  }
}
