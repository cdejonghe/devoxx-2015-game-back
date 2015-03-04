package fr.sii.survival;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OsefApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class AbstractFunctionalTest {
	@Value("${local.server.port}")
	protected int port;


}
