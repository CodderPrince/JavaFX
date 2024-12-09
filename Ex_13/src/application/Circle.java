package application;

public class Circle extends GeometricObject {
    private double radius;

    // Default constructor
    public Circle() {
        this(1.0, "white", false);
    }

    // Parameterized constructor
    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    // Getter and Setter
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    // Calculate the area
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    // Calculate the perimeter (circumference)
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle: radius = " + radius + ", color = " + getColor() + ", filled = " + isFilled();
    }
}
