package Interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JSlider;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Button;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import Data.DataProductos;
import Entidades.Productos;

import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaProductos {

	public JFrame frmGestionProductos;
	private JTextField txtCodigoBarras;
	private JTextField txtNombre;
	private JSlider sldPrecioVenta;
	private JSlider sldStock;
	private JLabel lblValorPrecio;
	private JLabel lblValorStock;
	private JTable tblProductos;
	
	
	
	
	
	
	
DefaultTableModel model = new DefaultTableModel();
ArrayList<Productos>ListaProductos= new ArrayList<Productos>();
DataProductos dp= new DataProductos();
Productos p= new Productos();
int fila=-1;
private JButton btnActualizar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaProductos window = new PantallaProductos();
					window.frmGestionProductos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaProductos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionProductos = new JFrame();
		frmGestionProductos.setIconImage(
				Toolkit.getDefaultToolkit().getImage(PantallaProductos.class.getResource("/img/cecylogo.png")));
		frmGestionProductos.setTitle("GESTION PRODUCTOS");
		frmGestionProductos.setBounds(100, 100, 603, 601);
		frmGestionProductos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGestionProductos.setLocationRelativeTo(null);
		frmGestionProductos.getContentPane().setLayout(null);

		JLabel lblTitulo = new JLabel("GESTION DE PRODUCTOS");
		lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblTitulo.setBounds(199, 11, 223, 14);
		frmGestionProductos.getContentPane().add(lblTitulo);

		JLabel lblCodigoBarras = new JLabel("Codigo de Barras");
		lblCodigoBarras.setBounds(22, 59, 46, 14);
		frmGestionProductos.getContentPane().add(lblCodigoBarras);

		txtCodigoBarras = new JTextField();
		txtCodigoBarras.setBounds(92, 56, 86, 20);
		frmGestionProductos.getContentPane().add(txtCodigoBarras);
		txtCodigoBarras.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 100, 46, 14);
		frmGestionProductos.getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(92, 97, 86, 20);
		frmGestionProductos.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblPrecioVenta = new JLabel("PrecioVenta");
		lblPrecioVenta.setBounds(22, 142, 46, 14);
		frmGestionProductos.getContentPane().add(lblPrecioVenta);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(22, 183, 46, 14);
		frmGestionProductos.getContentPane().add(lblStock);

		lblValorPrecio = new JLabel("0.0");
		lblValorPrecio.setBounds(316, 128, 46, 14);
		frmGestionProductos.getContentPane().add(lblValorPrecio);

		sldPrecioVenta = new JSlider();
		sldPrecioVenta.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblValorPrecio.setText("$" + sldPrecioVenta.getValue());
			}
		});
		sldPrecioVenta.setValue(1);
		sldPrecioVenta.setMinimum(1);
		sldPrecioVenta.setPaintTicks(true);
		sldPrecioVenta.setPaintLabels(true);
		sldPrecioVenta.setBounds(92, 128, 200, 26);
		frmGestionProductos.getContentPane().add(sldPrecioVenta);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(redimensionar(32, 32, "/img/add.png"));
		btnAgregar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAgregar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAgregar.setVerticalAlignment(SwingConstants.CENTER);

		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtCodigoBarras.getText().length() == 0 || txtNombre.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS", "ERROR", 
								JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecylogo.png"));
						JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR", null, 
								JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecylogo.png"));
						return;

					} 
					Productos p = new Productos(txtCodigoBarras.getText(), txtNombre.getText(),
							sldPrecioVenta.getValue(), sldStock.getValue());
					if (!p.insertarProducto()) {
						JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR", "ERROR",
								JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecylogo.png"));
					}else {
						limpiar();
						JOptionPane.showMessageDialog(null, "SE INSERTO CORRECTAMENTE", "EXITO",
								JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecylogo.png"));
					}
	
					
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR");

				}

			}
		});
		btnAgregar.setBounds(25, 219, 89, 87);
		frmGestionProductos.getContentPane().add(btnAgregar);

		lblValorStock = new JLabel("0.0");
		lblValorStock.setBounds(316, 171, 46, 14);
		frmGestionProductos.getContentPane().add(lblValorStock);

		sldStock = new JSlider();
		sldStock.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblValorStock.setText("" + sldStock.getValue());

			}
		});
		sldStock.setValue(1);
		sldStock.setPaintTicks(true);
		sldStock.setPaintLabels(true);
		sldStock.setMinimum(1);
		sldStock.setBounds(92, 171, 200, 26);
		frmGestionProductos.getContentPane().add(sldStock);
		
		JScrollPane sclProductos = new JScrollPane();
		sclProductos.setBounds(117, 377, 394, 174);
		frmGestionProductos.getContentPane().add(sclProductos);
		
		tblProductos = new JTable();
		tblProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblProductos.getSelectedRow();
				p=ListaProductos.get(fila);	
				txtCodigoBarras.setText(p.getCodigoBarras());
				txtNombre.setText(p.getNombre());
				sldPrecioVenta.setValue((int)p.getPrecioVenta());
				sldStock.setValue(p.getStock());
			
			}
		});
		model.addColumn("CODIGO DE BARRAS");
		model.addColumn("NOMBRE DEL PRODUCTO");
		model.addColumn("PRECIO");
		model.addColumn("STOCK");
		sclProductos.setViewportView(tblProductos);
		
		JButton btnEliminar = new JButton("Eliminar");
		
		btnEliminar.setIcon(redimensionar(32, 32, "/img/delete.png"));
		btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar.setVerticalAlignment(SwingConstants.CENTER);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(fila!=-1) {
						int opcion = JOptionPane.showConfirmDialog(null, "ESTAS SEGURO DE ELIMINAR PRODUCTO?",
								"ELIMINAR PRODUCTO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
								redimensionar(32, 32, "/img/add.png"));
						
						if(opcion==0) {
							if(p.eliminarProducto()) {
								JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE", "EXITO",
										JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecylogo.png"));
								limpiar();
								fila=-1;
								cargarProductos();
								
							}else {
								JOptionPane.showMessageDialog(null, "ERORR AL ELIMINAR", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "FALTA SELECCIONAR PRODUCTO", "ERROR",
								JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecylogo.png"));
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERORR AL ELIMINAR", "ERROR", JOptionPane.ERROR_MESSAGE);// TODO: handle exception
				}
			}
		});
		btnEliminar.setBounds(148, 219, 89, 87);
		frmGestionProductos.getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(redimensionar(32, 32, "/img/edit.png"));
		btnActualizar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnActualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnActualizar.setVerticalAlignment(SwingConstants.CENTER);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila !=-1) {
					if (txtCodigoBarras.getText().length() == 0 || txtNombre.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS", "ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					} 
					
					p.setCodigoBarras(txtCodigoBarras.getText());
					p.setNombre(txtNombre.getText());
					p.setPrecioVenta(sldPrecioVenta.getValue());
					p.setStock(sldStock.getValue());
					
					if (!p.actualizarProducto()) {
						JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR", "ERROR", JOptionPane.ERROR_MESSAGE);
					}else {
						fila= -1;
						limpiar();
						cargarProductos();
						JOptionPane.showMessageDialog(null, "SE ACTUALIZO CORRECTAMENTE", "EXITO",
								JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecylogo.png"));
					}
					}else {
						JOptionPane.showMessageDialog(null, "FALTA SELECCIONAR PRODUCTO", "ERROR",
								JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecylogo.png"));
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR");

				}
			}
		});
		btnActualizar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnActualizar.setVerticalAlignment(SwingConstants.CENTER);
		btnActualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnActualizar.setBounds(273, 219, 96, 87);
		frmGestionProductos.getContentPane().add(btnActualizar);
		cargarProductos();

	}
	public void cargarProductos() {
		while(model.getRowCount()>0)model.removeRow(0);
		ListaProductos=dp.cargarProductos();
		for (Productos p : ListaProductos) {
			
			model.addRow(new Object[] {
					
					p.getCodigoBarras(),
					p.getNombre(),
					p.getPrecioVenta(),
					p.getStock()	
			});
		}
		tblProductos.setModel(model);
	}

	public ImageIcon redimensionar(int w, int h, String ruta) {
		ImageIcon icono = new ImageIcon(PantallaProductos.class.getResource(ruta));
		Image imagenOriginal = icono.getImage();
		Image imagenEscalada = imagenOriginal.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionado = new ImageIcon(imagenEscalada);
		return iconRedimensionado;
	}

	public void limpiar() {
		txtCodigoBarras.setText("");
		txtNombre.setText("");
		sldPrecioVenta.setValue(1);
		sldStock.setValue(1);
	}
}
