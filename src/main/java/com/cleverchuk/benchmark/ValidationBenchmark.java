package com.cleverchuk.benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.cleverchuk.benchmark.javabean.recursive.validation.JavaBeanR;
import com.cleverchuk.benchmark.javabean.setter.validation.JavaBeanS;
import lombok.SneakyThrows;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Fork(2)
@Warmup(iterations = 5, time = 2)
@Measurement(iterations = 5, time = 1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class ValidationBenchmark {

    ObjectMapper objectMapper;
    Validator validator;

    @Param({
            "{\"fieldA\":\"fieldA\",\"nestedJavaBeanR\":null}",
            "{\"fieldA\":\"fieldA\",\"nestedJavaBeanR\":{\"fieldA\":\"nested-1\",\"nestedJavaBeanR\":null}}",
            "{\"fieldA\":\"fieldA\",\"nestedJavaBeanR\":{\"fieldA\":\"nested-1\",\"nestedJavaBeanR\":{\"fieldA\":\"nested-2\",\"nestedJavaBeanR\":null}}}",
            "{\"fieldA\":\"fieldA\",\"nestedJavaBeanR\":{\"fieldA\":\"nested-1\",\"nestedJavaBeanR\":{\"fieldA\":\"nested-2\",\"nestedJavaBeanR\":{\"fieldA\":\"nested-3\",\"nestedJavaBeanR\":null}}}}"
    })
    String recursive;

    @Param({
            "{\"fieldA\":\"fieldA\",\"nestedJavaBeanS\":null}",
            "{\"fieldA\":\"fieldA\",\"nestedJavaBeanS\":{\"fieldA\":\"nested-1\",\"nestedJavaBeanS\":null}}",
            "{\"fieldA\":\"fieldA\",\"nestedJavaBeanS\":{\"fieldA\":\"nested-1\",\"nestedJavaBeanS\":{\"fieldA\":\"nested-2\",\"nestedJavaBeanS\":null}}}",
            "{\"fieldA\":\"fieldA\",\"nestedJavaBeanS\":{\"fieldA\":\"nested-1\",\"nestedJavaBeanS\":{\"fieldA\":\"nested-2\",\"nestedJavaBeanS\":{\"fieldA\":\"nested-3\",\"nestedJavaBeanS\":null}}}}"
    })
    String setter;


    @Setup
    @SneakyThrows
    public void setup() {
        objectMapper = new ObjectMapper();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Benchmark
    @SneakyThrows
    public void recursiveValidation(){
        validator.validate(objectMapper.readValue(recursive, JavaBeanR.class));
    }

    @Benchmark
    @SneakyThrows
    public void setterValidation(){
        objectMapper.readValue(setter, JavaBeanS.class);
    }

    public static void main(String... args) throws IOException, RunnerException {
        Main.main(args);
    }
}
