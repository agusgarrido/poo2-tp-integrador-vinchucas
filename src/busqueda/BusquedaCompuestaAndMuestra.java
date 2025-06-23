package busqueda;

import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;

public class BusquedaCompuestaAndMuestra extends BusquedaCompuestaMuestra {

	public BusquedaCompuestaAndMuestra() {
		super();
	}

	@Override
	protected boolean condicionFormato(List<Muestra> lista, Muestra elemento) {
		return lista.contains(elemento);
	}

	@Override
	protected List<Muestra> casoBase(List<Muestra> muestras) {
		return muestras;
	}
	
	@Override
	protected List<Muestra> listaInicioFormato(List<Muestra> muestras) {
	    return new ArrayList<Muestra>();
	}
	
}