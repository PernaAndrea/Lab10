package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {

	private RiversDAO dao ;
	private ArrayList<River> fiumi;
	
	public Model() {
		
		dao = new RiversDAO();
		fiumi = new ArrayList<>(dao.getAllRivers());
	}
	
	public List<River> getAllRivers() {
		return fiumi;
	}
	
	public void setRiverFlows() {
		for(River r : fiumi ) {
			dao.addAllRiversFlows(r);
		}
	}
	
	public void setRiverAVGFlow() {
		for(River r : fiumi ) {
			dao.setAllRiversFlows(r);
		}
	}

	
	public LocalDate getStartDate(River r) {
		return r.flows.get(0).getDay();
	}
	
	public LocalDate getEndDate(River r) {
		return r.flows.get(r.flows.size()-1).getDay();
	}

	public int getNMeasurements(River value) {
		// TODO Auto-generated method stub
		return value.flows.size();
	}

	public double getFmedia(River value) {
		// TODO Auto-generated method stub
		return value.getFlowAvg();
	}
}
