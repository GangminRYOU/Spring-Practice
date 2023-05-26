package hello.core.beanfine;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class ApplicationContextBasicFindTest {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("빈 이름으로 조회")
	void findBeanByName() {
		MemberService memberService = ac.getBean("memberService", MemberService.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

	}

	@Test
	@DisplayName("이름 없이 타입으로만 조회")
	void findBeanByType() {
		//인터페이스로 조회 -> 인터페이스의 구현체가 대상이 된다.
		MemberService memberService = ac.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("구현체로 조회")
	void findBeanByName2() {
		MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

	}

	@Test
	@DisplayName("빈이름으로 조회X")
	void findBeanByNameX() {
		//MemberService memberService = ac.getBean("xxxxxx", MemberService.class);

		assertThrows(NoSuchBeanDefinitionException.class,
			() -> ac.getBean("xxxxxx", MemberService.class));
		//이 로직을 실행하면 예외가 터져야 한다.
		//터지면 성공
	}
}
