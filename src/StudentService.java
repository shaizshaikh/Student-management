import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentService {
    HashMap<Integer, Student> studentHashMap = new HashMap<>();
    ArrayList<Student> studentArrayList = new ArrayList<>();

    public void displayAllStudents() {
        if (studentArrayList.isEmpty()){
            System.out.println("no student exists!");
        }
        Collections.sort(studentArrayList, (a, b) -> Double.compare(b.getMarks(), a.getMarks()));
        for (Student student : studentArrayList) {
            System.out.println(student.getName());
            System.out.println(student.getMarks());
        }
    }

    public void addStudent(Student student) throws DuplicateStudentException {
        if (studentHashMap.containsKey(student.getStudentId())) {
throw  new DuplicateStudentException("the student with this id "+ student.getStudentId() + " is already exists");
        }
        else
        {
            studentHashMap.put(student.getStudentId(), student);
            studentArrayList.add(student);
            System.out.println("Student addedd successfully!");
        }
    }
    public void updateMarks(int studentId, double newMarks) throws StudentNotFoundException {
        if (studentHashMap.containsKey(studentId)) {
            studentHashMap.get(studentId).setMarks(newMarks);
            System.out.println("the marks updated successfully, " + "updated marks are: " + newMarks);
        } else {
            throw new StudentNotFoundException("fail to update students marks, the student does not exist. please enter a valid studentID.");
        }
    }

    public void removeStudent(int student) throws StudentNotFoundException {
        if (!studentHashMap.containsKey(student)) {
            throw new StudentNotFoundException("the student does not exist!");
        } else {
            studentHashMap.remove(student);
            for (int i = 0; i<studentArrayList.size(); i++){
                if (studentArrayList.get(i).getStudentId() == student) {
                    studentArrayList.remove(i);
                    break;
                }
            }
            System.out.println("the student with id " + student + " is removed successfully!");
        }
    }

    public Student findTopScorer() {
        if(studentArrayList.isEmpty()) {
            return null;
        }
        Collections.sort(studentArrayList, (a, b) -> Double.compare(b.getMarks(), a.getMarks()));
        return studentArrayList.get(0);
    }

    public double averageMarks(String course){
        int count = 0;
        double totalMarks = 0;
        for (Student i : studentArrayList){
            if (i.getCourse().equals(course)){
                count+=1;
                totalMarks+= i.getMarks();
            }
        }

        if(count == 0) {
            return 0;
        }else {
            return totalMarks / count;
        }
    }

    public void displayGroupedByCourse() {
        if(studentArrayList.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        HashMap<String, ArrayList<Student>> groupedStudents = new HashMap<>();
        for (Student student : studentArrayList) {
            if (!groupedStudents.containsKey(student.getCourse())) {
                groupedStudents.put(student.getCourse(), new ArrayList<>());
            }
            groupedStudents.get(student.getCourse()).add(student);
        }

        for (String course : groupedStudents.keySet()) {
            System.out.print(course + " -> ");
            for (Student student : groupedStudents.get(course)) {
                System.out.print(student.getName() + " ");
            }
            System.out.println();
        }
    }
}
