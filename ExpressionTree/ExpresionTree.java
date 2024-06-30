package ExpressionTree;

import java.util.Stack;

public class ExpresionTree {
    public static boolean isOperator(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^') {
            return true;
        }
        return false;
    }

    public static Node postfixToExpressionTree(String postfix) {
        Stack<Node> st = new Stack<Node>();
        Node t1, t2, temp;

        for (int i = 0; i < postfix.length(); i++) {
            if (!isOperator(postfix.charAt(i))) {
                temp = new Node(i, postfix.charAt(i));
                st.push(temp);
            } else {
                temp = new Node(i, postfix.charAt(i));

                t1 = st.pop();
                t2 = st.pop();

                temp.left = t2;
                temp.right = t1;

                st.push(temp);
            }

        }
        temp = st.pop();
        return temp;
    }

    public static void inorder(Node root) {
        if (root == null)
            return;

        inorder(root.left);
        System.out.print(root.key);
        inorder(root.right);
    }

    public static double solve(Node node) {
        if (node.isNumber())
            return Integer.parseInt(String.valueOf(node.key));

        switch (node.key) {
            case '+':
                return solve(node.left) + solve(node.right);
            case '-':
                return solve(node.left) - solve(node.right);
            case '*':
                return solve(node.left) * solve(node.right);
            default: // -> /
                return solve(node.left) / solve(node.right);
        }
    }

    public static String toJson(Node root) {
        return "{\"root\": " + root.toJson() + "}";
    }
}
