package ra.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentInfo
{
    private String name;
    private  int age;

    public String getName() {
        return name;
    }

    public StudentInfo() {
    }

    public StudentInfo(Student student) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        this.name = student.getName();
        int now = Integer.parseInt(simpleDateFormat.format(new Date()));
        int born = Integer.parseInt(simpleDateFormat.format(student.getBirthday()));
        this.age = now-born+1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
