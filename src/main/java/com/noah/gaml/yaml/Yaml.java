package com.noah.gaml.yaml;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class Yaml {

    public static Yaml read(String string) {
        String[] splitAtNewline = string.split("\n");
        List<String> lines = Arrays.asList(splitAtNewline);
        YamlSection root = YamlSection.read(lines);
        return new Yaml(root);
    }

    private YamlSection root;

    public YamlSection getSection(String key) {
        return this.root.getSection(key);
    }

    public Integer getInt(String key) {
        return this.root.getInt(key);
    }

    public Boolean getBoolean(String key) {
        return this.root.getBoolean(key);
    }

    public void print(PrintStream stream) {
        this.root.print(stream);
    }

}
