package com.kevin.spring.verification;

import com.kevin.base.domain.User;
import com.kevin.base.utils.PrintUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 * 错误文案demo
 *
 * @Author:Kevin
 * @Date:Created in 20:32 2021/1/3
 * @see Errors
 */
public class ErrorsMessageDemo {
    public static void main(String[] args) {

        //1.创建User对象
        User user = new User();

        //2.选择Errors - BeanPropertyBindingResult
        Errors errors = new BeanPropertyBindingResult(user,"user");

        //3.调用reject或rejectValue方法
        //reject生成ObjectError
        //rejectValue生成FieldError
        errors.reject("user.properties.not.null");

        //user.name=user.getName()
        errors.rejectValue("name","name.required");

        //4.获取Errors中的ObjectError和FieldError
        List<ObjectError> globalErrors = errors.getGlobalErrors();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<ObjectError> allErrors = errors.getAllErrors();

        //5.通过ObjectError和FieldError中的code和args来关联MessageSource实现
        MessageSource messageSource = createMessageSource();
        allErrors.forEach(error -> {
            String message = messageSource.getMessage(error.getCode(),error.getArguments(),Locale.getDefault());
            PrintUtil.print(message);
        });
    }

    private static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("name.required", Locale.getDefault(),"the name of User must not be null");
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(),"The user must exist");
        return messageSource;
    }
}
