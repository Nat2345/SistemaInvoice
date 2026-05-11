package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entidades.Productos;
import Entidades.Ventas;
import conexion.Conexion;

public class DataVentas {
	Conexion c = new Conexion();

	public int insertarVenta(Ventas v) {
		Connection con = c.conectar();

		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO ventas VALUES(null,null,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, v.getCliente());
			ps.setDouble(2, v.getTotal());
			ps.setString(3, v.getMetodo());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);

			}

		} catch (SQLException e) {

		}

		return 0;
	}
}