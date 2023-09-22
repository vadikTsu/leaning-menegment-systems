package ua.com.euniversity.domain.course;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(of = "courseName")
public class Course {

    private int id;

    private String courseName;

    private String courseDescription;
}
