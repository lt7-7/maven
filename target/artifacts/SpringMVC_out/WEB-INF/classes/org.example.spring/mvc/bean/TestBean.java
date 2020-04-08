package org.example.spring.mvc.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component

public class TestBean {
    private Long id;
    private  String name;

    @Override
    public String toString(){
        return "TestBean{"+
                "id = " +id+
                ",name='"+name+'\''+
                '}';
    }

    public TestBean(){

    }
    public TestBean(Long id , String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
