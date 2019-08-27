package org.study.cinema;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(locations = "classpath:application-test.properties")
@TestPropertySource(locations = "classpath:application-test.properties")
public class CinemaApplicationTests {

	@Autowired
	protected ApplicationContext context;

	public static void printBeforeContextInfo(ApplicationContext context, Class clazz) {
		System.out.println("\n---------------------------------" + clazz.getSimpleName() + " BEFORE ---------------------------------\n");
		System.out.println("Context hash: " + context.hashCode());
		System.out.println("Bean count: " + context.getBeanDefinitionCount());
		System.out.println("MockMvc count: " + context.getBeansOfType(MockMvc.class).size());
	}

	@Before
	public void setUp() {
		printBeforeContextInfo(context, this.getClass());
	}

}
