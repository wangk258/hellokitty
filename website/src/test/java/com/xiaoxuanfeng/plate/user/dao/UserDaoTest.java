package com.xiaoxuanfeng.plate.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.xiaoxuanfeng.plate.common.util.MD5Util;
import com.xiaoxuanfeng.plate.core.TestBase;
import com.xiaoxuanfeng.plate.user.domain.User;

public class UserDaoTest extends TestBase {

	@Autowired
	private UserDao userDao;
	
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private long start=0l;
	
	@Before
	public void beginTime(){
		start = System.currentTimeMillis();
	}
	
	@After
	public void endTime(){
		System.out.println("耗时："+((System.currentTimeMillis() - start))+"毫秒");
	}
	
	@Test
	public void saveUserTest() throws Exception{
		User user = new User();
		char[] chars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		for(int i=0;i<9999;i++){
			System.out.println(i);
			String randomString = getRandomString(chars);
			user.setUsername(randomString);
			user.setPassword(MD5Util.string2MD5(randomString));
			userDao.save(user);
		}
	}
	
	@Test
	public void saveUserWithBatchTest() throws Exception{
		char[] chars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		Session session = this.hibernateTemplate.getSessionFactory().openSession();
		Connection conn = session.connection();
		conn.setAutoCommit(false);
		PreparedStatement ps = conn.prepareStatement("insert into t_user (password, username) values (?, ?)");
		for(int i=1;i<=999;i++){
			System.out.println(i);
			String randomString = getRandomString(chars);
			ps.setString(1, MD5Util.string2MD5(randomString));
			ps.setString(2, randomString);
			ps.addBatch();
			if(i%300 == 0){
				ps.executeBatch();
				conn.commit();
			}
		}
		ps.executeBatch();
		conn.commit();
		session.flush();
		session.close();
	}

	private String getRandomString(char[] chars) {
		String randomString = "";
		for(int j=0;j<=7;j++){
			int index = (int)(Math.random()*100);
			if(index >= chars.length){
				index -= chars.length;
			}
			randomString += chars[index];
		}
		return randomString;
	}
	
	@Test
	public void GetUserTest() throws Exception{
		System.out.println(userDao.executeBySQL("select count(id) from t_user"));
	}
	
	public static void main(String[] args){
		System.out.println((int)(Math.random()*10));
	}
	
	
}
