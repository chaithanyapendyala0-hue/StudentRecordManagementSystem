import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class StudentManagement {

    static final String FILE_NAME = "students.txt";

    public static void saveStudents(ArrayList<Student> students) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(FILE_NAME))) {

            for (Student s : students) {
                writer.println(s.studentId + "|" + s.name
                        + "|" + s.course + "|" + s.marks);
            }

            System.out.println("Records saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving records!");
        }
    }

    public static void loadStudents(ArrayList<Student> students) {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|", -1);

                if (data.length == 4) {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String course = data[2];
                    double marks = Double.parseDouble(data[3]);

                    students.add(new Student(id, name, course, marks));
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading records!");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        loadStudents(students);
        System.out.println("Student records loaded: " + students.size());

        int choice;

        do {
            System.out.println("\n===== STUDENT RECORD MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {

                System.out.print("Enter Student ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                boolean exists = false;

                for (Student s : students) {
                    if (s.studentId == id) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("Student ID already exists!");
                } else {
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();

                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    sc.nextLine();

                    students.add(new Student(id, name, course, marks));
                    System.out.println("Student added successfully!");

                    saveStudents(students);
                }

            } else if (choice == 2) {

                if (students.isEmpty()) {
                    System.out.println("No student records found.");
                } else {
                    for (Student s : students) {
                        System.out.println("\nStudent Details:");
                        s.displayDetails();
                        System.out.println("--------------------");
                    }
                }

            } else if (choice == 3) {

                System.out.print("Enter Student ID to search: ");
                int searchId = sc.nextInt();
                sc.nextLine();

                boolean found = false;

                for (Student s : students) {
                    if (s.studentId == searchId) {
                        System.out.println("Student Found!");
                        s.displayDetails();
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Student not found!");
                }

            } else if (choice == 4) {

                System.out.print("Enter Student ID to update: ");
                int updateId = sc.nextInt();
                sc.nextLine();

                boolean found = false;

                for (Student s : students) {
                    if (s.studentId == updateId) {
                        System.out.print("Enter new name: ");
                        s.name = sc.nextLine();

                        System.out.print("Enter new course: ");
                        s.course = sc.nextLine();

                        System.out.print("Enter new marks: ");
                        s.marks = sc.nextDouble();
                        sc.nextLine();

                        System.out.println("Student updated successfully!");
                        saveStudents(students);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Student not found!");
                }

            } else if (choice == 5) {
                System.out.println("Thank you!");
            } else {
                System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}