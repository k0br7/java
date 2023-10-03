package com.example.mod2.aop.Aspects;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointcuts {

    @Pointcut("execution( * abc*(..))")
    public void allAddMethods(){}

}
