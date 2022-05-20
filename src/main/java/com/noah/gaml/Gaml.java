package com.noah.gaml;


import com.noah.gaml.yaml.Yaml;

import java.io.*;

public class Gaml {

    public static void main(String[] args) throws IOException {
        Gaml gaml = new Gaml();
        gaml.init();
    }

    public void init() throws IOException {
        InputStream stream = Gaml.class.getClassLoader().getResourceAsStream("test-yaml.yml");
        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line + "\n");
        }

        String input = builder.toString();

        Yaml yaml = Yaml.read(input);
        yaml.print(System.out);

        System.out.println("fox.proud.copy = " + yaml.getSection("fox").getSection("proud").getBoolean("copy"));

    }


}
