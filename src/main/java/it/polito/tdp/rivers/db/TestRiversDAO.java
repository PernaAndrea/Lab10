package it.polito.tdp.rivers.db;

import java.util.ArrayList;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

public class TestRiversDAO {

	public static void main(String[] args) {
		RiversDAO dao = new RiversDAO();
		System.out.println(dao.getAllRivers());
	 
		ArrayList<River> fiu = new ArrayList<>(dao.getAllRivers());
		for(River r : dao.getAllRivers()) {
		
			dao.setAllRiversFlows(r);
			dao.addAllRiversFlows(r);
			System.out.println(r.getFlowAvg()+"  "+r.getId());
			for(Flow f : r.flows) {
			//	System.out.println(f.getDay());
			}
		}
	}

}
