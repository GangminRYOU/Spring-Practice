package hello.core.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MemoryMemberRepository implements MemberRepository {

	private static Map<Long, Member> store = new HashMap<>();

	@Override
	public void save(Member member) {
		//예외처리는 일단 넘긴다.
		store.put(member.getId(), member);
	}

	@Override
	public Member findById(Long memberId) {
		return store.get(memberId);
	}
}
