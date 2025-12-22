package com.company.utils

class CalculatorUtils {

    static int add(int a, int b) {
        return a + b
    }

    static int sub(int a, int b) {
        return a - b
    }

    static int mul(int a, int b) {
        return a * b
    }

    static Integer div(int a, int b) {
        if (b == 0) {
            return null       
        }
        return a / b           
    }
}
