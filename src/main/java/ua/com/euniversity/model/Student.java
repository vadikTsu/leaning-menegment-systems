package ua.com.euniversity.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Student {
    private String uniqueId;
    private String firstName;
    private String lastName;
    private int groupId;
}