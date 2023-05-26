package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

//Spring에서는 설정정보에 Configuration을 적용하게 되어있다.
@Configuration
public class AppConfig {

	/*
	 * 이전에는 객체를 생성하고, 어디에다 인터페이스에 어떤 구현체가 들어가야해
	 * 같은걸 MemberService안에서 직접 했었다.
	 * MemberRepository memberRepository = new MemoryMemberRepository();
	 * 이 코드를 MemberServiceImpl이 해줬다.
	 * -> 마치 배우가 직접 담당배우를 섭외하는 것
	 * 이런것은 AppConfig에서 다하는거다. (Application의 환경 구성에 대한 것)
	 * */
	//MemberService역할
	@Bean
	public MemberService memberService() {
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(getMemberRepository());
	}

	//MemberRepository라는 인터페이스를 반환해주는 역할
	@Bean
	public MemberRepository getMemberRepository() {
		System.out.println("call AppConfig.getMemberRepository");
		return new MemoryMemberRepository();
	}

	//OrderService 역할까지 역할들이 모두 드러난다.
	@Bean
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(getMemberRepository(), discountPolicy());
	}

	@Bean
	public DiscountPolicy discountPolicy() {
		return new RateDiscountPolicy();
		//return new FixDiscountPolicy();
	}
}
