package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;

public class MemberServiceTest {
	MemberService memberService;

	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
	}

	//테스트를 실행할때마다 수행되는 코드
	@Test
	void join() {
		//given
		Member member = new Member(1L, "memberA", Grade.VIP);
		//when
		memberService.join(member);
		Member findMember = memberService.findMember(1L);
		//then
		//검증은 Assertions라는게 있다.
		Assertions.assertThat(member).isEqualTo(findMember);
		//만약에 같으면, 테스트가 성공한 것

	}
}
