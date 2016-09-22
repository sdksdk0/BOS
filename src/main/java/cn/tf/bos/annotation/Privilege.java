package cn.tf.bos.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解
@Retention(RetentionPolicy.RUNTIME)
@Target(value=(ElementType.METHOD))
@Inherited
public @interface Privilege {
	
	String value();  //访问的业务方法需要的权限
	
	
	
}
