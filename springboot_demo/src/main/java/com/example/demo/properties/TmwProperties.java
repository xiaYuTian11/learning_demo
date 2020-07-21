package com.example.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author TMW
 * @date 2020/7/21 10:06
 */
@ConfigurationProperties(prefix = "tmw")
// @Component
@Validated
public class TmwProperties {

    private String name;
    private Integer age;
    // @NotBlank(message = "address 不能为空")
    private String address;
    // @Valid  嵌套属性试用注解，默认不加也会验证，但是以防万一
    public String getName() {
        return name;
    }

    public TmwProperties setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public TmwProperties setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public TmwProperties setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public String toString() {
        return "TmwProperties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
