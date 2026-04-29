package Entidades;

import Data.DataProductos;

public class Productos {
	int idProducto;
	String codigoBarras;
	String nombre;
	double precioVenta;
	int stock;
	
	DataProductos dp = new DataProductos();
	
	public Productos() {
		 
		
	}
	

	public Productos(int idProducto, String codigoBarras, String nombre, double precioVenta, int stock) {
		super();
		this.idProducto = idProducto;
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.precioVenta = precioVenta;
		this.stock = stock;
	}
	
	public Productos( String codigoBarras, String nombre, double precioVenta, int stock) {
		super();
		
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.precioVenta = precioVenta;
		this.stock = stock;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Productos [idProducto=" + idProducto + ", codigoBarras=" + codigoBarras + ", nombre=" + nombre
				+ ", precioVenta=" + precioVenta + ", stock=" + stock + "]";
	}


	public boolean insertarProducto() {
		return dp.insertarProducto(this);
	}


	public boolean eliminarProducto() {
		return dp.eliminarProducto(this);
	}


	public boolean actualizarProducto() {
		return dp.actualizarProducto(this);
	}
	
   
    
}
