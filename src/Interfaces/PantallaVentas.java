package Interfaces;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Entidades.DetalleVentas;
import Entidades.Productos;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

public class PantallaVentas {

	private JFrame frmCecyPos;
	PantallaProductos pp= new PantallaProductos();
	private JTextField txtCodigoBarras;
	double  total= 0.0;
	private JButton btnAgregarProducto;
	private JLabel lblCodigoBarras;
	private JButton btnBuscarProducto;
	private JTable table;
	
    DefaultTableModel model  = new DefaultTableModel();
    private JLabel lblTotal;
    private JLabel lblMxn;
    private JButton btnCobrar;
    String codigoBarras= "";
    ArrayList<DetalleVentas> detalleVenta = new ArrayList<DetalleVentas>();
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaVentas window = new PantallaVentas();
					window.frmCecyPos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public PantallaVentas() {
		initialize();
	}

	
	private void initialize() {
		frmCecyPos = new JFrame();
		frmCecyPos.getContentPane().setFont(new Font("Verdana", Font.BOLD, 25));
		frmCecyPos.setIconImage(
				Toolkit.getDefaultToolkit().getImage(PantallaVentas.class.getResource("/img/cecylogo.png")));
		frmCecyPos.setTitle("CECY POS");
		frmCecyPos.setBounds(100, 100, 916, 727);
		frmCecyPos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCecyPos.getContentPane().setLayout(null);
		frmCecyPos.setLocationRelativeTo(null);
		
		JButton btnProductos = new JButton("Productos");
		btnProductos.setIcon(redimensionar(32 , 32, "/img/productos.png"));
		btnProductos.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnProductos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProductos.setVerticalAlignment(SwingConstants.CENTER);
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pp.frmGestionProductos.setVisible(true);
			}
		});
		btnProductos.setBounds(10, 11, 96, 64);
		frmCecyPos.getContentPane().add(btnProductos);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setIcon(redimensionar(32, 32, "/img/salir.png"));
		btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setVerticalAlignment(SwingConstants.CENTER);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres salir?", "Salir", JOptionPane.YES_NO_OPTION) ==0) {
					JOptionPane.showMessageDialog(null, "ADIOS");
					System.exit(0);	
				}
				
			}
		});
		btnSalir.setBounds(801, 11, 89, 64);
		frmCecyPos.getContentPane().add(btnSalir);
		
		txtCodigoBarras = new JTextField();
		txtCodigoBarras.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					agregarProducto();
				}
			}
		});
		txtCodigoBarras.setBounds(116, 93, 416, 27);
		frmCecyPos.getContentPane().add(txtCodigoBarras);
		txtCodigoBarras.setColumns(10);
		
		lblCodigoBarras = new JLabel("Codigo de barras");
		lblCodigoBarras.setBounds(10, 93, 124, 27);
		frmCecyPos.getContentPane().add(lblCodigoBarras);
		
		btnAgregarProducto = new JButton("Agregar Producto");
		btnAgregarProducto.setIcon(redimensionar(16, 16, "/img/add.png"));
		btnAgregarProducto.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnAgregarProducto.setHorizontalAlignment(SwingConstants.CENTER);
		btnAgregarProducto.setVerticalTextPosition(SwingConstants.CENTER);
		btnAgregarProducto.setVerticalAlignment(SwingConstants.CENTER);	
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducto();
			}
		});
		btnAgregarProducto.setBounds(542, 95, 169, 23);
		frmCecyPos.getContentPane().add(btnAgregarProducto);
		
		btnBuscarProducto = new JButton("Buscar Producto");
		btnBuscarProducto.setIcon(redimensionar(16, 16, "/img/buscar.png"));
		btnBuscarProducto.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnBuscarProducto.setHorizontalAlignment(SwingConstants.CENTER);
		btnBuscarProducto.setVerticalTextPosition(SwingConstants.CENTER);
		btnBuscarProducto.setVerticalAlignment(SwingConstants.CENTER);
		btnBuscarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscarProducto.setBounds(721, 95, 169, 23);
		frmCecyPos.getContentPane().add(btnBuscarProducto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 189, 836, 209);
		frmCecyPos.getContentPane().add(scrollPane);
		
		table = new JTable();
		model.addColumn("CODIGO");
		model.addColumn("NOMBRE");
		model.addColumn("PRECIO");
		model.addColumn("CANTIDAD");
		model.addColumn("IMPORTE");
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Verdana", Font.BOLD, 25));
		lblTotal.setBounds(584, 426, 107, 50);
		frmCecyPos.getContentPane().add(lblTotal);
		
		lblMxn = new JLabel("$ 0.00 MXN");
		lblMxn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMxn.setFont(new Font("Verdana", Font.BOLD, 30));
		lblMxn.setBounds(637, 422, 234, 55);
		frmCecyPos.getContentPane().add(lblMxn);
		
		btnCobrar = new JButton("Cobrar");
		btnCobrar.setIcon(redimensionar(32, 32, "/img/cobrar.png"));
		btnCobrar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCobrar.setHorizontalAlignment(SwingConstants.CENTER);
		btnCobrar.setVerticalTextPosition(SwingConstants.CENTER);
		btnCobrar.setVerticalAlignment(SwingConstants.CENTER);
		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCobrar.setBounds(553, 499, 138, 41);
		frmCecyPos.getContentPane().add(btnCobrar);
	}
	
	public ImageIcon redimensionar(int w, int h, String ruta) {
		ImageIcon icono = new ImageIcon(PantallaProductos.class.getResource(ruta));
		Image imagenOriginal = icono.getImage();
		Image imagenEscalada = imagenOriginal.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionado = new ImageIcon(imagenEscalada);
		return iconRedimensionado;
	}
	public void agregarProducto() {
		codigoBarras = txtCodigoBarras.getText();
		if(codigoBarras.length() == 0 ) {
			JOptionPane.showMessageDialog(null, "NO HAS INGRESADO CODIGO DE BARRAS", "ERROR", 
					JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecylogo.png"));
			return;
		}
		int id= -1;
		for(Productos p : pp.ListaProductos) {
			if(p.getCodigoBarras().equals(codigoBarras)) {
				id = p.getIdProducto();
			}
		}
		if(id == -1) {
			JOptionPane.showMessageDialog(null, "NO EXISTE PRODUCTO", "ERROR", 
					JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecylogo.png"));
			return;
		}
		if (!siEsta(id)) {
			detalleVenta.add(new DetalleVentas(id, 1));
		}else {
			incrementaCantidad(id);
		}
		while (model.getRowCount()> 0)
			model.removeRow(0);
		total=0;
		for (DetalleVentas d : detalleVenta) {
			Productos p = buscarProducto(d.getIdProducto());
			model.addRow(new Object[] { p.getCodigoBarras(), p.getNombre(), p.getPrecioVenta(), d.getCantidad(),
					(p.getPrecioVenta() * d .getCantidad()) });
			total += (p.getPrecioVenta() * d.getCantidad());
			}
		table.setModel(model);
		lblMxn.setText("" + aMoneda(total));
		} 
	
	public Productos buscarProducto(int idProducto) {
		for (Productos p : pp.ListaProductos){
			if (p.getIdProducto() == idProducto) {
				return p;
			}
		}
		return null;		
	}
	public void incrementaCantidad(int idProducto) {
		int index = -1;
		for (int i = 0; i <detalleVenta.size(); i++) {
			if( idProducto == detalleVenta.get(i).getIdProducto()) {
				index= i;
			}
		}
		detalleVenta.get(index).setCantidad(detalleVenta.get(index).getCantidad() + 1);
	}
	
	public boolean siEsta(int idProducto) {
		boolean si =false;
		for(DetalleVentas d : detalleVenta) {
			if (d.getIdProducto() == idProducto) {
				return true;
			}
		}
		return false;
	}
	
	public static String aMoneda(double cantidad) {
		Locale localeMexico = new Locale("es", "MX");
		NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(localeMexico);
		return formatoMoneda.format(cantidad);
	}
}
