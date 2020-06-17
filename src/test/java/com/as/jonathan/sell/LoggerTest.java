package com.as.jonathan.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/14/2020
 */
@SpringBootTest
@Slf4j
public class LoggerTest {
//	private Logger log = LoggerFactory.getLogger(LoggerTest.class);

	@Test
	public void test1() {
		String name = "123";
		String password = "123";
		log.debug("debug....");
		log.info("running....");
		log.error("error....");
		log.warn("warn....");
		log.info("name: {}, password {}.",name, password);
	}
}
