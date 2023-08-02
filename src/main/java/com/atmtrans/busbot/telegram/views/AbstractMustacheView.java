package com.atmtrans.busbot.telegram.views;

import static com.atmtrans.busbot.springframework.utils.NameUtils.classToBeanName;

import java.io.IOException;
import java.io.StringWriter;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractMustacheView implements View {

    @Autowired
    private MustacheFactory factory;

    protected String renderTemplate(Object model) throws IOException {
        String modelName = classToBeanName(getClass());

        Mustache mustache = factory.compile("templates/" + modelName + ".mustache");
        StringWriter writer = new StringWriter();
        mustache.execute(writer, model).flush();
        return writer.toString();
    }
}
