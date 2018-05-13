package com.java.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.java.util.WeekNumUtil;

public class StudentTest extends BaseJunitTest {


	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@SuppressWarnings("deprecation")
	@Test
	public void weekworkService() {
		 String s="2017-9-4";
		 String s2="2017-10-9";
		 WeekNumUtil w=new WeekNumUtil();
		System.out.println(w.get(s, s2));
	}
	
}


