package ua.com.euniversity.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.euniversity.model.Course;
import ua.com.euniversity.model.Group;
import ua.com.euniversity.model.Student;
import ua.com.euniversity.model.mapper.CourseRowMapper;
import ua.com.euniversity.model.mapper.GroupRowMapper;
import ua.com.euniversity.model.mapper.StudentRowMapper;

import java.util.List;

@Repository
public class SchoolRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public SchoolRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public List<Student> getAllStudents() {
        String query = "SELECT * FROM students";
        List<Student> students = jdbc.query(query, new StudentRowMapper());
        return students;
    }

    public List<Course> getAllCourses() {
        String query = "SELECT * FROM courses";
        List<Course> students = jdbc.query(query, new CourseRowMapper());
        return students;
    }

    public List<Group> getAllGroups() {
        String query = "SELECT * FROM groups";
        List<Group> groups = jdbc.query(query, new GroupRowMapper());
        return groups;
    }

    
}
