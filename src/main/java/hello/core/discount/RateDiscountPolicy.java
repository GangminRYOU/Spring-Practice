package hello.core.discount;

import org.springframework.stereotype.Component;

import hello.core.member.Grade;
import hello.core.member.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy {
	private int discountPercent = 10;

	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP) {
			//이 로직이 제일 불안
			return price * discountPercent / 100;
		} else {
			return 0;
		}
	}
}
