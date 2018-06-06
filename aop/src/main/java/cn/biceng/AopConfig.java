package cn.biceng;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("cn.biceng")
@EnableAspectJAutoProxy //1
public class AopConfig {

}
