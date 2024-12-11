package proyecto;

import javax.swing.*;
import java.sql.*;

public class InicioSesion {

    public void iniciarSesion()  {
        while (true) {
            // Solicitar el nombre de usuario
            String Nombre = JOptionPane.showInputDialog("Ingrese su Nombre de Usuario:");

            if (Nombre == null) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión cancelado.", 
                        "Cancelación de Sesión", JOptionPane.INFORMATION_MESSAGE);
                break; // Si el usuario cancela, termina el programa
            }

            if (Nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar su nombre de usuario.", 
                        "Error de Sesión", JOptionPane.ERROR_MESSAGE);
                continue; // Solicita nuevamente el ingreso
            }

            if (!esNombreValido(Nombre)) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario solo debe contener letras.", 
                        "Error de Validación", JOptionPane.ERROR_MESSAGE);
                continue; // Solicita nuevamente el ingreso
            }

            // Solicitar la contraseña
            JPasswordField contraseñaField = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(null, contraseñaField, "Ingrese su Contraseña:",
                    JOptionPane.OK_CANCEL_OPTION);

            if (option != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión cancelado.", 
                        "Cancelación de Sesión", JOptionPane.INFORMATION_MESSAGE);
                break; // Si el usuario cancela, termina el programa
            }

            String contraseña = new String(contraseñaField.getPassword());
            if (contraseña.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña.", 
                        "Error de Sesión", JOptionPane.ERROR_MESSAGE);
                continue; // Solicita nuevamente el ingreso
            }

            // Validar las credenciales
            if (validarCredenciales(Nombre, contraseña)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.", 
                        "Ingreso Realizado", JOptionPane.INFORMATION_MESSAGE);
                break; // Credenciales correctas, termina el programa
            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos. Ingrese sus credenciales correctamente.", 
                        "Error de Sesión", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Función para verificar si el nombre es válido
    public static boolean esNombreValido(String nombre) {
        for (char c : nombre.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false; // Si hay algún carácter no permitido, retorna falso
            }
        }
        return true; // Todos los caracteres son válidos
    }

    // Función para validar credenciales usando la base de datos
    public static boolean validarCredenciales(String nombre, String contraseña) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establecer conexión con la base de datos
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Juegos", "root", "Mantismarina2");

            // Consulta preparada para evitar inyección SQL
            String sql = "SELECT * FROM Registro WHERE nombre = ? AND contraseña = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, contraseña);

            rs = stmt.executeQuery();

            // Validar resultados
            return rs.next(); // Retorna true si se encuentra un resultado
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos:");
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar recursos en el orden adecuado
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos:");
                e.printStackTrace();
            }
        }
    }
}
