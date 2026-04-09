package com.practice;
import java.util.Stack;

/**
 * 🧠 The SIMPLE idea (Stack)
 * Think of a stack like a pile of plates:
 *
 * When you see an opening bracket, put it on the stack
 * When you see a closing bracket:
 * The stack must NOT be empty
 * The top of the stack must be the matching opening bracket
 *
 * If it matches → remove it
 * If not → ❌ invalid
 * At the end:
 * Stack must be empty → ✅ valid
 * Otherwise → ❌ invalid
 */
public class ParenthesisExample
{
    public static boolean isProperlyNested(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            // opening brackets
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // closing brackets
            else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // valid only if stack is empty
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isProperlyNested("{[()()]}")); // true
        System.out.println(isProperlyNested("([)]"));     // false
    }
}
