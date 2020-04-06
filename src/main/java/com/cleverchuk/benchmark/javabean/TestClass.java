package com.cleverchuk.benchmark.javabean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.cleverchuk.benchmark.javabean.recursive.validation.JavaBeanR;
import com.cleverchuk.benchmark.javabean.recursive.validation.NestedJavaBeanR;
import com.cleverchuk.benchmark.javabean.setter.validation.JavaBeanS;
import com.cleverchuk.benchmark.javabean.setter.validation.NestedJavaBeanS;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class TestClass {
    @Test
    @SneakyThrows
    public void writeOutS(){
        ObjectMapper objectMapper = new ObjectMapper();
        NestedJavaBeanS nestedJavaBean3 = NestedJavaBeanS.builder().fieldA("nested-3#").build();
        NestedJavaBeanS nestedJavaBean2 = NestedJavaBeanS.builder().fieldA("nested-2").nestedJavaBeanS(nestedJavaBean3).build();
        NestedJavaBeanS nestedJavaBean1 = NestedJavaBeanS.builder().fieldA("nested-1").nestedJavaBeanS(nestedJavaBean2).build();
        System.out.println(objectMapper.writeValueAsString(JavaBeanS.builder().fieldA("fieldA").nestedJavaBeanS(nestedJavaBean1).build()));
        /*
        {"fieldA":"fieldA","nestedJavaBeanS":{"fieldA":"nested-1","nestedJavaBeanS":{"fieldA":"nested-2","nestedJavaBeanS":{"fieldA":"nested-3","nestedJavaBeanS":null}}}}
         */

        objectMapper.readValue("{\"fieldA\":\"fieldA\",\"nestedJavaBeanS\":null}", JavaBeanS.class);
    }

    @Test
    @SneakyThrows
    public void writeOutR(){
        ObjectMapper objectMapper = new ObjectMapper();
        NestedJavaBeanR nestedJavaBean3 = NestedJavaBeanR.builder().fieldA("nested-3").build();
        NestedJavaBeanR nestedJavaBean2 = NestedJavaBeanR.builder().fieldA("nested-2").nestedJavaBeanR(nestedJavaBean3).build();
        NestedJavaBeanR nestedJavaBean1 = NestedJavaBeanR.builder().fieldA("nested-1").nestedJavaBeanR(nestedJavaBean2).build();
        System.out.println(objectMapper.writeValueAsString(JavaBeanR.builder().fieldA("fieldA").nestedJavaBeanR(nestedJavaBean1).build()));
        /*
        {"fieldA":"fieldA","nestedJavaBeanR":{"fieldA":"nested-1","nestedJavaBeanR":{"fieldA":"nested-2","nestedJavaBeanR":{"fieldA":"nested-3","nestedJavaBeanR":null}}}}
         */
    }

}
