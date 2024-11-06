package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringTokenizer tokenizer = new StringTokenizer(signatureString, "(");
        StringTokenizer tokenizerStart = new StringTokenizer(tokenizer.nextToken().trim(), " ");

        String accessModifier = null;
        String returnType = "";
        String methodName = "";

        if (tokenizerStart.countTokens() == 3) {
            accessModifier = tokenizerStart.nextToken();
            returnType = tokenizerStart.nextToken();
            methodName = tokenizerStart.nextToken();
        } else if (tokenizerStart.countTokens() == 2) {
            returnType = tokenizerStart.nextToken();
            methodName = tokenizerStart.nextToken();
        } else if (tokenizerStart.countTokens() == 1) {
            methodName = tokenizerStart.nextToken();
        }

        String argumentsString = tokenizer.nextToken().trim();
        if (argumentsString.endsWith(")")) {
            argumentsString = argumentsString.substring(0, argumentsString.length() - 1);  // Remove closing parenthesis
        }

        StringTokenizer tokenizerEnd = new StringTokenizer(argumentsString, ",");

        List<MethodSignature.Argument> arguments = new ArrayList<>();

        while (tokenizerEnd.hasMoreTokens()) {
            String token = tokenizerEnd.nextToken().trim();
            StringTokenizer tempToken = new StringTokenizer(token, " ");

            if (tempToken.countTokens() == 2) {
                String type = tempToken.nextToken();
                String name = tempToken.nextToken();
                arguments.add(new MethodSignature.Argument(type, name));
            }
        }

        if (argumentsString.isEmpty()) {
            arguments = new ArrayList<>();
        }

        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setReturnType(returnType);
        methodSignature.setMethodName(methodName);
        methodSignature.setAccessModifier(accessModifier);

        return methodSignature;
    }
}
