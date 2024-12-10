package proyecto;

import javax.swing.*;
import java.awt.*;

public class soporteTecnico {
    //Metodo estatico la cual no evuelve ningun valor
    public static void VentanaSoporte() {
        // Se crea un objeto JFrame con el título "GameStore"
        JFrame ventana = new JFrame("GameStore");
        //Define que al cerrar la ventana, solo se cerrará esta ventana específica, 
        //pero el programa seguirá ejecutándose si hay otras ventanas abiertas.
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setSize(1000, 700): Establece el tamaño de la ventana
        ventana.setSize(1000, 700);

        // Configurar el ícono personalizado
        //Esta imagen se establece como el ícono de la ventana mediante el método setIconImage
        Image icono = Toolkit.getDefaultToolkit().getImage("img/icono.png");
        //Para que sea visible el icono en la aplicacion
        ventana.setIconImage(icono);

        // Crear un JPanel con un fondo personalizado
        ImageIcon fondoApp = new ImageIcon("img/principal.jpg");
        //Se crea un JPanel con un comportamiento personalizado para dibujar la imagen.
        JPanel fondo = new JPanel() {
            @Override
            //paintComponent(Graphics g): Es un método que se sobrescribe para dibujar la imagen en el fondo del panel.
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Dibuja la imagen cargada del (fondoApp.getImage()) en el panel, escalándola al tamaño del panel.
                g.drawImage(fondoApp.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        //Se establece el diseño del panel como null, lo que permite posicionar manualmente los componentes.
        fondo.setLayout(null);

        // Crear y configurar los labels
        JLabel titulo = new JLabel("Soporte Técnico", SwingConstants.CENTER);
        titulo.setBounds(345, 40, 350, 40);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 35));
        fondo.add(titulo);

        JLabel descripcion = new JLabel("<html>Si necesitas ayuda, estamos disponibles para ayudarte en lo que necesites. <br>" +"Contáctanos a través de nuestros medios:</html>",
                SwingConstants.CENTER);
        descripcion.setBounds(30, 200, 940, 60);
        descripcion.setForeground(Color.WHITE);
        descripcion.setFont(new Font("Arial", Font.BOLD, 20));
        fondo.add(descripcion);

        JLabel email = new JLabel("Correo: GameStore@gmail.com", SwingConstants.CENTER);
        email.setBounds(300, 400, 400, 30);
        email.setForeground(Color.WHITE);
        email.setFont(new Font("Arial", Font.BOLD, 20));
        fondo.add(email);

        JLabel telefono = new JLabel("Teléfono: 0995538246", SwingConstants.CENTER);
        telefono.setBounds(300, 450, 400, 30);
        telefono.setForeground(Color.WHITE);
        telefono.setFont(new Font("Arial", Font.BOLD, 20));
        fondo.add(telefono);
        //Se utiliza un ComponentAdapter, que es una clase adaptadora que implementa la interfaz ComponentListener
        //Esto permite sobrescribir solo los métodos necesarios, en este caso, componentResized.
        ventana.addComponentListener(new java.awt.event.ComponentAdapter() {
            //Este método se ejecuta automáticamente cada vez que se cambia el tamaño de la ventana.
            public void componentResized(java.awt.event.ComponentEvent evt) {
                //Se obtiene el ancho actual de la ventana con el método getWidth(). 
                //Este valor es clave para calcular las posiciones horizontales de los componente
                int anchoDeVentana = ventana.getWidth();
                //setBounds(x, y, width, height) Establece la posición y las dimensiones del componente
                titulo.setBounds((anchoDeVentana - 300) / 2, 40, 300, 40); // Centrado
                //Calculamos la posición x (800) para centrar el componente horizontalmente en la ventana (/2)
                //Los valores 40, 250, 400, y 440 son las posiciones verticales (y) predefinidas para cada componente
                descripcion.setBounds((anchoDeVentana - 800) / 2, 250, 800, 60); // Justo debajo del título
                email.setBounds((anchoDeVentana - 800) / 2, 400, 800, 60); // Justo debajo del título
                telefono.setBounds((anchoDeVentana - 800) / 2, 440, 800, 60); // Justo debajo del título
            }
        });
        

        JMenuBar menuBar = new JMenuBar();
        // Crear el menú "Salir"
        JMenu salir = new JMenu("Salir");
        JMenuItem cerrarSesion = new JMenuItem("Cerrar ventana");
        // Se realiza una acción para cerra el sistema inmobiliario
       cerrarSesion.addActionListener(e -> ventana.dispose());
       menuBar.add(salir);
       salir.add(cerrarSesion);

        
        ventana.setJMenuBar(menuBar);
        ventana.setVisible(true);

        // Configurar el panel como contenido de la ventana
        ventana.setContentPane(fondo);

        // Hacer visible la ventana
        ventana.setVisible(true);
    }
}
