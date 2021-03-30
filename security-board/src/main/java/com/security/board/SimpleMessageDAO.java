package com.security.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleMessageDAO {
	@Autowired
	JdbcTemplate jt;
	public List<Map<String, ?>> selectAll() {
		return jt.query("select user_id, user_name, ENCRYTED_PASSWORD from hr_user", (rs, rowNum) -> {
			Map<String, Object> mss = new HashMap<>();
			mss.put("subject", rs.getInt(1));
			mss.put("user_name", rs.getString(2));
			mss.put("content", rs.getString(3));
			mss.put("regdate", "2021-03-17");
			return mss;
		});
	}

	public Map<String, Object> getBoardWrite(String title, String content) {
		// TODO Auto-generated method stub
		return null;
	}
}