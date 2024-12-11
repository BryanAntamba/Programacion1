package proyecto;
//Importacion de bibliotecas de GUI
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
//Definimos la clase de GUI
public class apartadoPrincipal {
    //Declara el punto de entrada del programa en Java. El método main es donde comienza la ejecución del programa.
    public static void main(String[] args) {
        // Configuración de la ventana
        //---------------------------------------------------------------------------------------------------------
        //Crea una ventana principal usando la clase JFrame y establece su título como "GameStore".
        JFrame ventana = new JFrame("GameStore");
        //Configuracion al operación predeterminada al cerrar la ventana. 
        //En este caso, termina la ejecución del programa cuando la ventana se cierra.
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Establece el tamaño de la ventana en 1000 píxeles de ancho y 700 píxeles de alto.
        ventana.setSize(1000, 700);
        //Configura un diseño (layout) absoluto, permitiendo posicionar componentes en coordenadas específicas dentro de la ventana.
        ventana.setLayout(null); // Layout absoluto

        // Configurar el ícono personalizado
        //---------------------------------------------------------------------------------------------------------
        //Carga una imagen desde la ruta especificada (img/icono.png) que ser el Icono de la aplicacion
        Image icono = Toolkit.getDefaultToolkit().getImage("img/icono.png");
        //Establece la imagen cargada como el ícono de la ventana.
        ventana.setIconImage(icono);

        // Cargar la imagen de fondo
        //// Ajusta la ruta según tu estructura de carpetas
        ImageIcon fondoApp = new ImageIcon("img/principal.jpg"); 

        // Crear un JPanel con un fondo personalizado
        JPanel fondo = new JPanel() {
            @Override
            //El método paintComponent se sobrescribe para permitir la personalización del fondo
            protected void paintComponent(Graphics dimeciones) {
                super.paintComponent(dimeciones);
                //Dibuja la imagen cargada (fondoApp) como fondo del panel.
                //La imagen se redimensiona para ajustarse al ancho (getWidth()) y alto (getHeight()) del panel.
                dimeciones.drawImage(fondoApp.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Establecer el diseño del panel
        fondo.setLayout(null);

        // Crear y configurar la etiqueta principal
        JLabel label = new JLabel("GameStore", SwingConstants.CENTER);
        //Establecemos el texto en la posicion X, Y correcta y le ponemos 300 pixeles de ancho y 30 pixeles de alto
        label.setBounds(345, 40, 300, 40);
        // Texto en color blanco
        label.setForeground(Color.WHITE); 
        // Fuente adaptable y de tipo de letra "Arial" con tamaño del texto de 40 pixeles.
        label.setFont(new Font("Arial", Font.BOLD, 40)); 

        // Crear y configurar el subtítulo
        //El uso de <html> permite dar formato HTML al texto, como saltos de línea con <br>
        //SwingConstants.CENTER alinea el texto horizontalmente al centro del componente.
        JLabel label1 = new JLabel("<html>Bienvenido a GameStore donde podrás encontrar los mejores juegos<br>de acción, terror y aventura.</html>",SwingConstants.CENTER);
        // Fuente adaptable y de tipo de letra "Arial" con tamaño del texto de 20 pixeles.
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        // Texto en color blanco
        label1.setForeground(Color.WHITE);

        // Añadir ambos al panel de fondo
        fondo.add(label);
        fondo.add(label1);

        // Listener para ajustar la posición dinámica al redimensionar
        ventana.addComponentListener(new java.awt.event.ComponentAdapter() {
            //Sobrescribe el método componentResized para especificar qué sucede cuando se redimensiona la ventana
            public void componentResized(java.awt.event.ComponentEvent evt) {
                //Obtiene el ancho actual de la ventana (ventana) y lo guarda en la variable anchoDeVentana.
                int anchoDeVentana = ventana.getWidth();
                //(anchoDeVentana - 300) / 2 centra el componente horizontalmente dentro de la ventana.
                //40 píxeles desde la parte superior. 300 píxeles de ancho y 40 píxeles de alto.
                label.setBounds((anchoDeVentana - 300) / 2, 40, 300, 40);
                // Justo debajo del título
                label1.setBounds((anchoDeVentana - 800) / 2, 250, 800, 60); 
            }
        });
        //Desarrollo de la barra del Menu
        JMenuBar menuBar = new JMenuBar();

        //Crea un menú llamado "Salir" que estará visible en la barra de menús
        JMenu salir = new JMenu("Salir");
        //Crea un elemento de menú llamado "Cerrar Tienda", que estará dentro del menú "Salir"
        JMenuItem cerrarSesion = new JMenuItem("Cerrar Tienda");
        //Agrega un listener al elemento "Cerrar Tienda". Cuando se hace clic en esta opción, el programa se cerrará.
        cerrarSesion.addActionListener(e -> System.exit(0));
        //Añade el menú "Salir" a la barra de menús (menuBar)
        menuBar.add(salir);
        //Añade el elemento "Cerrar Tienda" al menú "Salir"
        salir.add(cerrarSesion);

        //Crea un menú llamado "Categorías", que estará visible en la barra de menús
        JMenu categorias = new JMenu("Categorias");
        //Crea tres elementos de menú dentro de "Categorías", 
        //con los nombres "Acción", "Terror" y "Aventura". Estos representan subcategorías.
        JMenuItem productos1 = new JMenuItem("Acción");
        JMenuItem productos2 = new JMenuItem("Terror");
        JMenuItem productos3 = new JMenuItem("Aventura");
        //Añade los tres elementos de menú ("Acción", "Terror" y "Aventura") al menú "Categorías"
        categorias.add(productos1);
        categorias.add(productos2);
        categorias.add(productos3);

        //Se añade un Listener al elemento de menú productos1 ("Acción") 
        //para que, cuando el usuario haga clic en esta opción, se ejecute el método mostrarJuegos.
        productos1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarJuegos("Acción", ventana);
            }
        });
        //Al hacer clic, se llama al método mostrarJuegos con "Terror"  como categoría y la ventana principal (ventana).
        productos2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarJuegos("Terror", ventana);
            }
        });
        //Al seleccionarlo, se llama al método mostrarJuegos con "Aventura" como categoría y la ventana principal (ventana)
        productos3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarJuegos("Aventura", ventana);
            }
        });
        //Agrega el menú "Categorías" (junto con sus elementos de menú: "Acción", "Terror" y "Aventura") a la barra de menús (menuBar)
        menuBar.add(categorias);

        //Crea un menú llamado "Ayuda" que se añade a la barra de menús (menuBar)
        JMenu help = new JMenu("Ayuda");
        //Dentro del menú "Ayuda", añade un elemento llamado "Soporte"
        JMenuItem soporte = new JMenuItem("Soporte");
        menuBar.add(help);
        help.add(soporte);
        //Cuando el usuario selecciona "Soporte", se ejecuta el método VentanaSoporte de la clase soporteTecnico.
        soporte.addActionListener(e -> soporteTecnico.VentanaSoporte());

        //Crea un menú llamado "Usuario" y lo añade a la barra de menús
        JMenu cliente = new JMenu("Usuario");
        //Dentro del menú "Usuario", añade un elemento llamado "Registrarse"
        JMenuItem registro = new JMenuItem("Registrarse");
        cliente.add(registro);
        menuBar.add(cliente);
        //Crea un menú llamado "Creadores", que será añadido a la barra de menús
        JMenu responsables = new JMenu("Creadores");
        //Crea un elemento de menú llamado "Esteban".
        JMenuItem creadores = new JMenuItem("Esteban");
        //Agrega un listener que ejecuta el método VentanaCreadorCuatro de la clase "esteban" cuando se selecciona esta opción.
        creadores.addActionListener(e -> esteban.VentanaCreadorCuatro());
        //Crea un elemento de menú llamado "Erick".
        JMenuItem creadores2 = new JMenuItem("Erick");
        //Agrega un listener que ejecuta el método VentanaCreadorTres de la clase "erick" cuando se selecciona esta opción.
        creadores2.addActionListener(e -> erick.VentanaCreadorTres());
        //Crea un elemento de menú llamado "David".
        JMenuItem creadores3 = new JMenuItem("David");
        //Agrega un listener que ejecuta el método VentanaCreadorDos de la clase "david" cuando se selecciona esta opción.
        creadores3.addActionListener(e -> david.VentanaCreadorDos());
        //Crea un elemento de menú llamado "Bryan".
        JMenuItem creadores4 = new JMenuItem("Bryan");
        //Agrega un listener que ejecuta el método VentanaCreadorUno de la clase "bryan" cuando se selecciona esta opción.
        creadores4.addActionListener(e -> bryan.VentanaCreadorUno());
        //Añade todos los elementos de menú ("Esteban", "Erick", "David", "Bryan") al menú "Creadores"
        menuBar.add(responsables);
        responsables.add(creadores);
        responsables.add(creadores2);
        responsables.add(creadores3);
        responsables.add(creadores4);
        //Crea un menú llamado "Inicio", que será añadido a la barra de menús
        JMenu Inicio1 = new JMenu("Inicio");
        JMenuItem IniciarLaSesion = new JMenuItem("Iniciar sesion");
         // Vincular el ítem con el método iniciarSesion
         IniciarLaSesion.addActionListener(e -> {
            // Creamos una instancia de la clase InicioSesion
            InicioSesion inicioSesion = new InicioSesion();
            // Llamamos al método iniciarSesion de la clase "InicioSesion"
            inicioSesion.iniciarSesion();
        });
        //Añade el elemento "IniciarLaSesion" al menu Inicio1
        menuBar.add(Inicio1);
        Inicio1.add(IniciarLaSesion);

        // Acción para "Registrarse"
        //Esto define lo que sucede cuando el usuario selecciona la opción "Registrarse".
        registro.addActionListener(e -> {
            //Imprime un mensaje en la consola para fines de depuración, indicando que la opción "Registro" ha sido seleccionada.
            System.out.println("Opción de registro seleccionada.");
            //Llama al método estático "realizarRegistro" de la clase "Registro", pasando como argumento la "ventana"
            Registro nuevoUsuario = Registro.realizarRegistro(ventana);
            //Si el objeto nuevoUsuario no es null (es decir, el registro fue exitoso), 
            //muestra una ventana emergente de tipo JOptionPane con un mensaje de confirmación.
            if (nuevoUsuario != null) {
                //El mensaje incluye los datos del usuario recién registrado
                // el simbolo "\n" es salto de linea para que se visualice de mejor forma los datos del usuario.
                JOptionPane.showMessageDialog(ventana,"Registro Exitoso:\n" +"Nombre: " + nuevoUsuario.getNombre() + "\n" +"Fecha de Nacimiento: " + nuevoUsuario.getFechaNacimiento() + "\n" +"Teléfono: " + nuevoUsuario.getTelefono() + "\n" +"Correo: " + nuevoUsuario.getCorreo() + "\n" +"Contraseña: " + nuevoUsuario.getContraseña(),"Registro Completado",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        //Asigna la barra de menús (menuBar) a la ventana principal (ventana)
        ventana.setJMenuBar(menuBar);

        // Configurar el panel como contenido de la ventana
        ventana.setContentPane(fondo);

        // Hacer visible la ventana
        ventana.setVisible(true);
    }
    // Método para mostrar los juegos
    private static void mostrarJuegos(String categoria, JFrame ventana) {
        // Obtener la lista de juegos desde la base de datos.
        List<Videojuegos> juegos = Videojuegos.obtenerJuegosPorCategoria(categoria);

        // Crear una nueva ventana para mostrar los juegos
        JFrame ventanaJuegos = new JFrame(categoria + " - Juegos");
        // Tamaño de la ventana
        ventanaJuegos.setSize(800, 400); 
        // Cerrara solo esta ventana
        ventanaJuegos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        //Configura el diseño (layout) de la ventana ventanaJuegos para que utilice un esquema de diseño de tipo BorderLayout.
        ventanaJuegos.setLayout(new BorderLayout());

        //Define un arreglo de cadenas (String[]) que contiene los nombres de las columnas para una tabla.
        String[] columnas = {"ID", "Título", "Género", "Fecha Lanzamiento", "Calificación", "Plataforma", "Acción"};
        //objeto
        //----------------------------------------------------------------------------------------------------------
        //"DefaultTableModel" Este es un modelo de tabla predeterminado en Java Swing que gestiona los datos de una tabla (JTable)
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            //Este método determina si una celda de la tabla puede ser editada.
            public boolean isCellEditable(int row, int column) {
                // Solo permitira editar la columna de acción
                //Los valores de las columnas "Acción" pueden ser editados por el usuario.
                return column == 6; 
            }
        };

        // Llenar el modelo con los datos de los videojuegos
        for (Videojuegos juego : juegos) {
            // Crea una nueva fila en el modelo de tabla (modeloTabla) con los datos de un videojuego.
            modeloTabla.addRow(new Object[] {
                //Obtiene el identificador único del videojuego.
                juego.getID(),
                juego.getTitulo(),
                juego.getGenero(),
                juego.getFecha_lanzamiento(),
                juego.getCalificacion(),
                juego.getPlataforma(),
                // Texto del botón
                "Comprar" 
            });
        }

        // Crear la tabla con el modelo
        JTable tabla = new JTable(modeloTabla);

        // Hacer que la tabla sea desplazable si tiene muchos datos
        JScrollPane scrollPane = new JScrollPane(tabla);
        ventanaJuegos.add(scrollPane, BorderLayout.CENTER);

        // Hacer visible la ventana con la tabla
        ventanaJuegos.setVisible(true);
    }

    // Método de confirmación de compra
    public static void mostrarConfirmacionCompra(Videojuegos juego, String usuario) {
        String mensaje = "Compra realizada con éxito\n" +
                         "Juego: " + juego.getTitulo() + "\n" +
                         "Usuario: " + usuario + "\n" +
                         "Plataforma: " + juego.getPlataforma() + "\n" +
                         "Fecha de Lanzamiento: " + juego.getFecha_lanzamiento() + "\n" +
                         "Calificación: " + juego.getCalificacion();
        JOptionPane.showMessageDialog(null, mensaje, "Confirmación de Compra", JOptionPane.INFORMATION_MESSAGE);
    }

}