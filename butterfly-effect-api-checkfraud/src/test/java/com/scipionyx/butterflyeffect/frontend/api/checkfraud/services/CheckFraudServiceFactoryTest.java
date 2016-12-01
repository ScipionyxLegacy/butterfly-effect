package com.scipionyx.butterflyeffect.frontend.api.checkfraud.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.scipionyx.butterflyeffect.api.checkfraud.model.CheckImage;
import com.scipionyx.butterflyeffect.api.checkfraud.services.CheckFraudServiceFactory;
import com.scipionyx.butterflyeffect.api.checkfraud.services.checkimage.ICheckImageService;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {})
public class CheckFraudServiceFactoryTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CheckFraudServiceFactoryTest.class);

	@Autowired
	private CheckFraudServiceFactory checkFraudServiceFactory;

	/**
	 * 
	 */
	@Test
	public void ping() {
		ICheckImageService service = checkFraudServiceFactory.instance();
		String a = service.ping();
		Assert.assertEquals("Hello", a);
		LOGGER.info("Test Ping:" + a);
	}

	/**
	 * 
	 */
	@Test
	public void analyzeFile() {
		String fileName = "CHECK_01.png";
		ICheckImageService service = checkFraudServiceFactory.instance();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		CheckImage a = service.analyze(file);
		//
		Assert.assertNotNull(a);
		Assert.assertEquals(fileName, a.getOriginalFileName());
		Assert.assertEquals(28841, a.getSize());
		LOGGER.info("Test Analyze:" + a.getSize());
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void analyzeByteArray() throws IOException {
		//
		String fileName = "CHECK_01.png";
		ICheckImageService service = checkFraudServiceFactory.instance();
		//
		File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
		//
		byte[] bs = new byte[(int) file.length()];
		FileInputStream fileInputStream = new FileInputStream(file);
		fileInputStream.read(bs);
		fileInputStream.close();

		CheckImage a = service.analyze(fileName, bs);
		//
		Assert.assertNotNull(a);
		//Assert.assertEquals(fileName, a.getOriginalFileName());
		Assert.assertEquals(28841, a.getSize());
		//
		LOGGER.info("Test Analyze:" + a.getSize());
	}

}
