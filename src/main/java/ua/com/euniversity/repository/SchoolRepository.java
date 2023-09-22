package ua.com.euniversity.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.euniversity.domain.course.Course;
import ua.com.euniversity.domain.group.Group;
import ua.com.euniversity.domain.student.Student;
import ua.com.euniversity.domain.course.CourseRowMapper;
import ua.com.euniversity.domain.group.GroupRowMapper;
import ua.com.euniversity.domain.student.StudentRowMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SchoolRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public SchoolRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Student> getAllStudents() {
        String query = "SELECT * FROM students";
        List<Student> students = jdbc.query(query, new StudentRowMapper());
        return students;
    }

    public List<Student> getAllStudentsAssignedToCourse(String CourseName) {
        String query = "SELECT * FROM get_students_related_to_course(?)";
        List<Student> students = jdbc.query(query, new StudentRowMapper(), CourseName);
        return students;
    }

    public int[] addStudent(List<Student> students) {
        return jdbc.batchUpdate(
                "INSERT INTO students(student_uid, group_uid, first_name, last_name) VALUES (?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, students.get(i).getId());
                        ps.setInt(2, students.get(i).getGroupId());
                        ps.setString(3, students.get(i).getFirstName());
                        ps.setString(4, students.get(i).getLastName());
                    }

                    @Override
                    public int getBatchSize() {
                        return 50;
                    }
                });
    }

    public int assignStudentToCourse(String studentId, String courseName) {
        String query = "INSERT INTO students_courses(student_id, course_id) " +
                "VALUES(?, (SELECT FROM courses.course_id WHERE courses.course_name = ?))";
        return jdbc.update(query, studentId, courseName);
    }

    public int removeStudentFromCourse(String studentId, String courseName) {
        String query = "DELETE FROM students_courses WHERE student_uid = ? " +
                "AND course_uid = (SELECT course_uid FROM courses WHERE course_name = ?)";
        return jdbc.update(query, studentId, courseName);
    }

    public int deleteStudent(String studentUniqueId) {
        String query = "DELETE FROM students WHERE student_uid = ?;" +
                "DELETE FROM students_courses WHERE student_uid = ?;";
        return jdbc.update(query, studentUniqueId, studentUniqueId);
    }

    public List<Course> getAllCourses() {
        String query = "SELECT * FROM courses";
        List<Course> courses = jdbc.query(query, new CourseRowMapper());
        return courses;
    }

    public List<Group> getAllGroups() {
        String query = "SELECT * FROM groups";
        List<Group> groups = jdbc.query(query, new GroupRowMapper());
        return groups;
    }
}
