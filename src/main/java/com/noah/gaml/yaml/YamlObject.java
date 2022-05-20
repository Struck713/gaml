package com.noah.gaml.yaml;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class YamlObject implements YamlChild {

    private String object;

    public boolean getAsBoolean() {
        return Boolean.parseBoolean(this.object);
    }

    public String getAsString() {
        return this.object;
    }

    public int getAsInt() {
        return  Integer.parseInt(this.object);
    }

//    public YamlSection getAsSection() {
//        return (YamlSection) this.object;
//    }

}
