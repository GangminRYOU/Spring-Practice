package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
	@Test
	void statefulServiceSingleton() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);
		//ThreadA : A사용자가 10000원 주문
		int price = statefulService1.order("userA", 10000);
		//ThreadB : B 사용자가 20000원 주문
		int price2 = statefulService1.order("userB", 20000);
		//A가 주문하고 조회하는 사이에 B가 끼어 들어온것
		//ThreadA: 사용자 A가 주문 금액 조회
		//int price = statefulService1.getPrice();//의도는 10000원이 나와야 한다.
		System.out.println("price = " + price);
		//서비스 1이든 2든 같은 객체 사용
		Assertions.assertThat(price).isEqualTo(20000);
		//사용자 A화면에 20000원이 나옴 -> 서비스 망한다.
	}

	static class TestConfig {
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}