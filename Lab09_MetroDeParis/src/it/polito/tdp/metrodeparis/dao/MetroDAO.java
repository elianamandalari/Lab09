package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

import com.javadocmd.simplelatlng.LatLng;
import com.sun.javafx.collections.MappingChange.Map;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.FermataPairs;
import it.polito.tdp.metrodeparis.model.Linea;

public class MetroDAO {

	public List<Fermata> getAllFermate(HashMap<Integer, Fermata> mappaFermate) {

		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC";
		List<Fermata> fermate = new ArrayList<Fermata>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata f = new Fermata(rs.getInt("id_Fermata"), rs.getString("nome"),
						new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")));
				fermate.add(f);
				mappaFermate.put(f.getIdFermata(), f);

			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return fermate;
	}

	public HashMap<Integer, Linea> getLinee() {
		final String sql = "SELECT * FROM linea ";

		HashMap<Integer, Linea> linee = new HashMap<Integer, Linea>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Linea l = new Linea(rs.getInt("id_linea"), rs.getString("nome"), rs.getDouble("velocita"),
						rs.getDouble("intervallo"), rs.getString("colore"));
				linee.put(l.getId_linea(), l);

			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return linee;
	}

	public List<FermataPairs> listCoppieFermateAdiacenti() {
		final String sql = "SELECT f1.id_fermata AS idF1 ,f2.id_fermata AS idF2,id_linea "
				+ "FROM fermata f1,fermata f2,connessione c "
				+ "WHERE  f1.id_fermata=c.id_stazP AND f2.id_fermata=c.id_stazA  ";

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			List<FermataPairs> list = new ArrayList<>();

			while (res.next()) {
				list.add(new FermataPairs(res.getInt("idF1"), res.getInt("idF2"), res.getInt("id_linea")));
			}

			res.close();
			conn.close();

			return list;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

	}

}
