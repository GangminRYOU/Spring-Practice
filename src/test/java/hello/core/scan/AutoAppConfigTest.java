package hello.core.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;

public class AutoAppConfigTest {
	@Test
	void basicScan() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
			AutoAppConfig.class);
		MemberService memberService = annotationConfigApplicationContext.getBean(MemberService.class);
		Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
	}
}
