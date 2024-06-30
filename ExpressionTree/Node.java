/**
 * Node
 */
package ExpressionTree;

public class Node {
    int id;
    char key;
    Node left, right;
    int height;

    public boolean isOperand() {
        if (key == '+' || key == '-' || key == '/' || key == '*')
            return true;
        return false;
    }

    public boolean isNumber() {
        return !isOperand();
    }

    public Node(int id, char item) {
        this.id = id;
        key = item;
        left = right = null;
    }

    public String toJson() {
        String str = "{\"id\": \"" + id + "\", \"key\": \"" + key + "\"";
        if (left != null || right != null) {
            str += ",";
        }
        if (left != null) {
            str += "\"left\": " + left.toJson() + "";
        }
        if (left != null && right != null) {
            str += ",";
        }

        if (right != null) {

            str += "\"right\": " + right.toJson() + "";
        }
        str += "}";
        return str;
    }
}