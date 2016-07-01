package com.anan.plate.mood.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.anan.plate.core.TestBase;
import com.anan.plate.mood.dao.MoodDao;

public class MoodDaoTest extends TestBase {

	@Autowired
	private MoodDao moodDao;
	
	@Test
	public void catchTest(){
		try {
			int i=1/0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(11111);
	}
	
	public static void main(String[] args) {
		
		try {
			int i = 0;
			i++;
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(3333);
	}
}
