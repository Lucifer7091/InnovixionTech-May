import java.util.*;
class Student {
    private String name;
    private int id;
    private Map<String, List<Double>> grades;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.grades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addGrade(String course, double grade) {
        grades.putIfAbsent(course, new ArrayList<>());
        grades.get(course).add(grade);
    }

    public void removeGrade(String course, double grade) {
        if (grades.containsKey(course)) {
            grades.get(course).remove(grade);
            if (grades.get(course).isEmpty()) {
                grades.remove(course);
            }
        }
    }

    public double calculateGPA() {
        double totalGrades = 0;
        int totalCourses = 0;
        for (List<Double> courseGrades : grades.values()) {
            for (double grade : courseGrades) {
                totalGrades += grade;
            }
            totalCourses += courseGrades.size();
        }
        return totalCourses == 0 ? 0 : totalGrades / totalCourses;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", GPA: " + String.format("%.2f", calculateGPA());
    }
}

class StudentManager {
    private List<Student> students;
    private int nextId;

    public StudentManager() {
        this.students = new ArrayList<>();
        this.nextId = 1;
    }

    public void addStudent(String name) {
        Student student = new Student(name, nextId++);
        students.add(student);
        System.out.println("Student added: " + student);
    }

    public void removeStudent(int id) {
        Optional<Student> studentOptional = students.stream().filter(s -> s.getId() == id).findFirst();
        if (studentOptional.isPresent()) {
            students.remove(studentOptional.get());
            System.out.println("Student removed: " + studentOptional.get());
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public void recordGrade(int id, String course, double grade) {
        Optional<Student> studentOptional = students.stream().filter(s -> s.getId() == id).findFirst();
        if (studentOptional.isPresent()) {
            studentOptional.get().addGrade(course, grade);
            System.out.println("Grade recorded for student ID " + id);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public void generateReport() {
        System.out.println("Student Report:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

public class StudentRecordApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Record Grade");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();
                    manager.addStudent(name);
                    break;
                case 2:
                    System.out.print("Enter student ID to remove: ");
                    int removeId = sc.nextInt();
                    manager.removeStudent(removeId);
                    break;
                case 3:
                    System.out.print("Enter student ID to record grade: ");
                    int gradeId = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter course name: ");
                    String course = sc.nextLine();
                    System.out.print("Enter grade: ");
                    double grade = sc.nextDouble();
                    manager.recordGrade(gradeId, course, grade);
                    break;
                case 4:
                    manager.generateReport();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
