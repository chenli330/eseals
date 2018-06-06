package cn.biceng.maven;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(DiConfig.class);
		UseFunctionService functionService=context.getBean(UseFunctionService.class);
		System.out.println(functionService.SayHello("hello word!"));
		context.close();
	}
}
