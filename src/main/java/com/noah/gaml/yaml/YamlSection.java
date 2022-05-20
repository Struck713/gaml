package com.noah.gaml.yaml;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.PrintStream;
import java.util.*;

public class YamlSection implements YamlChild {

    public static YamlSection read(List<String> lines) {
        YamlSection ret = new YamlSection();

        String nextKey = null;
        List<String> nextLines = new ArrayList<>();
        boolean depth = false;
        for (String line : lines) {

            String[] split = line.split(":");
            if (split.length < 1 || split.length > 2) continue;
            String key = split[0].trim();

            if (depth) {
                if (!line.trim().equals(line)) {
                    nextLines.add(line.substring(2)); //remove 2 spaces
                    continue;
                }
                depth = false;
                if (nextKey != null) ret.put(nextKey, YamlSection.read(nextLines)); // recurse
            }

            if (split.length == 1) { depth = true; nextKey = key; nextLines.clear(); continue; }

            if (split.length == 2) {
                String value = split[1].trim();
                ret.put(key, new YamlObject(value));
                continue;
            }
        }
        return ret;
    }

    private Map<String, YamlChild> children;

    private YamlSection() {
        this.children = new LinkedHashMap<>();
    }

    protected void print(PrintStream stream, String spacing) {
        for (Map.Entry<String, YamlChild> entry : children.entrySet()) {
            String key = entry.getKey();
            YamlChild value = entry.getValue();

            if (value instanceof YamlSection) {
                YamlSection section = (YamlSection) value;
                stream.println(spacing + key + ":");
                section.print(stream, (spacing + "  "));
                continue;
            }

            YamlObject object = (YamlObject)value;
            stream.println(spacing + key + ": " + object.getAsString());
        }
    }

    public YamlSection getSection(String key) {
        if (this.children.containsKey(key)) {
            YamlChild child = this.children.get(key);
            if (child instanceof YamlSection) return (YamlSection) child;
        }
        return null;
    }

    public String getString(String key) {
        if (this.children.containsKey(key)) {
            YamlChild child = this.children.get(key);
            if (!(child instanceof YamlObject)) return null;
            YamlObject object = (YamlObject) child;
            return object.getAsString();
        }
        return null;
    }

    public Boolean getBoolean(String key) {
        if (this.children.containsKey(key)) {
            YamlChild child = this.children.get(key);
            if (!(child instanceof YamlObject)) return null;
            YamlObject object = (YamlObject) child;
            return object.getAsBoolean();
        }
        return null;
    }

    public Integer getInt(String key) {
        if (this.children.containsKey(key)) {
            YamlChild child = this.children.get(key);
            if (!(child instanceof YamlObject)) return null;
            YamlObject object = (YamlObject) child;
            return object.getAsInt();
        }
        return null;
    }

    protected void print(PrintStream stream) {
        this.print(stream, "");
    }

    protected void put(String key, YamlChild value) {
        this.children.put(key, value);
    }

}
