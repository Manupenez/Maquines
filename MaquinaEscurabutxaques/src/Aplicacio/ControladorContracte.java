package Aplicacio;

import java.util.Date;
import java.util.LinkedList;

import Domini.Contracte;
import Domini.Maquina;
import Persistencia.ContracteBBDD;
import Persistencia.ComercBBDD;
import Persistencia.LineaContracteBBDD;
import Persistencia.MaquinaBBDD;
//COMENTARIO JONNHY
public class ControladorContracte {

	private Contracte contracte;
	private ContracteBBDD contracteBBDD;
	private ComercBBDD comercBBDD;
	private MaquinaBBDD maquinaBBDD;
	private LineaContracteBBDD lineaContracteBBDD;
	
	public ControladorContracte() throws Exception{
		contracteBBDD = new ContracteBBDD();
		comercBBDD = new ComercBBDD();
		maquinaBBDD = new MaquinaBBDD();
		lineaContracteBBDD = new LineaContracteBBDD();
	}	
	
	public void aconseguirContracte(String idComerc) throws Exception{
		try{
			this.contracte=this.contracteBBDD.recuperarContracte(idComerc);
		}catch(Exception e){
			throw new Exception("Error aconseguirContracte - "+e.getMessage());
		}
	}
	
	public void modificarContracte(String idComerc, String info) throws Exception{
		try{
			this.aconseguirContracte(idComerc);
			this.contracte.setInformacio(info);
			this.contracteBBDD.guardarContracteModificat(contracte, idComerc);
		}catch(Exception e){
			throw new Exception("Error modificarContracte - "+e.getMessage());
		}
	}
	//Per implementar
	public void baixaContracte(String idComerc) throws Exception{
		try{
			
		}catch(Exception e){
			throw new Exception("Error baixaContracte - "+e.getMessage());
		}
	}
	
	public LinkedList<Integer> comercSenseContracte() throws Exception{
		try {
			return comercBBDD.senseContracte();
		} catch (Exception e) {
			throw new Exception("Error comercSenseContracte - " + e.getMessage());
		}		
	}
	
	public LinkedList<Integer> obtenirMaquinesLlestes() throws Exception {
		try {
			return this.maquinaBBDD.obtenirMaquines("LLESTA");
		} catch (Exception e) {
			throw new Exception("Error obtenirMaquinesLlestes - "
					+ e.getMessage());
		}
	}
	
	public void nouContracte(int idComerc, String info, LinkedList<Integer> idmaquines) throws Exception{
		try{
			Contracte contracte = new Contracte(info,new Date());//No se agafar la data. 
			//capa persistencia te sql date, tots els altres util date, cuan la persistencia reb una data rep un sql date,
			//el transforma en un long(Amb un m��tode propi que te). El util date el canvies a sql amb el preparedStatement.getDate(utildate)
			//
			//
			contracteBBDD.inserirContracte(idComerc, contracte);
			for (Integer m : idmaquines) {
				Maquina maquina =maquinaBBDD.recuperarMaquina(m);
				maquina.setEstatEnUnComerc();
				maquinaBBDD.modificarEstat(maquina);
				lineaContracteBBDD.inserirlineaContracte(contracte, maquina);
			}
		}catch(Exception e){
			throw new Exception("Error nouContracte - "+e.getMessage());
		}
	}
	
	
	
	
}
