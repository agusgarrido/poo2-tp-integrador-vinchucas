package sistemaWeb;

import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;
 

public class SistemaWeb {
	
	private List<Muestra> muestras;
	
	public SistemaWeb() {
		this.muestras=new ArrayList<Muestra>();
	}


	public void addMuestra(Muestra muestra) {
		this.muestras.add(muestra);
		
	}

	public List<Muestra> getMuestras() {
		
		return muestras;
	}


}
