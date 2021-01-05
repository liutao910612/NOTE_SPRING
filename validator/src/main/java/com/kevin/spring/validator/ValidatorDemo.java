package com.kevin.spring.validator;

import com.kevin.base.domain.User;
import com.kevin.base.utils.PrintUtil;
import org.springframework.context.MessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;

import static com.kevin.spring.validator.ErrorsMessageDemo.createMessageSource;

/**
 * 自定义Spring{@link Validator}示例
 *
 * @Author:Kevin
 * @Date:Created in 20:16 2021/1/5
 */
public class ValidatorDemo {
    public static void main(String[] args) {

        //1.创建Validator
        Validator validator = new UserValidator();

        //2.判断是否支持目标对象的类型
        User user = new User();
        PrintUtil.print("user对象是否被UserValidator支持校验："+validator.supports(user.getClass()));

        //3.创建Errors对象
        Errors errors = new BeanPropertyBindingResult(user,"user");
        validator.validate(user,errors);

        //4.获取MessageSource对象
        MessageSource messageSource = createMessageSource();
        errors.getAllErrors().forEach(error -> {
            String message = messageSource.getMessage(error.getCode(),error.getArguments(), Locale.getDefault());
            PrintUtil.print(message);
        });
    }

    static class UserValidator implements Validator{

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"id","id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","name.required");
        }
    }
}
