import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args){
        StudentService service = new StudentService();
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while (choice!=0){
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Marks");
            System.out.println("4. Display All Students");
            System.out.println("5. Find Top Scorer");
            System.out.println("6. Average Marks by Course");
            System.out.println("7. Display Grouped by Course");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice){
                case  1:
                    try {
                        System.out.print("Enter student ID: ");
                        int id = sc.nextInt();
                        System.out.print("Enter name: ");
                        String name = sc.next();
                        System.out.print("Enter course: ");
                        String course = sc.next();
                        System.out.print("Enter marks: ");
                        double marks = sc.nextDouble();
                        service.addStudent(new Student(id, name, course, marks));
                        System.out.println("student added successfully");
                    } catch (DuplicateStudentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case  2:
                    try {
                        System.out.print("Enter student ID to remove: ");
                        int id = sc.nextInt();
                        service.removeStudent(id);
                    } catch (StudentNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Enter student ID: ");
                        int id = sc.nextInt();
                        System.out.print("Enter new marks: ");
                        double marks = sc.nextDouble();
                        service.updateMarks(id, marks);
                    } catch (StudentNotFoundException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case  4:
                    service.displayAllStudents();
                    break;
                case 5:
                    Student top = service.findTopScorer();
                    if(top == null) {
                        System.out.println("No students found!");
                    } else {
                        System.out.println("Top Scorer: " + top.getName() + " - " + top.getMarks());
                    }
                    break;
                case 6:
                    System.out.print("Enter course name: ");
                    String course = sc.next();
                    System.out.println("Average Marks: " + service.averageMarks(course));
                    break;
                case 7:
                    service.displayGroupedByCourse();
                    break;
                case 0:
                    System.out.println("Good by!");
                    break;
                default:
                    System.out.println("please enter a valid option");
                    break;
            }
        }
    }
}
