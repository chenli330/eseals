package cn.biceng.maven;


public class UseFunctionService {

	FunctionService functionService;
	
	public String SayHello(String word){
		return  functionService.sayHello(word);
	}
	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}
}
