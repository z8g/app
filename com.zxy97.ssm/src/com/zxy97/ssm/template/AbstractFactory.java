package com.zxy97.ssm.template;

public abstract class AbstractFactory {
    public abstract <T extends Template> T createTemplate(Class<T> c);
}
