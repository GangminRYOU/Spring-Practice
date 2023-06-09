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

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class ApplicationContextSameBeanFindTest {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 오류가 발생한다.")
	void findBeanByTypeDuplicate() {
		//MemberRepository bean = ac.getBean(MemberRepository.class);
		//Spring입장에서는 뭘 선택해야하지?
		//NoUniqueBean예외가 터진다.
		assertThrows(NoUniqueBeanDefinitionException.class,
			() -> ac.getBean(MemberRepository.class));
	}

	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈이름을 지정하면 된다.")
	void findBeanByName() {
		MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
		assertThat(memberRepository).isInstanceOf(MemberRepository.class);
	}

	@Test
	@DisplayName("특정 타입을 모두 조회하기")
	void findAllBeanByType() {
		Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
		for (String s : beansOfType.keySet()) {
			System.out.println("Key = " + s + " Values = " + beansOfType.get(s));
		}
		System.out.println("beansOfType = " + beansOfType);
		assertThat(beansOfType.size()).isEqualTo(2);
	}

	//이 안에서만 쓰겠다는 것
	@Configuration
	static class SameBeanConfig {
		//객체 타입이 달라도 빈 이름은 같을 수 있다.
		@Bean
		public MemberRepository memberRepository1() {
			return new MemoryMemberRepository();
		}

		@Bean
		public MemberRepository memberRepository2() {
			return new MemoryMemberRepository();
		}
	}
}
