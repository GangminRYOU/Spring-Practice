package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {
	//가입을하고 회원을 찾으려면, MemberRepository 인터페이스가 필요
	private final MemberRepository memberRepository;

	//생성자를 통해서 memberRepository의 구현체를 넣는다.
	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	//MemberServiceImpl에 MemoryMemberRepository가 없다. -> 추상화에만 의존
	//구체적인것은 AppConfig가 넣어준다.
	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

	//테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
