package application;

public class Triangle extends GeometricObject {
	private double side1;
	private double side2;
	private double side3;

	// Default constructor
	public Triangle() {
		this(1.0, 1.0, 1.0, "white", false);
	}

	// Parameterized constructor
	public Triangle(double side1, double side2, double side3, String color, boolean filled) {
		super(color, filled);
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}

	// Calculate the perimeter
	@Override
	public double getPerimeter() {
		return side1 + side2 + side3;
	}

	// Calculate the area using Heron's formula
	@Override
	public double getArea() {
		double s = getPerimeter() / 2;
		return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
	}

	@Override
	public String toString() {
		return "Triangle: side1 = " + side1 + ", side2 = " + side2 + ", side3 = " + side3 + ", color = " + getColor()
				+ ", filled = " + isFilled();
	}
}
