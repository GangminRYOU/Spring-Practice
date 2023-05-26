package hello.core.order;

import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor //final이 붙은 필수값인 멤버를 가지고 생성자를 만들어 준다.
public class OrderServiceImpl implements OrderService {
	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	@Override
	public Order createOrder(long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		/*이게 진짜 설게가 잘된 것
		 * orderService입장에서는 나는 할인에 대한건 모르겠다.
		 * 할인에 대한 것은 DiscountPolicy니가 알아서 해줘라고 설계 한것
		 * 단일 책임원칙(SRP)을 잘 지킨것
		 * 주문쪽 까지 같이 고칠 필요가 없다.
		 * 만약 단일 책임 원칙이 잘 설계가 되지 않고, Discount Policy까지 없었다면,
		 * OrderService에서 할인과 관련된 변경이 들어와도 OrderSerivice까지 고쳐야 한다.
		 * */
		int discountPrice = discountPolicy.discount(member, itemPrice);
		//        Grade만 넘길지 Member자체를 넘길지 고민을 해보면 된다.
		//        프로젝트와 상황에 따라 다르다.
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	//테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
