package org.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Desmond on 28/11/2017.
 */
@Component("configTest")
public class ConfigTest {

    @Value("${creditrule}")//property 中属性
    private String creditrule;

    public String getCreditrule() {
        return creditrule;
    }

    public void setCreditrule(String creditrule) {
        this.creditrule = creditrule;
    }
}
