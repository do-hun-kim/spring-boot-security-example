package com.security.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ApprovalBoardDAO {
	@Autowired
	JdbcTemplate jt;

	public List<Map<String, Object>> getBoardList() {
		String sql = "select BOARD_ID, TITLE, NAME, CONTENT, RGST_DATE,(CASE status WHEN 0  THEN '결재요청' WHEN 3000  THEN '결재완료' WHEN 9000  THEN '반려' END) AS status from APPROVAL_ITEM order by BOARD_ID DESC";
		return jt.query(sql, (rs, rowNum) -> {
			Map<String, Object> anArticle = new HashMap<>();
			anArticle.put("id", rs.getLong(1));
			anArticle.put("title", rs.getString(2));
			anArticle.put("name", rs.getString(3));
			anArticle.put("content", rs.getString(4));
			anArticle.put("rgstdate", rs.getString(5));
			anArticle.put("status", rs.getString(6));
			return anArticle;
		});
	}

	@SuppressWarnings("deprecation")
	public List<Map<String, Object>> getReqBoardList(String name) {
		String sql = "select BOARD_ID, TITLE, NAME, CONTENT, RGST_DATE,(CASE status WHEN 0  THEN '결재요청' WHEN 3000  THEN '결재완료' WHEN 9000  THEN '반려' END) AS status from APPROVAL_ITEM WHERE approval_name = ? order by BOARD_ID DESC";
		return jt.query(sql, new Object[] { name }, (rs, rowNum) -> {
			Map<String, Object> anArticle = new HashMap<>();
			anArticle.put("id", rs.getLong(1));
			anArticle.put("title", rs.getString(2));
			anArticle.put("name", rs.getString(3));
			anArticle.put("content", rs.getString(4));
			anArticle.put("rgstdate", rs.getString(5));
			anArticle.put("status", rs.getString(6));
			return anArticle;
		});
	}

	@SuppressWarnings("deprecation")
	public Map<String, Object> getBoardRead(int id) {
		String sql = "select BOARD_ID, TITLE, NAME, CONTENT, RGST_DATE, APPROVAL_NAME, status from APPROVAL_ITEM where BOARD_ID=?";
		Map<String, Object> anArticle = new HashMap<>();
		try {
			return jt.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> {
				anArticle.put("id", rs.getLong(1));
				anArticle.put("title", rs.getString(2));
				anArticle.put("name", rs.getString(3));
				anArticle.put("content", rs.getString(4));
				anArticle.put("rgstdate", rs.getString(5));
				anArticle.put("approval_name", rs.getString(6));
				anArticle.put("status", rs.getString(7));
				return anArticle;
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
		return anArticle;
	}

	public String getBoardWrite(Map<String, Object> param) {
		String sql = "INSERT INTO APPROVAL_ITEM (TITLE, USERID, NAME, APPROVAL_NAME, CONTENT) VALUES "
				+ "(?, ?, ?, ?, ?)";
		String status = "";
		try {
			jt.update(sql, param.get("title"), param.get("userid"), param.get("name"), param.get("approvalname"),
					param.get("content"));
			status = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public String getBoardEdit(Map<String, Object> param) {
		String sql = "update APPROVAL_ITEM set title = ?, content = ? where BOARD_ID = ? ";
		String status = "";
		try {
			jt.update(sql, param.get("title"), param.get("content"), param.get("id"));
			status = "success";
			// }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;
	}

	public List<Object> getPreWrite() {
		String sql = "select USER_ID, USER_NAME from hr_user where ROLE_NAME='ROLE_ADMIN' order by user_id";
		return jt.query(sql, (rs, rowNum) -> {
			Map<String, Object> anArticle = new HashMap<>();
			anArticle.put("userid", rs.getLong(1));
			anArticle.put("name", rs.getString(2));
			return anArticle;
		});
	}

	public String getBoardComplete(Map<String, Object> param) {
		String sql = "update APPROVAL_ITEM set status = ? where BOARD_ID = ? ";
		String status = "";
		try {
			jt.update(sql, param.get("status"), param.get("id"));
			status = "success";
			// }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;
	}

	public void getBoardDelete(int id) {
		String sql = "delete from APPROVAL_ITEM where BOARD_ID = ? and status = ? and isvisb= ?";
		jt.update(sql, id, 0, 1);
	}
}
