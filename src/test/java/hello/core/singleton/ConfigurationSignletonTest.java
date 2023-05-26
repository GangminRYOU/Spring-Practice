package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;

public class ConfigurationSignletonTest {
	@Test
	void configurationTest() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
		MemberRepository memberRepository = ac.getBean("getMemberRepository", MemberRepository.class);
		//아까 getMemberRepository를 호출하기 위해 impl꺼냄
		MemberRepository memberRepository1 = memberService.getMemberRepository();
		MemberRepository memberRepository2 = orderService.getMemberRepository();
		//이게 같으면 싱긅ㄴ을 유지
		System.out.println("memberService -> memberRepository1 = " + memberRepository1);
		System.out.println("orderService -> memberRepository2 = " + memberRepository2);
		System.out.println("memberRepository = " + memberRepository);
		Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
		Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
	}

	@Test
	void configurationDeep() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		AppConfig bean = ac.getBean(AppConfig.class);
		System.out.println("bean = " + bean.getClass());
	}
}
