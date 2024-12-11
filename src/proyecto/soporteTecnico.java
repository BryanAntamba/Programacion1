package proyecto;

import javax.swing.*;
import java.awt.*;

public class soporteTecnico {
    // Metodo estatico la cual no evuelve ningun valor
    public static void VentanaSoporte() {
        // Se crea un objeto JFrame con el título "GameStore"
        JFrame ventana = new JFrame("GameStore");
        // Define que al cerrar la ventana, solo se cerrará esta ventana específica,
        // pero el programa seguirá ejecutándose si hay otras ventanas abiertas.
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // setSize(1000, 700): Establece el tamaño de la ventana
        ventana.setSize(1000, 700);

        // Configurar el ícono personalizado
        // Esta imagen se establece como el ícono de la ventana mediante el método
        // setIconImage
        Image icono = Toolkit.getDefaultToolkit().getImage("img/icono.png");
        // Para que sea visible el icono en la aplicacion
        ventana.setIconImage(icono);

        // Crear un JPanel con un fondo personalizado
        ImageIcon fondoApp = new ImageIcon("img/principal.jpg");
        // Se crea un JPanel con un comportamiento personalizado para dibujar la imagen.
        JPanel fondo = new JPanel() {
            @Override
            // paintComponent(Graphics g): Es un método que se sobrescribe para dibujar la
            // imagen en el fondo del panel.
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibuja la imagen cargada del (fondoApp.getImage()) en el panel, escalándola
                // al tamaño del panel.
                g.drawImage(fondoApp.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        // Se establece el diseño del panel como null, lo que permite posicionar
        // manualmente los componentes.
        fondo.setLayout(null);

        // Crear y configurar los labels (etiquetas de texto)
        JLabel titulo = new JLabel("Soporte Técnico", SwingConstants.CENTER);
        // Establecer la posición y tamaño del título
        titulo.setBounds(345, 40, 350, 40); 
        // Establecer el color del texto como blanco
        titulo.setForeground(Color.WHITE); 
        // Establecer la fuente del texto
        titulo.setFont(new Font("Arial", Font.BOLD, 35)); 
        // Añadir el JLabel del título al panel de fondo
        fondo.add(titulo); 

        // Crear un JLabel con la descripción en formato HTML centrado
        JLabel descripcion = new JLabel("<html>Si necesitas ayuda, estamos disponibles para ayudarte en lo que necesites. <br>" +"Contáctanos a través de nuestros medios:</html>",SwingConstants.CENTER);
                // Establecer la posición y el tamaño de la descripción
        descripcion.setBounds(30, 200, 940, 60); 
        // Establecer el color del texto como blanco
        descripcion.setForeground(Color.WHITE); 
        // Establecer la fuente del texto
        descripcion.setFont(new Font("Arial", Font.BOLD, 20));
         // Añadir el JLabel de descripción al panel de fondo
        fondo.add(descripcion);

        // Crear un JLabel para mostrar el correo electrónico
        JLabel email = new JLabel("Correo: GameStore@gmail.com", SwingConstants.CENTER);
        // Establecer la posición y tamaño del correo
        email.setBounds(300, 400, 400, 30); 
        // Establecer el color del texto como blanco
        email.setForeground(Color.WHITE); 
         // Establecer la fuente del texto
        email.setFont(new Font("Arial", Font.BOLD, 20));
        // Añadir el JLabel de correo al panel de fondo
        fondo.add(email); 

        // Crear un JLabel para mostrar el teléfono
        JLabel telefono = new JLabel("Teléfono: 0995538246", SwingConstants.CENTER);
        // Establecer la posición y tamaño del teléfono
        telefono.setBounds(300, 450, 400, 30); 
        // Establecer el color del texto como blanco
        telefono.setForeground(Color.WHITE); 
        // Establecer la fuente del texto
        telefono.setFont(new Font("Arial", Font.BOLD, 20)); 
        // Añadir el JLabel de teléfono al panel de fondo
        fondo.add(telefono); 

        // Utilizar un ComponentAdapter para redimensionar los componentes cuando cambie
        // el tamaño de la ventana
        ventana.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                // Obtiene el ancho actual de la ventana
                int anchoVentana = ventana.getWidth();

                // Centrar los componentes horizontalmente en la ventana, manteniendo sus
                // posiciones verticales
                // Centrar el título en la parte superior
                titulo.setBounds(calcularPosicionCentrada(anchoVentana, 300), 40, 300, 40);

                // Centrar la descripción, correo y teléfono en la ventana
                descripcion.setBounds(calcularPosicionCentrada(anchoVentana, 800), 250, 800, 60);
                email.setBounds(calcularPosicionCentrada(anchoVentana, 800), 400, 800, 60);
                telefono.setBounds(calcularPosicionCentrada(anchoVentana, 800), 440, 800, 60);
            }

            // Método para calcular la posición X centrada de un componente basado en el
            // ancho de la ventana
            private int calcularPosicionCentrada(int anchoVentana, int anchoComponente) {
                return (anchoVentana - anchoComponente) / 2; // Calcular la posición centrada
            }
        });

        // Crear una barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Crear el menú "Salir"
        JMenu salir = new JMenu("Salir");

        // Crear un ítem de menú "Cerrar ventana"
        JMenuItem cerrarSesion = new JMenuItem("Cerrar ventana");
        // Agregar un ActionListener para cerrar la ventana al hacer clic en "Cerrar
        // ventana"
        cerrarSesion.addActionListener(e -> ventana.dispose());
        // Añadir el menú "Salir" a la barra de menú
        menuBar.add(salir); 
        // Añadir el ítem "Cerrar ventana" al menú "Salir"
        salir.add(cerrarSesion); 

        // Establecer la barra de menú en la ventana
        ventana.setJMenuBar(menuBar);

        // Hacer visible la ventana
        ventana.setVisible(true);

        // Configurar el panel de fondo como el contenido de la ventana
        ventana.setContentPane(fondo);

        // Hacer visible la ventana una vez configurada completamente
        ventana.setVisible(true);
    }
}