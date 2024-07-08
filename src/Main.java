import javax.swing.*;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*Visualización de la ventana emergente*/
        JFrame frame = new JFrame("Inserción de datos en mysql");
        frame.setContentPane(new Insertar(frame).insertaDatos);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}