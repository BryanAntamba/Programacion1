package proyecto;
//Bibliotecas de la GUI
import javax.swing.*;
import java.sql.*;
//La clase llamada "InicioSesion"
public class InicioSesion {
    //Metodo llamado "iniciarSesion"
    public void iniciarSesion()  {
        //Inicia un bucle infinito que solo se detendrá cuando el usuario ingrese un nombre de usuario válido o cancele la operación.
        while (true) {
            // Muestra un cuadro de diálogo para que el usuario ingrese su nombre de usuario.
            //JOptionPane.showInputDialog() crea un cuadro de entrada que permite al usuario introducir texto.
            String Nombre = JOptionPane.showInputDialog("Ingrese su Nombre de Usuario:");
            //Si el valor de Nombre es null, significa que el usuario presionó el botón "Cancelar" del cuadro de diálogo.
            if (Nombre == null) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión cancelado.", 
                        "Cancelación de Sesión", JOptionPane.INFORMATION_MESSAGE);
                        //Utilizo del break para salir del bucle while y finalizar el proceso.
                        // Si el usuario cancela, termina el programa
                break; 
            }
            //Nombre.trim() elimina los espacios al principio y al final del texto
            //(isEmpty()), muestra un mensaje de error indicando que el nombre de usuario no puede estar vacío.
            if (Nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar su nombre de usuario.", 
                        "Error de Sesión", JOptionPane.ERROR_MESSAGE);
                        //Utilizacion de continue para volver al principio del bucle y pedir al usuario que ingrese el nombre nuevamente.
                continue;
            }
            //Verifica si el nombre ingresado es válido en la función llamada esNombreValido(Nombre)
            //i el nombre no es válido (es decir, si esNombreValido(Nombre) devuelve false).
            if (!esNombreValido(Nombre)) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario solo debe contener letras.", 
                        "Error de Validación", JOptionPane.ERROR_MESSAGE);
                        //Utilizacion de continue para volver a pedir el nombre de usuario.
                continue; 
            }

            // Crea un campo de texto (JPasswordField) donde el usuario puede ingresar su contraseña.
            JPasswordField contraseñaField = new JPasswordField();
            //Muestra un cuadro de diálogo con el campo de contraseña (contraseñaField) 
            //y un botón de confirmación "OK" y cancelación "Cancel".
            int option = JOptionPane.showConfirmDialog(null, contraseñaField, "Ingrese su Contraseña:",
                    JOptionPane.OK_CANCEL_OPTION);

            if (option != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión cancelado.",
                 "Cancelación de Sesión", JOptionPane.INFORMATION_MESSAGE);
                break; // Si el usuario cancela, termina el programa
            }

            String contraseña = new String(contraseñaField.getPassword());
            //contraseña.trim() elimina los espacios al principio y al final de la contraseña ingresada.
            //Si la contraseña es vacía después de recortar los espacios (isEmpty()), muestra un mensaje de error.
            if (contraseña.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña.", 
                        "Error de Sesión", JOptionPane.ERROR_MESSAGE);
                        //continue para volver al principio del bucle y pedir al usuario que ingrese la contraseña nuevamente.
                continue;
            }

            // Verifica si las credenciales (nombre de usuario y contraseña) son correctas
            if (validarCredenciales(Nombre, contraseña)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.", 
                        "Ingreso Realizado", JOptionPane.INFORMATION_MESSAGE);
                        // Credenciales correctas, termina el programa
                break; 
            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos. Ingrese sus credenciales correctamente.", 
                        "Error de Sesión", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Esta función se encarga de validar si un nombre de usuario es válido.
    public static boolean esNombreValido(String nombre) {
        //toCharArray convierte la cadena de texto nombre en un arreglo de caracteres "char[]"
        //Utilizacion de un bucle for para recorrer cada uno de los caracteres del nombr
        for (char c : nombre.toCharArray()) {
            //Para cada carácter, verifica si es una letra (Character.isLetter(c)) 
            //o un espacio en blanco (Character.isWhitespace(c)).
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                // Si hay algún carácter no permitido, retorna falso
                return false;
            }
        }
        // Todos los caracteres son válidos
        return true;
    }

    // Función para validar credenciales usando la base de datos
    public static boolean validarCredenciales(String nombre, String contraseña) {
        //Esta variable representará la conexión a la base de datos.
        Connection conn = null;
        //Esta variable representará la consulta SQL preparada que se ejecutará en la base de datos.
        PreparedStatement stmt = null;
        //Esta variable almacenará el resultado de la consulta SQL. 
        ResultSet rs = null;

        try {
            // Establecer conexión con la base de datos
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Juegos", "root", "Mantismarina2");

            // Consulta preparada para evitar inyección SQL
            String sql = "SELECT * FROM Registro WHERE nombre = ? AND contraseña = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, contraseña);
            //Ejecuta la consulta SQL utilizando executeQuery(), que devuelve un ResultSet que contiene las filas.
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
