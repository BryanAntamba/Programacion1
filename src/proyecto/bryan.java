package proyecto;

import javax.swing.*;
import java.awt.*;

public class bryan {
    public static void VentanaCreadorUno() {
        // Crear una nueva ventana JFrame con título "GameStore"
        JFrame ventana = new JFrame("GameStore");

        // Establecer la acción de cierre de la ventana (cerrar cuando se presiona la X)
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Establecer el tamaño de la ventana a 1000x700 píxeles
        ventana.setSize(1000, 700);

        // Configurar un ícono personalizado para la ventana
        Image icono = Toolkit.getDefaultToolkit().getImage("img/icono.png");
        ventana.setIconImage(icono);

        // Crear un JPanel con fondo personalizado
        ImageIcon fondoApp = new ImageIcon("img/principal.jpg");
        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar la imagen de fondo en el panel
                g.drawImage(fondoApp.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        // Usar un layout nulo para poder posicionar los elementos manualmente
        fondo.setLayout(null);  

        // Crear un JLabel con el título "Bryan Antamba" centrado
        JLabel titulo = new JLabel("Bryan Antamba", SwingConstants.CENTER);
         // Establecer la posición y el tamaño del título
        titulo.setBounds(345, 40, 350, 40); 
        // Establecer el color del texto como blanco
        titulo.setForeground(Color.WHITE);  
        // Establecer la fuente del texto
        titulo.setFont(new Font("Arial", Font.BOLD, 35));  
        // Añadir el JLabel al panel de fondo
        fondo.add(titulo); 

        // Crear un JLabel con una descripción en formato HTML centrado
        JLabel descripcion = new JLabel("<html>Creador de l registro de usuario, Soporte tecnico, y portada de la aplicacion,<br> con manejos de las GUI y ayuda clases.</html>",SwingConstants.CENTER);
                // Establecer la posición y el tamaño de la descripción
        descripcion.setBounds(30, 200, 940, 60);  
        // Establecer el color del texto como blanco
        descripcion.setForeground(Color.WHITE); 
        // Establecer la fuente del texto
        descripcion.setFont(new Font("Arial", Font.BOLD, 20)); 
        // Añadir el JLabel de descripción al panel de fondo
        fondo.add(descripcion);  

        // Crear un JLabel con una imagen (foto del creador)
        ImageIcon Foto = new ImageIcon("img/bryan.jpg");
        JLabel etiquetaImagen = new JLabel(Foto);
        etiquetaImagen.setBounds(355, 320, 300, 300); // Ajustar la posición y tamaño de la imagen
        fondo.add(etiquetaImagen);  // Añadir la etiqueta con la imagen al panel de fondo

        // Escalar la imagen proporcionalmente a las dimensiones del JLabel
        Image originalImage = Foto.getImage();
        Image scaledImage = originalImage.getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH);

        // Establecer la imagen escalada en el JLabel
        etiquetaImagen.setIcon(new ImageIcon(scaledImage));

        // Ajustar el diseño cuando se cambia el tamaño de la ventana
        ventana.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int anchoDeVentana = ventana.getWidth();
                // Reposicionar y centrar el título cuando se cambia el tamaño de la ventana
                titulo.setBounds((anchoDeVentana - 300) / 2, 40, 300, 40); 
                // Reposicionar la descripción bajo el título
                descripcion.setBounds((anchoDeVentana - 800) / 2, 250, 800, 60);
            }
        });

        // Crear un JMenuBar para la barra de menú
        JMenuBar menuBar = new JMenuBar();
        
        // Crear un menú "Salir"
        JMenu salir = new JMenu("Salir");
        
        // Crear un ítem de menú para "Cerrar ventana"
        JMenuItem cerrarSesion = new JMenuItem("Cerrar ventana");
        
        // Agregar un ActionListener para cerrar la ventana cuando se selecciona el ítem
        cerrarSesion.addActionListener(e -> ventana.dispose());
        
        // Añadir el ítem al menú "Salir" y luego el menú a la barra de menú
        menuBar.add(salir);
        salir.add(cerrarSesion);

        // Establecer la barra de menú en la ventana
        ventana.setJMenuBar(menuBar);

        // Hacer visible la ventana
        ventana.setVisible(true);

        // Configurar el panel de fondo como el contenido principal de la ventana
        ventana.setContentPane(fondo);

        // Hacer visible la ventana una vez configurada completamente
        ventana.setVisible(true);
    }
}
