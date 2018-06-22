package com.bridgeit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bridgeit.model.User1;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private DataSource datasource;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User1 select(String email, String password) {
		String query = "select * from User1 where email=? and password=?";
		System.out.println(email+","+password);
		User1 user = jdbcTemplate.queryForObject(query, new Object[] { email, password }, new RowMapper<User1>() {

			public User1 mapRow(ResultSet rs, int rowNum) throws SQLException {
				User1 user = new User1();
				user.setEmail(rs.getString("email"));
				return user;
			}
		});

		return user;
	}

	@Override
	public User1 getByEmail(String email) {
		String query = "select * from User1 where email=?";
		 List<User1> list = jdbcTemplate.query(query, new Object[] { email }, new RowMapper<User1>()  {

			@Override
			public User1 mapRow(ResultSet rs, int rowNum) throws SQLException {
				User1 user = new User1();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setCity(rs.getString("city"));
				return user;
			}
		});
		 System.out.println(list.size());
		
		 return list.size() > 0 ? list.get(0) : null;
	}
	

	@Override
	public int insert(User1 user) {
		
		String query = "insert into User1(name,password,email,city) values(?,?,?,?)";
		Object[] object = new Object[] { user.getName(), user.getPassword(), user.getEmail(), user.getCity() };

		int out = jdbcTemplate.update(query, object);
		if (out != 0) {
			System.out.println("Employee saved with name=" + user.getName());
		} else {
			System.out.println("Employee save failed with id=" + user.getName());
		}

		return out;

	}

	

}
