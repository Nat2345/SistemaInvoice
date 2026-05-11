package Entidades;

import Data.DataVentas;

public class Ventas {
int idVenta;
String fecha, cliente, metodo;
double total;
DataVentas dv = new DataVentas();
public Ventas(int idVenta, String fecha, String cliente, String metodo, double total) {
	super();
	this.idVenta = idVenta;
	this.fecha = fecha;
	this.cliente = cliente;
	this.metodo = metodo;
	this.total = total;
}

public Ventas(String cliente, String metodo, double total) {
	super();
	this.cliente = cliente;
	this.metodo = metodo;
	this.total = total;
}

public int getIdVenta() {
	return idVenta;
}
public void setIdVenta(int idVenta) {
	this.idVenta = idVenta;
}
public String getFecha() {
	return fecha;
}
public void setFecha(String fecha) {
	this.fecha = fecha;
}
public String getCliente() {
	return cliente;
}
public void setCliente(String cliente) {
	this.cliente = cliente;
}
public String getMetodo() {
	return metodo;
}
public void setMetodo(String metodo) {
	this.metodo = metodo;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}
 public int insertarVenta() {
	 return dv.insertarVenta(this);
 }

}
