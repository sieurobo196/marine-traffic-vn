package com.marine.traffic.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;


public class Resource {
    private final static Logger LOGGER = LoggerFactory.getLogger(Resource.class);
    private static final String COMMENT_REGEX_MULTI_AND_SINGLE_LINE = "/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/";
    private static final String COMMENT_REGEX_SQL = "--.*(?=\\n)";

    public static String readSql(String virtualPath) {
        String filePath = String.format("sql/%s.sql", virtualPath);
        try {
            String output;
            output = new String(IOUtils.toByteArray(Objects.requireNonNull(Resource.class.getClassLoader().getResourceAsStream(filePath))), StandardCharsets.UTF_8);
            output = output.replaceAll(COMMENT_REGEX_MULTI_AND_SINGLE_LINE, "")
                    .replaceAll(COMMENT_REGEX_SQL, "");
            output = Arrays.stream(output.split(";"))
                    .map(String::trim)
                    .collect(Collectors.joining(";\n"));
            return output;
        } catch (Exception e) {
            LOGGER.error(String.format("Failed to read file %s", filePath), e);
            return "";
        }
    }

    public static Properties readProperties(String virtualPath) {
        Properties properties = new Properties();
        try {
            String filePath = String.format("%s.properties", virtualPath);
            properties.load(Resource.class.getClassLoader().getResourceAsStream(filePath));
        } catch (IOException e) {
            LOGGER.error(String.format("Failed to read file %s", virtualPath), e);
        }
        return properties;
    }
    public static String readJson(String virtualPath) {
        String output;
        String filePath = String.format("%s.json", virtualPath);
        try {
            output = new String(IOUtils.toByteArray(Objects.requireNonNull(Resource.class.getClassLoader().getResourceAsStream(filePath))), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error(String.format("Failed to read file %s", filePath), e);
            return null;
        }
        output = Arrays.stream(output.split(";"))
                .map(String::trim)
                .collect(Collectors.joining(";\n"));
        return output;
    }
}
