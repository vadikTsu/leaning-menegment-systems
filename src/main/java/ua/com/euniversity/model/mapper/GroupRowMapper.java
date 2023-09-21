package ua.com.euniversity.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.euniversity.model.Group;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupRowMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group();

        group.setGroupId(rs.getInt("uid"));
        group.setGroupName(rs.getString("group_name"));

        return group;
    }
}
