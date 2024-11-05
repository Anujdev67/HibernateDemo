package com.spring.sms.repository;

import org.springframework.stereotype.Repository;

import com.spring.sms.model.Course;
import com.spring.sms.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
@Repository
public class CourseRepository {
	@Autowired
	private JdbcTemplate jdbc;
	public List<Course> fetchAllCourses(){
		//prepare the stmt
		String sql = "select c.id as course_id, c.name as course_name, "
				+ "	c.credits, d.name as d_name "
				+ "	from course c join department d ON c.department_id=d.id";
		
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override 
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
				PreparedStatement pstmt = con.prepareStatement(sql);
				return pstmt;
			}
		};
		RowMapper<Course> rowMapper = new RowMapper<Course>() {
			@Override
			public Course mapRow(ResultSet rs,int rowNum) throws SQLException{
				Course course = new Course();
				int courseId = rs.getInt("course_id");
				String courseName = rs.getString("course_name");
				String credits = rs.getString("credits");
				String dName = rs.getString("d_name");
				course.setId(courseId);
				course.setName(courseName);
				course.setCredits(credits);
				
				Department dept = new Department();
				dept.setName(dName);
				course.setDepartment(dept);
				return course;
			}
		};
		List<Course> list = jdbc.query(psc, rowMapper);
		return list;
	}

}
