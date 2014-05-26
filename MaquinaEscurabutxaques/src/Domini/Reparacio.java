package Domini;

import java.util.Date;

public class Reparacio {
	
	private Date data;
	private String estat;
	private int idReparacio;
	private int idTecnic;
	private int idMaquina;
	
	public Reparacio(Date data, String estat, int idReparacio, int idTecnic, int idMaquina){
		this.data = data;
		this.estat = estat;
		this.idReparacio = idReparacio;
		this.idTecnic = idTecnic;
		this.idMaquina = idMaquina;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getEstat() {
		return estat;
	}

	public void setEstat(String estat) {
		this.estat = estat;
	}

	public int getIdReparacio() {
		return idReparacio;
	}

	public void setIdReparacio(int idReparacio) {
		this.idReparacio = idReparacio;
	}

	public int getIdTecnic() {
		return idTecnic;
	}

	public void setIdTecnic(int idTecnic) {
		this.idTecnic = idTecnic;
	}

	public int getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(int idMaquina) {
		this.idMaquina = idMaquina;
	}
	
	
}
