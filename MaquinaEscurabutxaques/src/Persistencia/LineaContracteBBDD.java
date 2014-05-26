package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Domini.Contracte;
import Domini.Maquina;

public class LineaContracteBBDD {

	private ConnexioBBDD connexio;

	public LineaContracteBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
	}

	public void tancarConnexio() throws Exception {
		connexio.close();
	}

	public void inserirlineaContracte(Contracte cont, Maquina maquina)
			throws Exception {
		try {
			String sql = "INSERT INTO lineaContracte(idlinea, idcontracte, idmaquina) VALUES(?,?,?)";
			PreparedStatement pst = connexio.prepareStatement(sql);
			PreparedStatement pst2 = connexio
					.prepareStatement("SELECT S_LINEACONTRACTE.NEXTVAL FROM DUAL");
			pst2.clearParameters();
			ResultSet rs = pst2.executeQuery();
			if (rs.next()) {
				int retorn = rs.getInt(1);
				pst.setInt(1, retorn);
				pst.setInt(2, cont.getId());
				pst.setInt(3, maquina.getId());
				if (pst.executeUpdate() != 1) {
					throw new Exception(
							"lineaContracte inserida incorrectament");
				}
			}

		} catch (Exception e) {
			throw new Exception("Error inserirlineaContracte - "
					+ e.getMessage());
		}

	}
}
