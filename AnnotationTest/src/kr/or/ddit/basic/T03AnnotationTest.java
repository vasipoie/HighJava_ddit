package kr.or.ddit.basic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class T03AnnotationTest {
	public static void main(String[] args) {
		
		System.out.println("id 값 : " +T01PrintAnnotation.id);
		System.out.println();
		
		//Reflection(=API기술)을 이용해서 메서드 정보 가져오기
		Method[] methodArr = T02Service.class.getDeclaredMethods();
		
		for (Method m : methodArr) {
			System.out.println("메서드명 : "+m.getName());
			
			Annotation[] annos = m.getDeclaredAnnotations();
			
			for (Annotation anno : annos) {
				String annoName = anno.annotationType().getSimpleName();
				if(annoName.equals("T01PrintAnnotation")) {
					//T01PrintAnnotation 타입에 접근하기 위해 casting
					T01PrintAnnotation printAnno = (T01PrintAnnotation) anno;
					String value = printAnno.value();
					int count = printAnno.count();
					
					for(int i=0; i<count; i++) {
						System.out.print(value);
					}
				}
			}
			System.out.println();
		}
	}
}
