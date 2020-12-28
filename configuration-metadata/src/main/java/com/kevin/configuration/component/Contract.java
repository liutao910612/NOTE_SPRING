package com.kevin.configuration.component;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author:Kevin
 * @Date:Created in 21:07 2020/12/28
 */
@Component
public class Contract {
    private Long contractNo;
    private Integer contracType;
    private String name;

    @PostConstruct
    public void init() {
        this.contractNo = System.currentTimeMillis();
        this.contracType = 1;
        this.name = "DEMO";
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractNo=" + contractNo +
                ", contracType=" + contracType +
                ", name='" + name + '\'' +
                '}';
    }
}
