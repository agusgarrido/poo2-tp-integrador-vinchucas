package busqueda;

import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;

abstract public class BusquedaMuestra {
	
	public BusquedaMuestra() {
	}

	public List<Muestra> buscarMuestras(List<Muestra> muestras) {
		List<Muestra> listaFiltrada = new ArrayList<Muestra>();
		for (Muestra m : muestras) {
			if (this.aplicaBusqueda(m)) {
				listaFiltrada.add(m); 
			}
		}
		return listaFiltrada;
	}

	 protected abstract boolean aplicaBusqueda(Muestra muestra);

}