import ExpressionTree.Notation;
import ExpressionTree.Node;
import ExpressionTree.ExpresionTree;

public class Solver {
    public static double solve(String infixOperation) {
        String posfixNotation = Notation.infixToPostfix(infixOperation);

        Node tree = ExpresionTree.postfixToExpressionTree(posfixNotation);

        ExpresionTree.toJson(tree);

        return ExpresionTree.solve(tree);


    }
}
