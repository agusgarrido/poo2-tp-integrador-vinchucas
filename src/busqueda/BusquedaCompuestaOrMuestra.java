package busqueda;

import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;

public class BusquedaCompuestaOrMuestra extends BusquedaCompuestaMuestra {

	public BusquedaCompuestaOrMuestra() {
		super();
	}

	@Override
	protected boolean condicionFormato(List<Muestra> lista, Muestra elemento) {
		return !lista.contains(elemento);
	}

	@Override
	protected List<Muestra> casoBase(List<Muestra> muestras) {
		return new ArrayList<Muestra>();
	}
	@Override 
	protected List<Muestra> listaInicioFormato(List<Muestra> muestras){
		return muestras;
	}	
}