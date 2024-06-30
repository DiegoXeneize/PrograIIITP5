import ExpressionTree.Notation;
import ExpressionTree.Node;
import ExpressionTree.ExpresionTree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Solver {
    public static double solve(String infixOperation) {
        String posfixNotation = Notation.infixToPostfix(infixOperation);

        Node tree = ExpresionTree.postfixToExpressionTree(posfixNotation);

        saveStringToFile(ExpresionTree.toJson(tree));

        return ExpresionTree.solve(tree);

    }

    public static void saveStringToFile(String content) {
        // Obtener el directorio actual
        String currentDir = System.getProperty("user.dir");

        // Crear un objeto File para tree.json en el directorio actual
        File file = new File(currentDir, "ExpressionTree/tree.json");

        // Utilizar FileWriter para escribir en el archivo, sobrescribiendo su contenido
        // si ya existe
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
            System.out.println("El contenido se ha guardado correctamente en tree.json");
        } catch (IOException e) {
            System.err.println("Ocurrió un error al guardar el archivo: " + e.getMessage());
        }
    }
}
