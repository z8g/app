package com.zxy97.ssm.template;

public class TemplateFactory extends AbstractFactory {

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Template> T createTemplate(Class<T> c) {
        Template result = null;
        try {
            result = (T) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return (T) result;
    }
}
