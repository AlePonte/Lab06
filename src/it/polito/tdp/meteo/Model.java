package it.polito.tdp.meteo;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


import it.polito.tdp.meteo.bean.Rilevamento;
import it.polito.tdp.meteo.bean.SimpleCity;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {

	private MeteoDAO meteoDao = new MeteoDAO();
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;

	public Model() {

	}

	public String getUmiditaMedia(int mese) {
		
		
		List<Rilevamento> rilevamenti = meteoDao.getAllRilevamenti();
		List<Rilevamento> rilevamentiMese = new LinkedList<Rilevamento>();
		List<Rilevamento> rilevamentiGenova = new LinkedList<Rilevamento>();
		List<Rilevamento> rilevamentiTorino = new LinkedList<Rilevamento>();
		List<Rilevamento> rilevamentiMilano = new LinkedList<Rilevamento>();
		
		
		
		//creo una lista solo con quelli del mese corretto
		for(Rilevamento r : rilevamenti) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(r.getData());
			int month = cal.get(Calendar.MONTH);
			if(month==mese) {
				rilevamentiMese.add(r);
			}
		}
		
		for(Rilevamento r : rilevamentiMese) {
			if(r.getLocalita().compareTo("Genova")==0) {
				rilevamentiGenova.add(r);
			}else {
				if(r.getLocalita().compareTo("Milano")==0) {
					rilevamentiMilano.add(r);
				}else {
					rilevamentiTorino.add(r);
				}
			}
		}
		//calcolo la media delle tre liste 
		double umiditaGenova=0.0;
		double umiditaTorino=0.0;
		double umiditaMilano=0.0;
		
		for (Rilevamento r : rilevamentiGenova) {
			umiditaGenova=umiditaGenova+r.getUmidita();
		}
		for (Rilevamento r : rilevamentiTorino) {
			umiditaTorino=umiditaTorino+r.getUmidita();
		}
		for (Rilevamento r : rilevamentiMilano) {
			umiditaMilano=umiditaMilano+r.getUmidita();
		}
		
		umiditaMilano=umiditaMilano/rilevamentiMilano.size();
		umiditaGenova=umiditaGenova/rilevamentiGenova.size();
		umiditaTorino=umiditaTorino/rilevamentiTorino.size();
		//new DecimalFormat("##.##").format(i2)
		String mediaTorino=new DecimalFormat("##.##").format(umiditaTorino);
		String mediaGenova=new DecimalFormat("##.##").format(umiditaGenova);
		String mediaMilano=new DecimalFormat("##.##").format(umiditaMilano);
		
		
		
		String risultato = "L'umidita media nelle varie citta nel periodo "+mese+"/2013 è stata : "+"\n"+"Genova "+mediaGenova+"%\n"+"Torino "+mediaTorino+"%\n"+"Milano "+mediaMilano+"%\n";
		
		
		return risultato;
	}

	public String trovaSequenza(int mese) {

		return "TODO!";
	}

	private Double punteggioSoluzione(List<SimpleCity> soluzioneCandidata) {

		double score = 0.0;
		return score;
	}

	private boolean controllaParziale(List<SimpleCity> parziale) {

		return true;
	}

}
