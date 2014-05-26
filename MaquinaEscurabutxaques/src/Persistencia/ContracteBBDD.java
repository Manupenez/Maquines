package Persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Domini.Contracte;
import Domini.Maquina;

public class ContracteBBDD {

	private ConnexioBBDD connexio;

	public ContracteBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
	}

	public void tancarConnexio() throws Exception {
		connexio.close();
	}

	public Contracte recuperarContracte(String idComerc) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("SELECT infocontracte, dataalta, databaixa, idcontracte FROM Contracte WHERE idComerc = ?");
			pst.clearParameters();
			pst.setString(1, idComerc);
			ResultSet rs = pst.executeQuery();
			return new Contracte(rs.getString("infocontracte"),
					rs.getDate("dataalta"), rs.getDate("databaixa"),
					rs.getInt("idContracte"));
				//si no funciona fem un getDate passat a long i del long fem el new Date del util
		} catch (Exception e) {
			throw new Exception("Error agafarContracte - " + e.getMessage());
		}
	}

	public void guardarContracteModificat(Contracte contracte, String idComerc)
			throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("UPDATE Contracte SET informacio = ? WHERE idComerc = ?");
			pst.clearParameters();
			pst.setString(1, contracte.getInformacio());
			pst.setString(2, idComerc);
		} catch (Exception e) {
			throw new Exception("Error guardarContracteModificat - "
					+ e.getMessage());
		}

	}

	public void inserirContracte(int idComerc, Contracte contracte)
			throws Exception {
		try {
			String sql = "INSERT INTO Contracte(idcontracte, infocontracte, dataalta, idcomerc) VALUES(?,?,?,?)";
			PreparedStatement pst = connexio.prepareStatement(sql);
			PreparedStatement pst2 = connexio
					.prepareStatement("SELECT S_CONTRACTE.NEXTVAL FROM DUAL");
			pst2.clearParameters();
			ResultSet rs = pst2.executeQuery();
			rs.next();
			int retorn = rs.getInt(1);
			contracte.setId(retorn);
			pst.setInt(1, retorn);
			pst.setString(2, contracte.getInformacio());
			long data = contracte.getDataAlta().getTime();
			pst.setDate(3, new Date (data));
			pst.setInt(4, idComerc);
			if (pst.executeUpdate() != 1) {
				throw new Exception("Contracte inserit incorrectament");
			}
		} catch (Exception e) {
			throw new Exception("Error inserirContracte - " + e.getMessage());
		}
	}

	public int getIdContracte(String idComerc) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("SELECT idContracte FROM Contracte WHERE idComerc = ?");
			pst.clearParameters();
			pst.setString(1, idComerc);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt("idContracte");
			} else {
				return -1;
			}
		} catch (Exception e) {
			throw new Exception("Error getIdContracte - " + e.getMessage());
		}
	}

}
