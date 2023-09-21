package ua.com.euniversity.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.euniversity.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
