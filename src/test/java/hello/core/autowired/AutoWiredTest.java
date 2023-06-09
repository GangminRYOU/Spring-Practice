package hello.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import hello.core.member.Member;

public class AutoWiredTest {
	@Test
	void AutowiredOptions() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
			TestBean.class);

	}

	static class TestBean {
		@Autowired(required = false)
		public void setNoBean1(Member noBean1) {
			//Member는 스프링 빈이 아니다.
			System.out.println("noBean1 = " + noBean1);
		}

		@Autowired
		public void setNoBean2(@Nullable Member noBean2) {
			//Member는 스프링 빈이 아니다.
			System.out.println("noBean2 = " + noBean2);
		}

		@Autowired
		public void setNoBean3(Optional<Member> noBean3) {
			System.out.println("noBean3 = " + noBean3);
		}
	}
}
