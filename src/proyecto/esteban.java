package proyecto;
import javax.swing.*;
import java.awt.*;
public class esteban {
    
    public static void VentanaCreadorCuatro() {
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
        JLabel titulo = new JLabel("Esteban sanchez", SwingConstants.CENTER);
        titulo.setBounds(345, 40, 350, 40);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 35));
        fondo.add(titulo);

        JLabel descripcion = new JLabel(
                "<html>Desarrollador de ingreso de metodo de pago con debito y nombre de la facturacion, <br>colaborador en facturacion y colaborador en desarrollo de la base de datos del proyecto.</html>",
                SwingConstants.CENTER);
        descripcion.setBounds(30, 200, 940, 60);
        descripcion.setForeground(Color.WHITE);
        descripcion.setFont(new Font("Arial", Font.BOLD, 20));
        fondo.add(descripcion);
        ImageIcon Foto = new ImageIcon("img/esteban.jpg");
        JLabel etiquetaImagen = new JLabel(Foto);
        etiquetaImagen.setBounds(355, 320, 300, 300); // Ajusta la posición y tamaño
        fondo.add(etiquetaImagen);
        ImageIcon originalIcon = new ImageIcon("img/esteban.jpg");
        //getImage() Se obtiene el objeto Image de la imagen cargada, que se usará para manipular su tamaño.
        Image originalImage = originalIcon.getImage();

        //Estos métodos devuelven el ancho y alto actuales del componente etiquetaImagen
        int labelWidth = etiquetaImagen.getWidth();
        int labelHeight = etiquetaImagen.getHeight();
        //Este método escala la imagen al tamaño especificado.
        Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

        //Es importante asegurarse de que etiquetaImagen tenga un tamaño definido antes de ejecutar este código.
        etiquetaImagen.setIcon(new ImageIcon(scaledImage));
        //Se agrega un listener al objeto ventana para detectar eventos relacionados con los componentes de la interfaz
        ventana.addComponentListener(new java.awt.event.ComponentAdapter() {
            //Este método se ejecuta automáticamente cada vez que la ventana es redimensionada.
            public void componentResized(java.awt.event.ComponentEvent evt) {
                //Obtiene el ancho actual de la ventana con el método getWidth()
                int anchoDeVentana = ventana.getWidth();
                //Establece la posición (x, y) y las dimensiones width, height de los componentes
                titulo.setBounds((anchoDeVentana - 300) / 2, 40, 300, 40); // Centrado
                descripcion.setBounds((anchoDeVentana - 800) / 2, 250, 800, 60); // Justo debajo del título
               
            }
        });
        

        JMenuBar menuBar = new JMenuBar();
        // Crear el menú "Salir"
        JMenu salir = new JMenu("Salir");
        JMenuItem cerrarSesion = new JMenuItem("Cerrar ventana");
        // Se realiza una acción para cerra el sistema inmobiliario
       // Crear el menú "Salir"
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
