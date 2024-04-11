package org.zhiran.anno.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.zhiran.anno.State;

//用作state注解中校验过程的代码
public class StateValidation implements ConstraintValidator<State,String>{
    /**
     *
     * @param s
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null){
            return  false;
        }
        if (s.equals("已发布")|| s.equals("草稿")){
            return true;
        }
        return false;
    }
}
