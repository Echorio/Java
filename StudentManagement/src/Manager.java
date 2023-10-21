import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Manager {
    private List<Student> studentList = new ArrayList<>();
    private Validation validator = new Validation();

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int option = validator.getIntInput(scanner, "Please choose an option: ");

            switch (option) {
                case 1:
                    createStudent(scanner);
                    break;
                case 2:
                    findAndSortStudents(scanner);
                    break;
                case 3:
                    updateOrDeleteStudent(scanner);
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid option! Please choose a valid option.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("WELCOME TO STUDENT MANAGEMENT");
        System.out.println("1. Create");
        System.out.println("2. Find and Sort");
        System.out.println("3. Update/Delete");
        System.out.println("4. Report");
        System.out.println("5. Exit");
    }

    private void createStudent(Scanner scanner) {
        do {
            int id = validator.getIntInput(scanner, "Enter student ID: ");
            String studentName = validator.getStringInput(scanner, "Enter student name: ");
            int semester = validator.getIntInput(scanner, "Enter semester: ");
            String courseName = validator.getStringInput(scanner, "Enter course name (Java, .Net, C/C++): ");

            Student student = new Student(id, studentName, semester, courseName);
            studentList.add(student);

            System.out.print("Do you want to continue (Y/N)? ");
        } while (scanner.next().equalsIgnoreCase("Y"));

        System.out.println("Returning to the main screen...");
    }

    private void findAndSortStudents(Scanner scanner) {
        String searchName = validator.getStringInput(scanner, "Enter student name or part of student name: ");

        List<Student> foundStudents = new ArrayList<>();

        for (Student student : studentList) {
            if (student.getStudentName().toLowerCase().contains(searchName.toLowerCase())) {
                foundStudents.add(student);
            }
        }

        if (foundStudents.isEmpty()) {
            System.out.println("No students found with the given name.");
        } else {
            Collections.sort(foundStudents, Comparator.comparing(Student::getStudentName));

            System.out.println("Sorted Students:");
            for (Student student : foundStudents) {
                System.out.println("Student Name: " + student.getStudentName());
                System.out.println("Semester: " + student.getSemester());
                System.out.println("Course Name: " + student.getCourseName());
                System.out.println("-----------------------");
            }
        }
    }

    private void updateOrDeleteStudent(Scanner scanner) {
        int searchId = validator.getIntInput(scanner, "Enter student ID: ");

        Student foundStudent = null;

        for (Student student : studentList) {
            if (student.getId() == searchId) {
                foundStudent = student;
                break;
            }
        }

        if (foundStudent == null) {
            System.out.println("No student found with the given ID.");
            return;
        }

        System.out.println("Student found:");
        System.out.println("Student ID: " + foundStudent.getId());
        System.out.println("Student Name: " + foundStudent.getStudentName());
        System.out.println("Semester: " + foundStudent.getSemester());
        System.out.println("Course Name: " + foundStudent.getCourseName());

        String option = validator.getStringInput(scanner, "Do you want to update (U) or delete (D) the student? ");

        if (option.equalsIgnoreCase("U")) {
            String newName = validator.getStringInput(scanner, "Enter new student name: ");
            int newSemester = validator.getIntInput(scanner, "Enter new semester: ");
            String newCourse = validator.getStringInput(scanner, "Enter new course name: ");

            foundStudent.setStudentName(newName);
            foundStudent.setSemester(newSemester);
            foundStudent.setCourseName(newCourse);

            System.out.println("Student updated successfully.");
        } else if (option.equalsIgnoreCase("D")) {
            studentList.remove(foundStudent);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Invalid option! Returning to the main screen.");
        }
    }

    private void generateReport() {
        List<String> reportList = new ArrayList<>();

        for (Student student : studentList) {
            String reportEntry = student.getStudentName() + " | " + student.getCourseName();
            reportList.add(reportEntry);
        }

        System.out.println("Report:");
        for (String entry : reportList) {
            int totalCourses = Collections.frequency(reportList, entry);

            System.out.println(entry + " | " + totalCourses);
        }
    }

    private static class Student {
        private int id;
        private String studentName;
        private int semester;
        private String courseName;

        public Student(int id, String studentName, int semester, String courseName) {
            this.id = id;
            this.studentName = studentName;
            this.semester = semester;
            this.courseName = courseName;
        }

        public int getId() {
            return id;
        }

        public String getStudentName() {
            return studentName;
        }

        public int getSemester() {
            return semester;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setStudentName(String newName) {
            this.studentName = newName;
        }

        public void setSemester(int newSemester) {
            this.semester = newSemester;
        }

        public void setCourseName(String newCourse) {
            this.courseName = newCourse;
        }
    }
}
