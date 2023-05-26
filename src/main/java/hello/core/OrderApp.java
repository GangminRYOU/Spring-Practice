package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;

public class OrderApp {
	public static void main(String[] args) {
		/*AppConfig appConfig = new AppConfig();
		MemberService memberService = appConfig.memberService();
		OrderService orderService = appConfig.orderService();*/
		//MemberService memberService = new MemberServiceImpl(null);
		//OrderService orderService = new OrderServiceImpl(null, null);
		//생성자 주입된 그림을 완성시키고 완성된 OrderServiceImple에서 객체를 생성해서 반환
		//기존에는 impl안에서 생성, 이제는 AppConfig가 갈아 끼워준다.
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
		OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);
		Order order = orderService.createOrder(memberId, "itemA", 10000);
		System.out.println(order);
		System.out.println("order.calculatePrice() = " + order.calculatePrice());
	}
}
