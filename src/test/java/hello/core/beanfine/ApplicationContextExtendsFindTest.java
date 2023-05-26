package hello.core.beanfine;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;

public class ApplicationContextExtendsFindTest {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

	@Test
	@DisplayName("부모타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
	void findBeanByParentTypeDuplicate() {
		//DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
		assertThrows(NoUniqueBeanDefinitionException.class,
			() -> ac.getBean(DiscountPolicy.class));
	}

	@Test
	@DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈이름을 지정하면 된다.")
	void findByParentTypeBeanName() {
		DiscountPolicy bean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
		assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
	}

	//구체적인 타입으로 지정하면 중복될 이유가 없다.
	@Test
	@DisplayName("특정 하위 타입으로 조회 - 물론 안좋은 방법")
	void findBeanBySubType() {
		RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
		assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
	}

	@Test
	@DisplayName("부모 타입으로 모두 조회하기")
	void findAllBeanByParentName() {
		Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
		assertThat(beansOfType.size()).isEqualTo(2);
		for (String s : beansOfType.keySet()) {
			System.out.println("s = " + s + " values = " + beansOfType.get(s));
			//실제 테스트 코드 짤때는 출력물을 남기면 안된다.
			//통과실패를 시스템이 결정해야한다.
			//테스트 통과하는지 안하는지 만 보면된다.
		}
	}

	//스프링에 있는것 까지 다 튀어 나오는것
	@Test
	@DisplayName("부모 타입으로 모두 조회하기 - Object")
	void finAllBeanByObjectType() {
		Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
		for (String s : beansOfType.keySet()) {
			System.out.println("key = " + s + " values = " + beansOfType.get(s));
		}
	}

	@Configuration
	static class TestConfig {
		//반환값을 인터페이스로 해놓는 이유 : 역할만 보게 하려고
		@Bean
		DiscountPolicy rateDiscountPolicy() {
			return new RateDiscountPolicy();
		}

		@Bean
		DiscountPolicy fixDiscountPolicy() {
			return new FixDiscountPolicy();
		}
	}
}
