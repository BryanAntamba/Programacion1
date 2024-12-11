package proyecto;
// Define que esta clase pertenece al paquete "proyecto".

import javax.swing.*;
// Importa las clases de Swing para crear interfaces gráficas, como JOptionPane y JFrame.

import java.sql.*;
// Importa las clases necesarias para trabajar con bases de datos en Java.

import java.text.SimpleDateFormat;
// Importa la clase para formatear y analizar fechas.

public class Registro {
    // Define la clase pública "Registro".

    // Atributos privados de la clase
    // Identificador único del registro.
    private int id;
    // Nombre del usuario.
    private String nombre;
    // Fecha de nacimiento del usuario.
    private Date fechaNacimiento;
    // Número de teléfono del usuario.
    private String telefono;
    // Correo electrónico del usuario.
    private String correo;
    // Contraseña del usuario.
    private String contraseña;

    // Constructor que inicializa los atributos de la clase.
    public Registro(int id, String nombre, Date fechaNacimiento, String telefono, String correo, String contraseña) {
        // Instanciamos los valores de la clase Registro
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    // Uso del métodos "getter" el cual permiten acceder a los valores de los
    // atributos privados.
    public int getID() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    // Método estático que permite realizar el registro del usuario. Toma un objeto
    // JFramecomo argumento (una ventana).
    public static Registro realizarRegistro(JFrame ventana) {
        // Mensaje a mostrar si el usuario cancela el registro.
        String cancelMessage = "El registro ha sido cancelado.";

        // Declaracion de una variable nombre que almacenará el nombre ingresado por el
        // usuario
        String nombre;
        // Inicia un bucle infinito que continuará hasta que el usuario ingrese un
        // nombre válido.
        while (true) {
            // Muestra un cuadro de entrada donde el usuario puede escribir su nombre.
            // El valor ingresado se guarda en la variable nombre.
            nombre = JOptionPane.showInputDialog(ventana, "Ingrese un Nombre de Usuario:");
            // Si el usuario cancela el cuadro de entrada, muestra un mensaje de cancelación
            // y devuelve null.
            if (nombre == null) {
                JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
            // Si el nombre ingresado está vacío, muestra un mensaje de error y vuelve a
            // pedir el nombre con el "continue".
            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "Debe ingresar un Nombre de Usuario.", "Error de registro",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }
            // Si el nombre contiene números, muestra un mensaje de error y vuelve a pedir
            // el nombre.
            if (!esNombreValido(nombre)) {
                JOptionPane.showMessageDialog(ventana, "El nombre de usuario no puede contener números.",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            // Si el nombre es válido, el bucle se interrumpe, y el proceso de registro
            // continúa.
            break;
        }

        // Solicitar y validar la fecha de nacimiento.
        //// Declaracion de una variable que almacenará la fecha de nacimiento ingresado
        // por el usuario
       // Solicitar y validar la fecha de nacimiento.
String fechaNacimientoStr;
// Esta variable se utilizará para almacenar la fecha de nacimiento del usuario.
Date fechaNacimiento = null;
// Inicia un bucle infinito que continuará hasta que se ingrese una fecha válida.
while (true) {
    // Muestra un cuadro de diálogo de entrada en la ventana ventana
    fechaNacimientoStr = JOptionPane.showInputDialog(ventana, "Ingrese su Fecha de Nacimiento (DDMMYYYY):");
    // Si el usuario cancela el cuadro de entrada, la función retorna null, lo que indica que el proceso de registro ha sido cancelado.
    if (fechaNacimientoStr == null)
        return null;
    
    // Verifica si la cadena ingresada cumple con el formato requerido (solo números y 8 caracteres).
    if (fechaNacimientoStr.matches("\\d{8}")) {
        try {
            // Extraer día, mes y año de la cadena ingresada.
            String dia = fechaNacimientoStr.substring(0, 2);
            String mes = fechaNacimientoStr.substring(2, 4);
            String anio = fechaNacimientoStr.substring(4, 8);
            
            // Crear una cadena con formato "DD/MM/YYYY" para facilitar la conversión.
            String fechaConFormato = dia + "/" + mes + "/" + anio;

            // Crear un objeto "SimpleDateFormat" con el formato "dd/MM/yyyy".
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); // Para evitar fechas inválidas como 31/02/2023.
            
            // Convertir la fecha a un objeto "java.util.Date".
            java.util.Date utilDate = sdf.parse(fechaConFormato);
            
            // Convertir la fecha util a un objeto SQL Date.
            fechaNacimiento = new Date(utilDate.getTime());
            
            // Si todo es exitoso, el bucle se interrumpe con break.
            break;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "Error al Ingresar la fecha. Inténtelo nuevamente.",
                    "Error de registro", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(ventana, "Por favor, ingrese una fecha válida (DDMMYYYY).",
                "Error de registro", JOptionPane.ERROR_MESSAGE);
    }
}

        // Declaracion de una variable llamada telefono de tipo String
        String telefono;
        // Inicio de un bucle while que continuará ejecutándose hasta que el número de
        // teléfono ingresado sea válido.
        while (true) {
            // Muestra un cuadro de diálogo donde se solicita al usuario que ingrese su
            // número de teléfono.
            telefono = JOptionPane.showInputDialog(ventana, "Ingrese su número de teléfono:");
            // Si el usuario cancela la entrada, muestra un mensaje indicando que el
            // registro ha sido cancelado y retorna null,
            // indicando que el proceso de registro se detienga.
            if (telefono == null) {
                JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
            // Si el número de teléfono ingresado tiene espacios al principio o al final, se
            // elimina usando "trim()""
            // o si su longitud no es exactamente 10 caracteres
            if (telefono.trim().isEmpty() || telefono.length() != 10) {
                JOptionPane.showMessageDialog(ventana, "Debe ingresar 10 dígitos. Intente nuevamente.",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
                // Si el número tiene 10 dígitos, se entra en un bloque donde se verifica si
                // todos los caracteres de la cadena telefono son dígitos.
            } else {
                boolean valido = true;
                for (int numerico = 0; numerico < telefono.length(); numerico++) {
                    if (!Character.isDigit(telefono.charAt(numerico))) {
                        valido = false;
                        break;
                    }
                }
                // Si la variable valido sigue siendo true, se sale del bucle con break.
                // Si no es válido, se muestra un mensaje de error
                if (valido) {
                    // Si el número es válido, sale del bucle
                    break;
                } else {
                    JOptionPane.showMessageDialog(ventana, "El número debe contener solo dígitos. Intente nuevamente.",
                            "Error de registro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        // Solicitar y validar el correo electrónico.
        String correo;
        // Bucle infinito para solicitar el correo hasta que sea válido o se cancele la
        // acción
        while (true) {
            // Mostrar un cuadro de entrada para que el usuario ingrese su correo
            // electrónico
            correo = JOptionPane.showInputDialog(ventana, "Ingrese su Correo Electrónico:");

            // Verificar si el usuario ha cancelado la entrada
            if (correo == null) {
                // Si se cancela, mostrar un mensaje y salir del método
                JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                return null;
            }

            // Verificar si el correo es válido usando una función de validación
            if (!esCorreoValido(correo)) {
                // Si el correo no es válido, mostrar un mensaje de error y continuar
                // solicitando el correo
                JOptionPane.showMessageDialog(ventana, "Ingrese su correo correctamente.", "Error de registro",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Si el correo es válido, salir del bucle
                break; // El correo es válido
            }
        }

        // Solicitar la contraseña.
        JPasswordField contraseñaField = new JPasswordField();
        // Mostrar un cuadro de diálogo para ingresar la contraseña
        int option = JOptionPane.showConfirmDialog(ventana, contraseñaField, "Ingrese una Contraseña:",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Si el usuario acepta la contraseña
        if (option == JOptionPane.OK_OPTION) {
            // Obtener la contraseña ingresada en forma de un array de caracteres
            char[] contraseña = contraseñaField.getPassword();
            // Verificar si la contraseña está vacía
            if (contraseña.length == 0) {
                // Si está vacía, mostrar un mensaje de error y salir del método
                JOptionPane.showMessageDialog(ventana, "Debe ingresar una contraseña.", "Error de registro",
                        JOptionPane.ERROR_MESSAGE);
                return null;
            }
            // Convertir el array de caracteres a un String
            String contraseñaString = new String(contraseña);

            // Crear un nuevo objeto de registro con los datos ingresados
            Registro nuevoRegistro = new Registro(0, nombre, fechaNacimiento, telefono, correo, contraseñaString);

            // Intentar insertar el registro en la base de datos
            if (insertarEnBaseDeDatos(nuevoRegistro)) {
                // Si la inserción es exitosa, mostrar un mensaje de éxito y devolver el
                // registro
                JOptionPane.showMessageDialog(ventana, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return nuevoRegistro;
            } else {
                // Si la inserción falla, mostrar un mensaje de error y devolver null
                JOptionPane.showMessageDialog(ventana, "Error al guardar en la base de datos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } else {
            // Si el usuario cancela, mostrar un mensaje y devolver null
            JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                    JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }

    // Método para validar que el nombre no contenga números
    public static boolean esNombreValido(String nombre) {
        // Recorrer cada carácter del nombre
        for (char c : nombre.toCharArray()) {
            // Si algún carácter es un dígito, devolver false
            if (Character.isDigit(c)) {
                return false;
            }
        }
        // Si no se encontró ningún dígito, devolver true
        return true;
    }

    // Método para validar el correo sin expresión regular compleja
    public static boolean esCorreoValido(String correo) {
        // Verificar si el correo es nulo o está vacío
        if (correo == null || correo.isEmpty()) {
            return false;
        }
        // Buscar la posición del símbolo '@' y el último punto '.'
        int atIndex = correo.indexOf('@');
        int dotIndex = correo.lastIndexOf('.');
        // Verificar si la estructura del correo es válida
        return atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < correo.length() - 1;
    }

    // Método para insertar un registro en la base de datos.
    private static boolean insertarEnBaseDeDatos(Registro registro) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Conectar a la base de datos MySQL
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Juegos", "root", "Mantismarina2");
            // Definir la consulta SQL para insertar el registro
            String sql = "INSERT INTO Registro (nombre, fechaNacimiento, telefono, correo, contraseña) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            // Asignar los valores del registro a los parámetros de la consulta
            stmt.setString(1, registro.getNombre());
            stmt.setDate(2, registro.getFechaNacimiento());
            stmt.setString(3, registro.getTelefono());
            stmt.setString(4, registro.getCorreo());
            stmt.setString(5, registro.getContraseña());

            // Ejecutar la actualización en la base de datos y verificar si se insertaron
            // filas
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            // Si ocurre un error, imprimir el stack trace
            e.printStackTrace();
            return false;
        } finally {
            try {
                // Cerrar los recursos (statement y conexión) si no son nulos
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                // Si ocurre un error al cerrar los recursos, imprimir el stack trace
                e.printStackTrace();
            }
        }
    }
}