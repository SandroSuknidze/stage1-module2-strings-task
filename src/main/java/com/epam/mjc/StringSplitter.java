package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result = new ArrayList<>();
        if (source == null || delimiters == null || delimiters.isEmpty()) {
            return result;
        }

        StringBuilder tokenBuffer = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char currentChar = source.charAt(i);
            boolean isDelimiter = false;

            for (String delimiter : delimiters) {
                if (delimiter.equals(String.valueOf(currentChar))) {
                    isDelimiter = true;
                    break;
                }
            }

            if (isDelimiter) {
                if (tokenBuffer.length() > 0) {
                    result.add(tokenBuffer.toString());
                    tokenBuffer.setLength(0);
                }
            } else {
                tokenBuffer.append(currentChar);
            }
        }

        if (tokenBuffer.length() > 0) {
            result.add(tokenBuffer.toString());
        }

        return result;
    }
}
