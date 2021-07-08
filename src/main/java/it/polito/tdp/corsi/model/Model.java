package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.DAO;

public class Model {
	private DAO Dao;
	
	public Model() {
		Dao = new DAO();
	}
	
	public List<Corso> getCorsiByPeriodo(Integer pd){
		return Dao.ListaCorsi(pd);
	}
	
	public Map<Corso,Integer> contaIscrittiCorso(int pd) {
		return Dao.contaIscrittiCorso(pd);
	}
	
	public List<Studente> getStudentiByCorso(String corso){
		return Dao.elencoIscrittiCorso(corso);
	}

	public boolean esisteCorso(String corso) {
		return Dao.esisteCorso(new Corso(corso,0,null,0));
	}

	public Map<String,Integer> getStudentiByCorsoGroupedByCDS(String corso) {
		return Dao.elencoIscrittiCorsoRaggrupatiPerCDS(corso);
	}
}
