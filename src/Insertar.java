import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insertar {
    private JTextField nombre;
    private JTextField cedula;
    private JTextField nota1;
    private JTextField nota2;
    private JButton enviarButton;
    private JLabel confirmacion;
    public JPanel insertaDatos;
    private JButton buscar;

    public Insertar(JFrame frame2) {
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*Guardar en una variable lo digitado por el usuario*/
                String cedulaNueva = cedula.getText();
                String nombreNuevo = nombre.getText();
                Float nota1Nuevo = Float.parseFloat(nota1.getText());
                Float nota2Nuevo = Float.parseFloat(nota2.getText());
                /*Linea de conexión*/
                String url = "jdbc:mysql://localhost:3306/clasePoo";;
                String user = "root";
                String contrasenia = "123456";
                /*¿Qué sucede si se realiza adecuadamente la conexión?*/
                try(Connection connection= DriverManager.getConnection(url, user, contrasenia)){
                    System.out.println("Conexion exitosa");
                    String query = "insert into estudiantes(cedula, nombre, b1, b2) values (?,?,?,?)";
                    PreparedStatement insercion = connection.prepareStatement(query);
                    insercion.setString(1, cedulaNueva);
                    insercion.setString(2, nombreNuevo);
                    insercion.setFloat(3, nota1Nuevo);
                    insercion.setFloat(4, nota2Nuevo);
                    insercion.executeUpdate();
                    confirmacion.setText("Datos insertados en mysql correctamente");
                }
                /*¿Qué sucede si la conexión presenta un error?*/
                catch (SQLException exception){
                    System.out.println("No se realizo la conexión, verifique si existe algun error");
                    System.out.println(exception.getMessage());
                }
            }
        });
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Buscar por numero de cedula a un alumno");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane( new Buscar(frame).buscarAlu);
                frame.pack();
                frame.setSize(500, 400);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                frame2.setVisible(false);
            }
        });
    }
}
