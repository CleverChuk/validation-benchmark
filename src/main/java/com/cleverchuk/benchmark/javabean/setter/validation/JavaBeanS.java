package com.cleverchuk.benchmark.javabean.setter.validation;

import lombok.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author carlos.raphael.lopes@gmail.com
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JavaBeanS {

    @Setter(value = AccessLevel.NONE)
    private  String fieldA;

    private  NestedJavaBeanS nestedJavaBeanS;

    public void setFieldA(String fieldA){
        Pattern pattern = Pattern.compile("[Aa-zZ0-9.-]+$");
        Matcher matcher = pattern.matcher(fieldA);
        if (!matcher.find()){
            throw new IllegalArgumentException("must be alphanumeric with dot or dashes");
        }

        this.fieldA = fieldA;
    }
}
