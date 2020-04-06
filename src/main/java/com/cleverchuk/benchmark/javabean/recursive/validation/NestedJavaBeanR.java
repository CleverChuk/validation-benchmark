package com.cleverchuk.benchmark.javabean.recursive.validation;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * @author carlos.raphael.lopes@gmail.com
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NestedJavaBeanR {

    @Pattern(regexp = "[Aa-zZ0-9.-]+$")
    private String fieldA;
    @Valid
    private NestedJavaBeanR nestedJavaBeanR;
}
