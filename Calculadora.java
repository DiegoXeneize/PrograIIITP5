import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {
    private JTextArea campoCuentas;
    private JPanel panelBotones;
    private JPanel panelPrincipal;

    private boolean solveClick = false;
    private boolean operatorClick = false;
    private boolean numberClick = false;


    public Calculadora() {
        // Configuración del JFrame
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal con margen
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelPrincipal.setBackground(new Color(43, 45, 48));
        add(panelPrincipal, BorderLayout.CENTER);

        // Área de texto para mostrar el contenido
        campoCuentas = new JTextArea(2, 20);
        campoCuentas.setEditable(false);
        campoCuentas.setFont(new Font("Arial", Font.BOLD, 24));
        campoCuentas.setBackground(new Color(183, 229, 116));
        campoCuentas.setLineWrap(true);
        campoCuentas.setWrapStyleWord(true);

        // Envolver JTextArea en JScrollPane
        JScrollPane scrollPane = new JScrollPane(campoCuentas);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelPrincipal.add(scrollPane, BorderLayout.NORTH);

        // Panel para los botones
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 4, 2, 2));
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        String[] botones = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "DEL", "=", "+"
        };

        for (String texto : botones) {
            JButton boton = new JButton(texto);
            boton.addActionListener(new BotonActionListener());
            panelBotones.add(boton);
        }

        setVisible(true);
    }

    private class BotonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (solveClick) campoCuentas.setText("");
            String textoBoton = ((JButton) e.getSource()).getText();

            if (textoBoton.equals("DEL")) {
                solveClick = false;
                numberClick = false;
                operatorClick = false;
                String textoActual = campoCuentas.getText();
                if (!textoActual.isEmpty()) {
                    campoCuentas.setText(textoActual.substring(0, textoActual.length() - 1));
                }
            } else if (textoBoton.equals("=")) {
                solveClick = true;
                numberClick = false;
                operatorClick = false;
                String textoActual = campoCuentas.getText();

                try{

                    campoCuentas.setText(String.valueOf(Solver.solve(textoActual)));
                }catch (Exception exception){
                    campoCuentas.setText("Error");

                }
            } else if (textoBoton.equals("+") || textoBoton.equals("-") || textoBoton.equals("*") || textoBoton.equals("/") ) {
                solveClick = false;
                numberClick = false;

                String textoActual = campoCuentas.getText();

                if (operatorClick){
                    campoCuentas.setText(textoActual.substring(0, textoActual.length() - 1));
                }else{
                    operatorClick = true;
                }
                campoCuentas.append(textoBoton);

            }else {
                String textoActual = campoCuentas.getText();

                solveClick = false;
                operatorClick = false;

                if (numberClick){
                    campoCuentas.setText(textoActual.substring(0, textoActual.length() - 1));
                }else{
                    numberClick = true;
                }
                campoCuentas.append(textoBoton);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculadora();
        });
    }
}
