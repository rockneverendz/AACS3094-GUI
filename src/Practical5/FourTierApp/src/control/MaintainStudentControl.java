package control;

import da.StudentDA;
import domain.Student;

public class MaintainStudentControl {

    private StudentDA studentDA;

    public MaintainStudentControl() {
        studentDA = new StudentDA();
    }

    public Student selectRecord(String code) {
        return studentDA.getRecord(code);
    }

    public void addRecord(Student student) {
        studentDA.addRecord(student);
    }

    public void updateRecord(Student student) {
        studentDA.updateRecord(student);
    }

    public void deleteRecord(Student student) {
        studentDA.deleteRecord(student);
    }
}
