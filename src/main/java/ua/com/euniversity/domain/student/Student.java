package ua.com.euniversity.domain.student;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Student {

    private int id;

    private String firstName;

    private String lastName;

    private int groupId;
}