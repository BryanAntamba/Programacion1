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
        //Instanciamos los valores de la clase Registro
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    // Uso del métodos "getter" el cual permiten acceder a los valores de los atributos privados.
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

    //Método estático que permite realizar el registro del usuario. Toma un objeto JFramecomo argumento (una ventana).
    public static Registro realizarRegistro(JFrame ventana) {
        // Mensaje a mostrar si el usuario cancela el registro.
        String cancelMessage = "El registro ha sido cancelado."; 

        // Solicitar y validar el nombre del usuario.
        //String nombre declara una variable local al método realizar Registro que 
        //se usa exclusivamente para almacenar el nombre ingresado por el usuario durante el proceso de registro.
        String nombre;
        //Bucle que se repite hasta que se ingrese un nombre válido.
        while (true) {
            // Muestra un cuadro de diálogo para ingresar el nombre.
            // usando un cuadro de entrada de texto ( JOptionPane.showInputDialog).
            nombre = JOptionPane.showInputDialog(ventana, "Ingrese un Nombre de Usuario:"); 
            //Si el usuario cancela, se muestra un mensaje y termina el registro.
            if (nombre == null) { 
                JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                return null; 
                // Termina el registro y devuelve null.
            }
            //Si el campo del nombre de usuario está vacío, muestra un error y solicita nuevamente.
            if (nombre.trim().isEmpty()) { 
                // Verifica si el nombre está vacío.
                JOptionPane.showMessageDialog(ventana, "Debe ingresar un Nombre de Usuario.", "Error de registro",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Sale del bucle si el nombre es válido.
                break; 
            }
        }

        // Solicitar y validar la fecha de nacimiento.
        String fechaNacimientoStr;
        //"Date" para almacenar la fecha validada como un objeto de tipo DateJava.
        Date fechaNacimiento = null;
        //Bucle que se repite hasta que se ingresa una fecha válida.
        while (true) {
            //Se le pide al usuario que ingrese su fecha de nacimiento en el formato DD/MM/AAAA
            // usando un cuadro de entrada de texto ( JOptionPane.showInputDialog).
            fechaNacimientoStr = JOptionPane.showInputDialog(ventana, "Ingrese su Fecha de Nacimiento (DD/MM/AAAA):");
            //Si el usuario cancela o cierra el cuadro de entrada 
            //(es decir, fechaNacimientoStres null), el método retorna null y termina el proceso.
            if (fechaNacimientoStr == null) return null; 
            // Se valida que la cadena ingresada cumpla con el formato de fecha DD/MM/AAAA. 
            //Esto se hace mediante una expresión regular ( \\d{2}/\\d{2}/\\d{4}), 
            //donde \\d representa un dígito numérico y {2}, {4} especifican el número de dígitos esperados.
            if (fechaNacimientoStr.matches("\\d{2}/\\d{2}/\\d{4}")) { 
                // Valida el formato de la fecha.
                try {
                    //Si la cadena tiene el formato correcto, se intenta convertir la fecha de tipo Stringa 
                    //un objeto de tipo Dateutilizando SimpleDateFormat. Se especifica el formato dd/MM/yyyy.
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    //Si la conversión es exitosa, se crea un nuevo objeto java.sql.Dateutilizando 
                    //utilDate.getTime()y se asigna a la variable fechaNacimiento.break
                    java.util.Date utilDate = sdf.parse(fechaNacimientoStr); 
                    // Convierte el String a un objeto Date.
                    // Convierte la fecha de java.util.Date a java.sql.Date.
                    fechaNacimiento = new Date(utilDate.getTime()); 
                    break;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(ventana, "Error al Ingresar la fecha. Inténtelo nuevamente.",
                            "Error de registro", JOptionPane.ERROR_MESSAGE);
                }
                //Si la fecha ingresada no coincide con el formato DD/MM/AAAA, 
                //se muestra un mensaje de error y el bucle sigue pidiendo la fecha hasta que el usuario ingrese una válida.
            } else {
                JOptionPane.showMessageDialog(ventana, "Por favor, ingrese una fecha válida (DD/MM/AAAA).",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Solicitar y validar el teléfono.
        String telefono;
        while (true) {
            telefono = JOptionPane.showInputDialog(ventana, "Ingrese su número de teléfono:");
            if (telefono == null) { 
                // Cancela si el usuario no ingresa nada.
                JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
            if (telefono.trim().isEmpty() || !telefono.matches("\\d{10}")) { 
                // Verifica si el teléfono está vacío o no tiene 10 dígitos.
                JOptionPane.showMessageDialog(ventana, "Debe ingresar 10 dígitos. Intente nuevamente.",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        }

        // Solicitar y validar el correo.
        String correo;
        while (true) {
            correo = JOptionPane.showInputDialog(ventana, "Ingrese su Correo Electrónico:");
            if (correo == null) {
                JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
            //El correo contenga una combinación de caracteres alfanuméricos, guiones y puntos antes del símbolo @
            //Después del @, haya otro conjunto de caracteres alfanuméricos, guiones o puntos.
            //Finalmente, el correo debe tener una extensión válida (de 2 a 6 letras).
            if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) { 
                // Valida el formato del correo.
                JOptionPane.showMessageDialog(ventana, "Ingrese su correo correctamente.",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        }

        // Solicitar la contraseña.
        //Se crea un campo de texto para que el usuario ingrese la contraseña.
        JPasswordField contraseñaField = new JPasswordField(); 
        //Se muestra un cuadro de diálogo (JOptionPane.showConfirmDialog) donde se le pide al usuario que ingrese su contraseña.
        //JOptionPane.OK_CANCEL_OPTION: El cuadro de diálogo tendrá botones de "OK" y "Cancelar"
        //JOptionPane.PLAIN_MESSAGE: No se muestra un icono en el cuadro de diálogo, solo el texto.

        //Si el usuario hace clic en "OK", se devuelve JOptionPane.OK_OPTION.
        //Si hace clic en "Cancelar", se devuelve JOptionPane.CANCEL_OPTION.
        int option = JOptionPane.showConfirmDialog(ventana,contraseñaField,"Ingrese una Contraseña:",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) { 
            //Se obtiene la contraseña ingresada en el JPasswordField utilizando el método getPassword(), 
            //que devuelve un arreglo de caracteres (char[])
            char[] contraseña = contraseñaField.getPassword(); 
            //Si el usuario no ingresa ninguna contraseña (es decir, el arreglo de caracteres tiene longitud 0),
            // se muestra un mensaje de error indicando que la contraseña es obligatoria.
            if (contraseña.length == 0) {
                JOptionPane.showMessageDialog(ventana, "Debe ingresar una contraseña.",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            // Convierte la contraseña a String.
            String contraseñaString = new String(contraseña); 

            // Crear un nuevo objeto "Registro".
            Registro nuevoRegistro = new Registro(0, nombre, fechaNacimiento, telefono, correo, contraseñaString);

            // Intenta guardar los datos en la base de datos.
            if (insertarEnBaseDeDatos(nuevoRegistro)) {
                JOptionPane.showMessageDialog(ventana, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                //// Devuelve el registro creado.
                return nuevoRegistro; 
            } else {
                JOptionPane.showMessageDialog(ventana, "Error al guardar en la base de datos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } else { 
            // Si el usuario cancela al ingresar la contraseña.
            JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                    JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }

    // Método para insertar un registro en la base de datos.
    private static boolean insertarEnBaseDeDatos(Registro registro) {
        // Conexión a la base de datos.
        Connection conn = null; 
        // Declaración preparada para ejecutar la consulta.
        PreparedStatement stmt = null; 

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Juegos", "root", "Mantismarina2");
            // Establece la conexión con la base de datos.

            String sql = "INSERT INTO Registro (nombre, fechaNacimiento, telefono, correo, contraseña) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql); 
            // Prepara la consulta SQL.
            stmt.setString(1, registro.getNombre());
            stmt.setDate(2, registro.getFechaNacimiento());
            stmt.setString(3, registro.getTelefono());
            stmt.setString(4, registro.getCorreo());
            stmt.setString(5, registro.getContraseña());

            int filasAfectadas = stmt.executeUpdate(); 
            // Ejecuta la consulta y obtiene el número de filas afectadas.
            return filasAfectadas > 0; 
            // Devuelve true si se insertaron registros correctamente.
        } catch (SQLException e) {
            e.printStackTrace(); 
            // Imprime el error en la consola.
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close(); 
                // Cierra el objeto PreparedStatement.
                if (conn != null) conn.close(); 
                // Cierra la conexión.
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
