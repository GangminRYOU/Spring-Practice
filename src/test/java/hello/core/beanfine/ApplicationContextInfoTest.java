package hello.core.beanfine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

public class ApplicationContextInfoTest {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("모든 빈 출력하기")
	void findAllBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			//타입을 지정하지 않았기 때문에 Object가 나온다.
			Object bean = ac.getBean(beanDefinitionName);
			System.out.println("name = " + beanDefinitionName + " object = " + bean);
		}
	}

	@Test
	@DisplayName("애플리케이션 빈 출력하기")
	void findApplicationBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			//getBeanDefinition -> Bean하나하나에 대한 메타데이터 정보를 반환
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
			//Spring내부에서 뭔가 하려고 등록한게 아니라.
			//내가 Application 개발하기 위해 등록한 Bean들
			//내가 등록한 경우에만 출력한다.
			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean = ac.getBean(beanDefinitionName);
				System.out.println("name = " + beanDefinitionName + " object = " + bean);
			}
		}
	}
}
