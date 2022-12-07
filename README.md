![Open in Codespaces](https://classroom.github.com/assets/open-in-codespaces-abfff4d4e15f9e1bd8274d9a39a0befe03a0632bb0f153d0ec72ff541cedbe34.svg)
# Intro

You are tasked to develop a program that can evaluate mathematical expressions of any length and complexity. Do **NOT** worry about unary operators (-), such as

```
-3 + 5
```

I am leaving it as a bonus (see below). However, remember to replace all SPACES with an empty string ("") before you start working on parsing the input string and evaluating its mathematical value. In other words, your program should not depend on the spaces in the input at all. Some inputs may contain lots of spaces, others may have none.

## Available operators and operands 

The operators that your program should support include `+ - * / ( )`. You are more than welcome to add `log`, `sin`, `^`, etc. for bonus points.

The operands can be any non-negative integers (integers >= 0).

For example, your program should be able to evaluate such expressions as:

- with spaces  
  `591 + 10 * 6 / (442 - 3 * ( 61 + 7 - 8 )) + 4 / 2`

- and without spaces  
  `591+10*6/(442-3*(61+7-8))+4/2`

# High-level overview of the algorithm

1. Read user's input into a `String` and replace all spaces with an empty string.

2. Parse the input according to the "infix to postfix" conversion algorithm using stacks as we did in class (follow the video recording on "Canvas -> Zoom" from October 10th - that's when we went over it).

3. Evaluate the postfix expression using a stack. The last number you pop out from the stack will be your final result.

4. Google, google, and again, google (and youtube it). May google be with you (or duckduckgo/startpage if you prefer privacy).

# Deeper algorithm ideas

## Infix to postfix conversion

Let's say you have the following input: `2 + 3 * ((5 - 8 / 4) + 6) / 9`, and you also created an `ArrayList<String> out` that will store the postfix notation of your input. You also created a `Stack<String> ops` that will store your operators. Let's read your input from left to right, following the rules:

1. Operand -> add it to the `out`. **NOTE**: operands will be multi-digit numbers such as in the expression `234 + 45`. We simplified it to single-digit numbers for the example's sake.
2. Operator that is among `+, -, *, /` -> check what is on the `ops` stack:
   - if `ops` is empty, push the operator to `ops`
   - if `ops.peek()` has `(`, push the operator to `ops`
   - if `ops.peek()` contains an operator that is _greater_ than or _equal_ to the current operator (on the precedence scale according to PEMDAS), **continuously** (in a loop) pop from `ops` stack to `out` _until_ `ops.peek()` has an operator that is `(`, `ops.peek()` has an operator with a smaller precedence value, or `ops` is empty
   - if **neither** of the above cases happened, then just push the operator to the `ops` stack
3. Operator `(` -> push it to the `ops` stack
4. Operator `)` -> do the following two steps:
   - continuously pop from the `ops` stack _until_ you encounter `(`
   - pop `(` from the stack as it's going to be at the top of the stack and you do not want it to be added to `out`

## Evaluation of the postfix notation

Let's  continue working with the `ArrayList<String> out` that you generated from the previous section. In this case, your `out` contains the following **Strings**:

```
["2", "3", "5", "8", "4", "/", "-", "6", "+", "*", "9", "/", "+"]
```

Let's create a `Stack<String> nums` which we will use to keep track of all operands. Read the items from `nums` from left to right and apply the following rules:

1. Operand -> convert to a `double` and add it to `nums`
2. Operator -> follow these steps:
   - `pop` two items from `nums`
   - apply the operator on those two items
   - `push` the result back to `nums`

When you are done processing `out` items, your final calculated result will be the last item in your `nums` that you can pop and return as a value.

# Bonus

1. Your calculator can support unary operations to evaluate `-(3 + 5)` or similar expressions. (+10 points to your midterm quiz or final)

2. Your calculator can support floating-point operands. For example, `3.56 + 5.97 / 4.63` should return the decimal value 4.85 (or the full number will also work). (+10 points to your midterm quiz or final)

3. Your calculator supports all of the above-mentioned bonuses as well as it throws `RuntimeException` with a message when you:
    - try to divide by zero
    - try to enter characters that are not supported by this calculator, and
    - try to evaluate expressions that do not make sense (e.g., 5+++3--8*/+9).
    - Additionally, you can explain to me every line of your code and answer any questions about your code.

If you do all of these bonus options - that is an ultra-kill, so it is worth +50 to your midterm quiz or final.
