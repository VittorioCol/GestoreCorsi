package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class DAO {
	public List<Corso> ListaCorsi(int semestre){
		try {
			Connection conn=DBConnect.getConnection();
			
			String sql="SELECT * FROM corso WHERE pd = ?";
			List<Corso> lcorsi = new ArrayList<Corso>();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, semestre);
			ResultSet res= st.executeQuery();
			
			
			while(res.next()) {
				Corso c= new Corso(res.getString("codins"), res.getInt("crediti"), res.getString("nome"), res.getInt("pd"));
				lcorsi.add(c);
			}
			
			res.close();
			st.close();
			conn.close();
			
			return lcorsi;
		} catch (SQLException e) {
			throw new RuntimeException("Database error in readShape",e);
		}
		
	}
	public Map<Corso,Integer> contaIscrittiCorso(int pd){
		String sql= "SELECT c.codins, c.crediti, c.nome, c.pd, COUNT(*) AS tot "
					+ "FROM corso c,iscrizione i "
					+ "WHERE c.codins = i.codins AND c.pd=? "
					+ "GROUP BY  c.codins, c.crediti, c.nome, c.pd";
			Map<Corso,Integer> corsi= new HashMap<Corso,Integer>();
			
		try{
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet res= st.executeQuery();
			
			
			while(res.next()) {
				Corso c= new Corso(res.getString("codins"), res.getInt("crediti"), res.getString("nome"), res.getInt("pd"));
				Integer count= res.getInt("tot");
				corsi.put(c, count);
			}
			
			
			res.close();
			st.close();
			conn.close();
			return corsi;
		} catch (SQLException e) {
			throw new RuntimeException("Database error in countByShapes",e);
		}
	}
public List<Studente> elencoIscrittiCorso(String codins){	
	String sql= "SELECT s.matricola, s.cognome, s.nome, s.CDS "
			+ "FROM studente s,iscrizione i "
			+ "WHERE s.matricola = i.matricola AND i.codins= ?";
	List<Studente> studenti= new ArrayList<Studente>();
			try{
				Connection conn=DBConnect.getConnection();
				PreparedStatement st=conn.prepareStatement(sql);
				st.setString(1, codins);
				ResultSet res= st.executeQuery();
				
				while(res.next()) {
					Studente s= new Studente( res.getInt("matricola"),res.getString("cognome"), res.getString("nome"), res.getString("CDS"));
					studenti.add(s);
					}
				
				res.close();
				st.close();
				conn.close();
				return studenti;
				} 
			catch (SQLException e) {
				throw new RuntimeException("Database error in countByShapes",e);
				}
			}
public boolean esisteCorso(Corso corso) {
	String sql= "SELECT * FROM corso WHERE codins=?";
			try {
				Connection conn=DBConnect.getConnection();
				PreparedStatement st=conn.prepareStatement(sql);
				st.setString(1, corso.getCodins());
				ResultSet res= st.executeQuery();
				if(res.next()) {
					res.close();
					st.close();
					conn.close();
					return true;
				}else {
					res.close();
					st.close();
					conn.close();
					return false;
				}
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
}
public Map<String,Integer> elencoIscrittiCorsoRaggrupatiPerCDS(String codins) {
		String sql= "SELECT s.CDS, + COUNT(s.matricola) AS num "
				+ "FROM studente s,iscrizione i "
				+ "WHERE s.matricola = i.matricola AND i.codins= ?"
				+ "GROUP BY s.CDS";
		 Map<String,Integer> mappa= new HashMap<String,Integer>();
				try{
					Connection conn=DBConnect.getConnection();
					PreparedStatement st=conn.prepareStatement(sql);
					st.setString(1, codins);
					ResultSet res= st.executeQuery();
					
					while(res.next()) {
						mappa.put(res.getString("CDS"), res.getInt("num"));
						}
					
					res.close();
					st.close();
					conn.close();
					return mappa;
					} 
				catch (SQLException e) {
					throw new RuntimeException("Database error in countByShapes",e);
					}
				}
}
