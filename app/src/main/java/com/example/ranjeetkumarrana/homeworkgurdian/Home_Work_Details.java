package com.example.ranjeetkumarrana.homeworkgurdian;

public class Home_Work_Details {

    String studentId;
    String student_Class;
    String subject;
    String date ;
    String roll_no ;
    String description ;

    public Home_Work_Details(){}

    public Home_Work_Details(String studentId, String student_Class, String subject, String date, String roll_no, String description) {
        this.studentId = studentId;
        this.student_Class = student_Class;
        this.subject = subject;
        this.date = date;
        this.roll_no = roll_no;
        this.description = description;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudent_Class() {
        return student_Class;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public String getDescription() {
        return description;
    }


}
