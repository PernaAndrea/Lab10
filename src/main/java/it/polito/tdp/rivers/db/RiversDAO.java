package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public void setAllRiversFlows(River r) {
			
			final String sql = "SELECT r.id, AVG(f.flow) AS media "
					+ "FROM river r, flow f "
					+ "WHERE r.id=f.river AND r.id=? "
					+ "GROUP BY r.id";
	
			try {
				Connection conn = DBConnect.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, r.getId());
				ResultSet res = st.executeQuery();
	
				res.next();
					r.setFlowAvg(res.getDouble("media"));
				
				
				conn.close();
				
			} catch (SQLException e) {
				//e.printStackTrace();
				throw new RuntimeException("SQL Error");
			}

		}
	
	public void addAllRiversFlows(River r) {
		
		final String sql = "SELECT f.id, f.river, f.day, f.flow "
				+ "FROM  flow f "
				+ "WHERE f.river = ? "
				+ "ORDER BY f.river, f.day";
		
		ArrayList<Flow> flussi =new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				flussi.add(new Flow(res.getDate("f.day").toLocalDate(),res.getDouble("f.flow"),r));
				}
				r.setFlows(flussi);
				

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

	}
}
