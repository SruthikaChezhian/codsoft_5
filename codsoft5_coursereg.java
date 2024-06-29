import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int availableSlots;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.availableSlots = capacity;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void registerStudent() {
        if (availableSlots > 0) {
            availableSlots--;
        }
    }

    public void dropStudent() {
        if (availableSlots < capacity) {
            availableSlots++;
        }
    }
}

class Student {
    private String id;
    private String name;
    private List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.getAvailableSlots() > 0) {
            course.registerStudent();
            registeredCourses.add(course);
        }
    }

    public void dropCourse(Course course) {
        course.dropStudent();
        registeredCourses.remove(course);
    }
}

public class codsoft5_coursereg {
    private Map<String, Course> courses;
    private Map<String, Student> students;

    public codsoft5_coursereg() {
        this.courses = new HashMap<>();
        this.students = new HashMap<>();
    }

    public void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public void displayCourseListing() {
        for (Course course : courses.values()) {
            System.out.println("Course Code: " + course.getCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Available Slots: " + course.getAvailableSlots());
            System.out.println();
        }
    }

    public void displayStudentInfo(String studentId) {
        Student student = students.get(studentId);
        if (student != null) {
            System.out.println("Student Name: " + student.getName());
            System.out.println("Registered Courses:");
            for (Course course : student.getRegisteredCourses()) {
                System.out.println("  " + course.getCode() + " - " + course.getTitle());
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public void registerStudentForCourse(String studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);
        if (student != null && course != null) {
            student.registerCourse(course);
        }
    }

    public void dropStudentFromCourse(String studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);
        if (student != null && course != null) {
            student.dropCourse(course);
        }
    }

    public static void main(String[] args) {
        codsoft5_coursereg system = new codsoft5_coursereg();

        Course course1 = new Course("CSE101", "Introduction to Computer Science", "Introduction to computer science concepts", 30);
        Course course2 = new Course("ICT101", "Data Structures", "Data structures and algorithms", 25);
        Course course3 = new Course("MAT101", "Engineering Mathematics I", "Basic Engineerng Mathematics concepts", 20);
        Course course4 = new Course("CSE404", "Computer Networks", "Computer networks and protocols", 35);
        Course course5 = new Course("ECE102", "Signals and Systems", "Basic signal and circuits concepts", 40);

        system.addCourse(course1);
        system.addCourse(course2);
        system.addCourse(course3);
        system.addCourse(course4);
        system.addCourse(course5);

        Student student1 = new Student("S1", "Brad Pitt");
        Student student2 = new Student("S2", "Kate Sharma");
        Student student3 = new Student("S3", "Will Smith");

        system.addStudent(student1);
        system.addStudent(student2);
        system.addStudent(student3);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Display course listing");
            System.out.println("2. Display student information");
            System.out.println("3. Register student for a course");
            System.out.println("4. Drop student from a course");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                system.displayCourseListing();
            } else if (choice == 2) {
                System.out.print("Enter student ID: ");
                String studentId = scanner.next();
                system.displayStudentInfo(studentId);
            } else if (choice == 3) {
                System.out.print("Enter student ID: ");
                String studentId = scanner.next();
                System.out.print("Enter course code: ");
                String courseCode = scanner.next();
                system.registerStudentForCourse(studentId, courseCode);
            } else if (choice == 4) {
                System.out.print("Enter student ID: ");
                String studentId = scanner.next();
                System.out.print("Enter course code: ");
                String courseCode = scanner.next();
                system.dropStudentFromCourse(studentId, courseCode);
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}