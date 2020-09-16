package workshop;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import workshop.controller.DesignTacoController;
import workshop.controller.OrderController;

@SpringBootTest
class MainApplicationTest {

	@Autowired
	private DesignTacoController designController;

	@Autowired
	private OrderController orderController;

	@Test
	public void contextLoads() {
		assertThat(designController).isNotNull();
		assertThat(orderController).isNotNull();
	}
}
