package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class ComercBBDD {

	private ConnexioBBDD connexio;

	public ComercBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
	}

	public void tancarConnexio() throws Exception {
		connexio.close();
	}

	public LinkedList<Integer> senseContracte() throws Exception {
		try {
			LinkedList<Integer> noContracte = new LinkedList<Integer>();
			String sql = "SELECT c.idComerc FROM Comerc c join contracte co on (c.idcomerc = co.idcomerc) GROUP BY c.idComerc HAVING count(*) = ?";
			PreparedStatement pstm;
			ResultSet rs;
			pstm = connexio.prepareStatement(sql);
			pstm.clearParameters();
			pstm.setInt(1, 0);
			rs = pstm.executeQuery();
			while (rs.next()) {
				noContracte.add(rs.getInt("idComerc"));
			}
			return noContracte;
		} catch (Exception e) {
			throw new Exception("Error senseContracte - " + e.getMessage());
		}
	}

}
