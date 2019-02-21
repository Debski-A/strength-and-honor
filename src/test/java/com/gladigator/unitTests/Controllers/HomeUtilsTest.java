package com.gladigator.unitTests.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.gladigator.Controllers.Utils.HomeUtils;
@RunWith(MockitoJUnitRunner.class)
public class HomeUtilsTest {

	private HomeUtils homeUtils;
	
	@Before
	public void before() {
		homeUtils = new HomeUtils();
	}
	
	@Test
	public void shouldPreparePostEntityCorrectly() throws Exception {
		//given
		String content = "";
		//when	
		homeUtils.preparePostEntity(content);
	
	}
}
