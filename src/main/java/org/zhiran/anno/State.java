package org.zhiran.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.zhiran.anno.validation.StateValidation;

import java.lang.annotation.*;

//这里使用注解是为了减低代码耦合度
@Documented//引用源文件
@Target({ElementType.FIELD})//哪些函数类型可以用
@Retention(RetentionPolicy.RUNTIME)//定义运行在程序运行的时候
@Constraint(validatedBy = {StateValidation.class})
public @interface State {
    String message() default "{状态只能为已发布或草稿}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
