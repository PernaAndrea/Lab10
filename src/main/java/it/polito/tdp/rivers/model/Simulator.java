package it.polito.tdp.rivers.model;

import java.time.LocalTime;
import java.util.Date;

public class Simulator {
	
	//CODA EVENTI
//	private PriorityQueue<Event> queue ;
	//MODELLO DEL MONDO
	double Fin;
	double Fout;
	
	//PARAMENTRI INPUT
	River input ;
	double k;
	double Q;
	double C;
	double FoutMin;
	
	//long inizio = Date.UTC(2000,1, 1, 0, 0, 0);
	//long fine = Date.UTC(2000,1, 1, 0, 0, 0);
	
	//PARAMENTRI OUTPUT
	int GiorniSbagliati ;
	double Cmed;
	
	//INIZIALIZZO IL SIMULATORE
	public void init(River r,double k) {
		input = r;
		this.k = k ;
		Q = 30*86400*r.getFlowAvg()*k;
		C = Q/2;
		FoutMin = r.getFlowAvg()*0.8*86400;
		GiorniSbagliati = 0;
		Cmed = 0;
	}

	//ESEGUO LA SIMULAZIONE
	public void run() {
		
		double p ;
				
		for(Flow f : input.getFlows()) {
			p= Math.random();
			if(p>0.05) {
				Fout = 0.8*input.getFlowAvg()*86400;
			}else {
				Fout = 10*FoutMin;
			}
			Fin = f.getFlow()*86400;
			if(Fin>Fout) {
				C += Fin-Fout;
				if(C>Q) {
					C = Q;
				}
			}else {
				C += Fin-Fout;
				if(C<0) {
					C = 0; 
					GiorniSbagliati ++;
				}
			}
			Cmed += C;
		}
		Cmed /= input.getFlows().size();
		
	}

	public int getGiorniSbagliati() {
		return GiorniSbagliati;
	}

	public double getCmed() {
		return Cmed;
	}
	
	
	
}
