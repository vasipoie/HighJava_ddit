package kr.or.ddit.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 	Annotation에 대하여...
 	
 	프로그램 소스코드 안에 다른 프로그램을 위한 정보를 미리 약속된 형식으로 포함시킨 것
 	(JDK 1.5부터 지원함)
 	주석처럼 기존 프로그램에 영향을 미치지 않으면서도 다른 누군가에게는 유용한 정보를 제공할 수 있다
 	
 	종류 : 1. 표준(일반) 애너테이션
 		  2. 메타 애너테이션(애너테이션을 위한 에너테이션. 즉, 애너테이션을 정의할 때 사용하는 애너테이션)
 		  
 	애너테이션 정의하기
 	 @interface 애너테이션이름{
 		요소타입 타입요소이름(); //반환타입이 있고, 매개변수는 없는 추상메서드 형태
 		...
     }
     
     애너테이션 요소의 규칙
     1. 요소타입은 기본형, String, enum, annotation, Class만 허용된다.
     2. ()안에 매개변수를 선언할 수 없다.
     3. 예외를 선언할 수 없다.
     4. 요소타입에 제너릭 타입 글자를 사용할 수 없다.
 */

//@Target({ElementType.METHOD, ElementType.TYPE})	//적용대상
@Target(ElementType.METHOD)	//적용대상
@Retention(RetentionPolicy.RUNTIME) //유지기간
public @interface T01PrintAnnotation {
	int id = 100; 				//상수 선언 가능
	String value() default "-"; //기본값을 '-'로 지정
	int count() default 20;		//기본값을 20으로 지정
}
