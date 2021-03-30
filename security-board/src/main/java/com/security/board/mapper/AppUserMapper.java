package com.security.board.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.security.board.model.HrUser;
 
public class AppUserMapper implements RowMapper<HrUser> {
 
    public static final String BASE_SQL //
            = "Select u.User_Id, u.User_Name, u.Encryted_Password From Hr_User u ";
 
    @Override
    public HrUser mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        Long userId = rs.getLong("User_Id");
        String userName = rs.getString("User_Name");
        String encrytedPassword = rs.getString("Encryted_Password");
        return new HrUser(userId, userName, encrytedPassword);
    }
}