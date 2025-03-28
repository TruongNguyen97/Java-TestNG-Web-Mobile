package com.automation.framework.core.utils;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    
    public static String toAbsolutePath(String relativePath) {
        if (relativePath == null || relativePath.isEmpty()) {
            throw new IllegalArgumentException("Relative path cannot be null or empty");
        }
        Path path = Paths.get(relativePath).toAbsolutePath().normalize();
        return path.toString();
    }

    public static List<String> findRegexMatches(String body, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(body);
        List<String> results = new ArrayList<>();

        while (matcher.find()) {
            results.add(matcher.group().toString());
        }

        System.out.println("List matchers: " + results);

        return results;
    }
}
