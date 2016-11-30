package com.scipionyx.butterflyeffect.frontend.checkfraud.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CheckFraudServiceFactoryTest {

	@Autowired
	private CheckFraudServiceFactory checkFraudServiceFactory;

	@Test
	public void test() {
		//ICheckImageService service = checkFraudServiceFactory.instance();
		//service.ping();
	}

}
