
public class Student {
	int studentId;
	String name;
	String course;
	double marks;
	
	public Student(int studentId, String
name, String course, double marks) 	{
		this.studentId = studentId;
		this.name = name;
		this.course = course;
		this.marks = marks;
	}

	public void displayDetails() {
		System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
		System.out.println("Course: " + course);
		System.out.println("Marks: " + marks);
	}
}
