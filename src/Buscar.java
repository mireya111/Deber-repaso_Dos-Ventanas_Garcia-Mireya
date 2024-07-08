import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Buscar {
    public JPanel buscarAlu;
    private JTextField cedulaBuscar;
    private JButton buscar;
    private JLabel informacion1;
    private JLabel informacion2;
    private JLabel informacion3;
    private JButton atras;
    private JFrame frame1;

    public Buscar(JFrame frame) {
        frame1=frame;
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*Recolección de datos digitados por el usuario*/
                String cedula = cedulaBuscar.getText();
                /*Linea de conexion*/
                String url = "jdbc:mysql://localhost:3306/clasePoo";
                String usuario = "root";
                String contrasenia = "123456";
                /*¿Qué sucede si la conexión se realiza adecuadamente?*/
                try(Connection connection = DriverManager.getConnection(url, usuario, contrasenia)){
                    System.out.println("Conexión exitosa");
                    String query = "select * from estudiantes where cedula = '" + cedula + "'";
                    PreparedStatement resultados = connection.prepareStatement(query);
                    ResultSet resultado = resultados.executeQuery();
                    while(resultado.next()) {
                        informacion1.setText("Nombre:   "+ resultado.getString("nombre"));
                        informacion2.setText("Nota Bimestre uno:   "+resultado.getString("b1"));
                        informacion3.setText("Nota Bimestre dos:   "+resultado.getString("b2"));
                    }
                }
                /*¿Qué sucede si la conexión presenta fallos?*/
                catch (SQLException exception){
                    System.out.println("No se realizo la conexión, verifique si existe algun error");
                    System.out.println(exception.getMessage());
                }
            }
        });
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Inserción de datos en mysql");
                frame.setContentPane(new Insertar(frame).insertaDatos);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(500,400);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                frame1.setVisible(false);
            }
        });
    }
}
