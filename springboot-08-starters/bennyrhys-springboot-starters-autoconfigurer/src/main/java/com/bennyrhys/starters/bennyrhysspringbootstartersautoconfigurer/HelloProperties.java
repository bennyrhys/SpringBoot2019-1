package com.bennyrhys.starters.bennyrhysspringbootstartersautoconfigurer;

import org.springframework.boot.context.properties.ConfigurationProperties;

//将能配置的属性都配置中此处
@ConfigurationProperties(prefix = "bennyrhys.hello")
public class HelloProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
