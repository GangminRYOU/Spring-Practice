package hello.core.scan.filter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//이 Annotation이 붙은 것은 컴포넌트 스캔에 추가할거다.
@Target(ElementType.TYPE) // TYPE은 클래스 레벨에 붙는다.
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
