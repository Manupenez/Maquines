package Persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Domini.Reparacio;

public class ReparacioBBDD {

	private ConnexioBBDD connexio;

	public ReparacioBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
	}

	public void tancarConnexio() throws Exception {
		connexio.close();
	}

	public Reparacio recuperarReparacio(int id) throws Exception {
		PreparedStatement pst = connexio
				.prepareStatement("SELECT data, estat, idTecnic, idMaquina FROM Reparacio WHERE idReparacio = ?");
		pst.clearParameters();
		pst.setInt(1, id);
		ResultSet rs;
		rs = pst.executeQuery();
		if (rs.next()) {
			return new Reparacio(rs.getDate("data"), rs.getString("estat"), id,
					rs.getInt("idTecnic"), rs.getInt("idMaquina"));
		} else {
			throw new Exception("Error no Existeix la Reparació");
		}
	}

	public void modificarReparacio(Reparacio reparacio)
			throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("UPDATE Reparacio SET estat = ? WHERE idReparacio = ?");
			pst.clearParameters();
			pst.setString(1, reparacio.getEstat());
			pst.setInt(2, reparacio.getIdReparacio());
		} catch (Exception e) {
			throw new Exception("Error modificarReparacio - " + e.getMessage());
		}
	}

	public void inserirReparacio(int idMaquina, Reparacio reparacio)
			throws Exception {
		try {
			String sql = "INSERT INTO Reparacio(data, estatReparacio, idReparacio, idTecnic, idMaquina) VALUES(?,?,?,?,?)";
			PreparedStatement pst = connexio.prepareStatement(sql);
			PreparedStatement pst2 = connexio
					.prepareStatement("SELECT S_CONTRACTE.NEXTVAL FROM DUAL");
			pst2.clearParameters();
			ResultSet rs = pst2.executeQuery();
			rs.next();
			int retorn = rs.getInt(1);
			reparacio.setIdReparacio(retorn);
			long data = reparacio.getData().getTime();
			pst.setDate(1, new Date(data));
			pst.setString(2, reparacio.getEstat());
			pst.setInt(3, retorn);
			pst.setInt(4, reparacio.getIdTecnic());
			pst.setInt(5, idMaquina);
			if (pst.executeUpdate() != 1) {
				throw new Exception("Reparació inserida incorrectament");
			}
		} catch (Exception e) {
			throw new Exception("Error inserirReparació - " + e.getMessage());
		}
	}

}
