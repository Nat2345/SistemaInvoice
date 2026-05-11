package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Entidades.DetalleVentas;
import Entidades.Productos;
import conexion.Conexion;

public class DataDetalleVentas {
	Conexion c = new Conexion();

	public boolean insertarProducto(DetalleVentas dv) {
		PreparedStatement ps= null, ps2=null;
		Connection con = c.conectar();
		try {
			ps=con.prepareStatement("INSERT INTO detalle_ventas VALUES(null,?,?,?)");
			ps.setInt(1, dv.getIdVenta());
			ps.setInt(2, dv.getIdProducto());
			ps.setDouble(3, dv.getCantidad());
			ps2=con.prepareStatement("UPDATE productos SET stock=stock-? WHERE id_producto=?");
			ps2.setInt(1, dv.getCantidad());
			ps2.setInt(2, dv.getIdProducto());
			ps2.executeUpdate();
			return ps.executeUpdate()>0;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
