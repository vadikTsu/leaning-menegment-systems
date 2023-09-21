package ua.com.euniversity.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.euniversity.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();

        student.setUniqueId(rs.getString("uid"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setGroupId(rs.getInt("group_id"));

        return student;
    }
}
