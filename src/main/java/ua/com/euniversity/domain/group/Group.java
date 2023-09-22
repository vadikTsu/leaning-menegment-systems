package ua.com.euniversity.domain.group;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "groupName")
public class Group {

    private int id;

    private String groupName;
}
