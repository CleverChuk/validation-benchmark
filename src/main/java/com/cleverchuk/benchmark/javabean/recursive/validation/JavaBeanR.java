package com.cleverchuk.benchmark.javabean.recursive.validation;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * @author carlos.raphael.lopes@gmail.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JavaBeanR {
    @Pattern(regexp = "[Aa-zZ0-9.-]+$")
    private  String fieldA;
    @Valid
    private  NestedJavaBeanR nestedJavaBeanR;
}
