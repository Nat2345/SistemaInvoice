package Interfaces;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Locale;

public class Cobro {

	public JFrame frmCobrar;
	public JTextField txtMonto;
	public JLabel lblTitulo;
	public JLabel lblTotalPagar;
	PantallaVentas ventas =null;
	private JLabel lblCambio;
	double  total= 0.0;
	double monto=0.0;
	boolean validar = false;
	private JLabel lblCambioTitulo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cobro window = new Cobro();
					window.frmCobrar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public JLabel getLblTotal() {
		return lblTitulo;
	}


	public void setLblTotal(JLabel lblTotal) {
		this.lblTitulo = lblTotal;
	}


	public Cobro() {
		initialize();
	}
	

	public PantallaVentas getVentas() {
		return ventas;
	}


	public void setVentas(PantallaVentas ventas) {
		this.ventas = ventas;
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCobrar = new JFrame();
		frmCobrar.setBounds(100, 100, 582, 496);
		frmCobrar.setLocationRelativeTo(null);
		frmCobrar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCobrar.getContentPane().setLayout(null);
		
		JToggleButton tglEfectivo = new JToggleButton("Efectivo");
		tglEfectivo.setIcon(redimensionar(32, 32, "/img/efectivo.png"));
		tglEfectivo.setHorizontalTextPosition(SwingConstants.RIGHT);
		tglEfectivo.setHorizontalAlignment(SwingConstants.CENTER);
		tglEfectivo.setVerticalTextPosition(SwingConstants.CENTER);
		tglEfectivo.setVerticalAlignment(SwingConstants.CENTER);
		tglEfectivo.setBounds(58, 11, 127, 101);
		frmCobrar.getContentPane().add(tglEfectivo);
		
		JToggleButton tglTarjeta = new JToggleButton("Tarjeta");
		tglTarjeta.setIcon(redimensionar(32, 32, "/img/tarjeta.png"));
		tglTarjeta.setHorizontalTextPosition(SwingConstants.RIGHT);
		tglTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
		tglTarjeta.setVerticalTextPosition(SwingConstants.CENTER);
		tglTarjeta.setVerticalAlignment(SwingConstants.CENTER);
		tglTarjeta.setBounds(195, 11, 121, 101);
		frmCobrar.getContentPane().add(tglTarjeta);
		
		JToggleButton tglTransferencia = new JToggleButton("Transferencia");
		tglTransferencia.setIcon(redimensionar(32, 32, "/img/transferencia.png"));
		tglTransferencia.setHorizontalTextPosition(SwingConstants.RIGHT);
		tglTransferencia.setHorizontalAlignment(SwingConstants.CENTER);
		tglTransferencia.setVerticalTextPosition(SwingConstants.CENTER);
		tglTransferencia.setVerticalAlignment(SwingConstants.CENTER);
		tglTransferencia.setBounds(324, 11, 158, 101);
		frmCobrar.getContentPane().add(tglTransferencia);
		
		txtMonto = new JTextField();
		txtMonto.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				try  {
					if(txtMonto.getText().length() > 0) {
						 monto = Double.parseDouble(txtMonto.getText());
						if(total <= monto) {
							lblCambio.setText(aMoneda(monto - total));
							validar=true;
							
						}else {
							validar = false;
						}
					}
					
				} catch (Exception e2) {
					
				}
			}
		});
		txtMonto.setBounds(195, 212, 222, 23);
		frmCobrar.getContentPane().add(txtMonto);
		txtMonto.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("MONTO");
		lblNewLabel.setBounds(121, 216, 46, 14);
		frmCobrar.getContentPane().add(lblNewLabel);
		
		lblCambio = new JLabel("$0.0");
		lblCambio.setFont(new Font("Verdana", Font.BOLD, 20));
		lblCambio.setBounds(222, 280, 82, 47);
		frmCobrar.getContentPane().add(lblCambio);
		
		JButton btnPagar = new JButton("PAGAR");
		btnPagar.setIcon(redimensionar(32, 32, "/img/pagar.png"));
		btnPagar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnPagar.setHorizontalAlignment(SwingConstants.CENTER);
		btnPagar.setVerticalTextPosition(SwingConstants.CENTER);
		btnPagar.setVerticalAlignment(SwingConstants.CENTER);
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(validar) {
						ventas.guardarVenta();
						frmCobrar.setVisible(false);
						total=0;
						txtMonto.setText("");
						mensaje("PAGO EXITOSO", "EXITO");
					}else {
						mensaje("MONTO A PAGAR INVALIDO", "ERRROR");
					}
				} catch (Exception e2) {
					
				}
			}
		});
		btnPagar.setBounds(217, 351, 121, 69);
		frmCobrar.getContentPane().add(btnPagar);
		
		lblTotalPagar = new JLabel("$0.0");
		lblTotalPagar.setForeground(new Color(255, 0, 0));
		lblTotalPagar.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 20));
		lblTotalPagar.setBounds(222, 162, 116, 27);
		frmCobrar.getContentPane().add(lblTotalPagar);
		
		lblTitulo = new JLabel("TOTAL A PAGAR");
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 15));
		lblTitulo.setBounds(180, 130, 158, 33);
		frmCobrar.getContentPane().add(lblTitulo);
		
		tglEfectivo.setSelected(true);
		
		lblCambioTitulo = new JLabel("CAMBIO");
		lblCambioTitulo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblCambioTitulo.setBounds(217, 237, 163, 47);
		frmCobrar.getContentPane().add(lblCambioTitulo);
	}
	
	public ImageIcon redimensionar(int w, int h, String ruta) {
		ImageIcon icono = new ImageIcon(PantallaProductos.class.getResource(ruta));
		Image imagenOriginal = icono.getImage();
		Image imagenEscalada = imagenOriginal.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionado = new ImageIcon(imagenEscalada);
		return iconRedimensionado;
	}


	public void setTotal(double total) {
		this.total=total;
		
	}
	
	public static String aMoneda(double cantidad) {
		Locale localeMexico = new Locale("es", "MX");
		NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(localeMexico);
		return formatoMoneda.format(cantidad);
	}
	
	public void mensaje(String msj, String titulo) {
		JOptionPane.showMessageDialog(null, msj,titulo, 
				JOptionPane.QUESTION_MESSAGE, redimensionar(32, 32, "/img/cecylogo.png"));
	}
	
}
