package cn.biceng.maven;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiConfig {
	@Bean
	public FunctionService functionService() {
		return new FunctionService();
	}
	// @Bean
	// public UseFunctionService UsefunctionService(){
	// UseFunctionService functionService=new UseFunctionService();
	// functionService.setFunctionService(functionService());
	// return functionService;
	// }

	@Bean
	public UseFunctionService useFunctionService(FunctionService functionService) {
		UseFunctionService useFunctionService = new UseFunctionService();
		useFunctionService.setFunctionService(functionService);
		return useFunctionService;
	}
}
