package sistemaCompras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GestionVentasApp extends JFrame {

    private double totalVenta = 0.0;
    private JLabel totalLabel;
    private JTextArea productosTextArea;
    private JLabel fechaLabel;
    private double totalIngresos = 0.0;

    public GestionVentasApp() {
        setTitle("Gestión de Ventas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));

        // Etiqueta del título
        JLabel tituloLabel = new JLabel("Bienvenido a Cloty Food");
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(tituloLabel);

        // Etiqueta para seleccionar categoría
        JLabel categoriaLabel = new JLabel("Seleccione una categoría:");
        panel.add(categoriaLabel);

        // Panel para botones de categorías
        JPanel categoriaPanel = new JPanel();
        categoriaPanel.setLayout(new FlowLayout());

        JButton golosinasButton = new JButton("Golosinas");
        golosinasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProductosGolosinas();
            }
        });
        categoriaPanel.add(golosinasButton);

        JButton comidaButton = new JButton("Comida");
        comidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProductosComida();
            }
        });
        categoriaPanel.add(comidaButton);

        JButton bebidaButton = new JButton("Bebida");
        bebidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProductosBebida();
            }
        });
        categoriaPanel.add(bebidaButton);

        panel.add(categoriaPanel);

        // Área de texto para mostrar productos
        productosTextArea = new JTextArea();
        productosTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(productosTextArea);
        panel.add(scrollPane);

        // Etiqueta para mostrar el total de la venta
        totalLabel = new JLabel("Total de la venta: $" + totalVenta);
        panel.add(totalLabel);

        // Botón para compra finalizada
        JButton compraFinalizadaButton = new JButton("Compra Finalizada");
        compraFinalizadaButton.setFont(new Font("Arial", Font.BOLD, 28));
        compraFinalizadaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalIngresos += totalVenta; // Actualizar total de ingresos
                JOptionPane.showMessageDialog(null, "Compra finalizada. Total: $" + totalVenta);
                totalVenta = 0.0; // Reiniciar total de venta
                actualizarTotalVenta(); // Actualizar etiqueta del total de la venta
                productosTextArea.setText(""); // Limpiar área de texto de productos
                actualizarTotalIngresos(); // Actualizar etiqueta del total de ingresos
            }
        });
        panel.add(compraFinalizadaButton);

        // Etiqueta para mostrar la fecha y el total de ingresos
        fechaLabel = new JLabel();
        panel.add(fechaLabel);

        // Agregar panel al frame
        add(panel);

        setVisible(true);

        // Actualizar fecha al iniciar la aplicación
        actualizarFecha();
    }

    private void mostrarProductosGolosinas() {
        String[] productosGolosinas = {"Chocolate Águila ($1000)", "Mogul ($500)", "Alfajor Jorgelin ($1000)"};
        String seleccion = (String) JOptionPane.showInputDialog(this, "Seleccione un producto:", "Golosinas",
                JOptionPane.PLAIN_MESSAGE, null, productosGolosinas, productosGolosinas[0]);
        if (seleccion != null) {
            double precio = extraerPrecio(seleccion);
            totalVenta += precio;
            actualizarTotalVenta(); // Actualizar etiqueta del total de la venta
            productosTextArea.append(seleccion + "\n"); // Mostrar producto en el área de texto
        }
    }

    private void mostrarProductosComida() {
        String[] productosComida = {"Hamburguesa ($2000)", "Milanesa ($2000)", "Empanada ($1000)"};
        String seleccion = (String) JOptionPane.showInputDialog(this, "Seleccione un producto:", "Comida",
                JOptionPane.PLAIN_MESSAGE, null, productosComida, productosComida[0]);
        if (seleccion != null) {
            double precio = extraerPrecio(seleccion);
            totalVenta += precio;
            actualizarTotalVenta(); // Actualizar etiqueta del total de la venta
            productosTextArea.append(seleccion + "\n"); // Mostrar producto en el área de texto
        }
    }

    private void mostrarProductosBebida() {
        String[] productosBebida = {"Coca Cola ($800)", "Fanta ($800)", "Agua ($300)"};
        String seleccion = (String) JOptionPane.showInputDialog(this, "Seleccione un producto:", "Bebida",
                JOptionPane.PLAIN_MESSAGE, null, productosBebida, productosBebida[0]);
        if (seleccion != null) {
            double precio = extraerPrecio(seleccion);
            totalVenta += precio;
            actualizarTotalVenta(); // Actualizar etiqueta del total de la venta
            productosTextArea.append(seleccion + "\n"); // Mostrar producto en el área de texto
        }
    }

    private double extraerPrecio(String producto) {
        // Eliminar el símbolo de moneda "$" de la cadena
        producto = producto.replace("$", "");
        // Extraer el precio del producto (parte que está entre paréntesis)
        int inicio = producto.indexOf("(") + 1;
        int fin = producto.indexOf(")");
        String precioStr = producto.substring(inicio, fin);
        return Double.parseDouble(precioStr);
    }

    private void actualizarTotalVenta() {
        totalLabel.setText("Total de la venta: $" + totalVenta);
    }

    private void actualizarTotalIngresos() {
        fechaLabel.setText("Fecha: " + obtenerFechaActual() + " - Total de ingresos: $" + totalIngresos);
    }

    private void actualizarFecha() {
        fechaLabel.setText("Fecha: " + obtenerFechaActual() + " - Total de ingresos: $" + totalIngresos);
    }

    private String obtenerFechaActual() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestionVentasApp();
            }
        });
    }
}

