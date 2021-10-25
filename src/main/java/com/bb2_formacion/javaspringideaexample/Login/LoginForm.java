package com.bb2_formacion.javaspringideaexample.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{
    private JLabel Header;
    private JTextField textUsuario;
    private JLabel nombreUsuario;
    private JPanel inicioSesion;
    private JLabel Contrasena;
    private JPasswordField passwordField;
    private JButton buttonInicio;
    private JButton buttonCrear;
    private ImageIcon IconoCrear;
    private ImageIcon IconoAcceder;

    public LoginForm(String titulo){
        super(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(inicioSesion);
        this.pack();

        //Listener de Boton Crear
        buttonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre_Usuario = textUsuario.getText();
                String contrasena_Usuario = new String(passwordField.getPassword());
                String query = "INSERT INTO tabla_usuarios(nombreusuario, contrasena) VALUES ('"+nombre_Usuario+"', '" + contrasena_Usuario + "');";
                crearUsuario();
            }
        });

        //Listener de Boton Iniciar Sesion
        buttonInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre_Usuario = textUsuario.getText();
                String contrasena_Usuario = new String(passwordField.getPassword());
                String query = "SELECT FROM nombreusuario tabla_usuarios WHERE nombreusuario='"+nombre_Usuario+"' AND contrasena='" + contrasena_Usuario +"'";
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new LoginForm("Inicio de Sesión");
        frame.setVisible(true);
    }

    public void run() {
        JFrame frame = new LoginForm("Inicio de Sesión");
        frame.setVisible(true);
    }

    public String crearUsuario(){
        String respuesta = "Sin respuesta...";
        //LLamar al DAO
        return respuesta;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}