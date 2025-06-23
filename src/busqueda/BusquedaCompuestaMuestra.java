package busqueda;

import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;

public abstract class BusquedaCompuestaMuestra extends BusquedaMuestra {

	protected List<BusquedaMuestra> listaBusquedas;

	public BusquedaCompuestaMuestra() {
		this.listaBusquedas = new ArrayList<BusquedaMuestra>();		
	}
	
	@Override
	public List<Muestra> buscarMuestras(List<Muestra> muestras){
		List<Muestra> resultado = this.casoBase(muestras);
		for(BusquedaMuestra busqueda: this.listaBusquedas) {
			resultado = this.formatoBusqueda(resultado,busqueda.buscarMuestras(muestras));
		}		
		return resultado;
	}
	
	protected abstract List<Muestra> casoBase(List<Muestra> muestras);
	
	protected abstract List<Muestra> listaInicioFormato(List<Muestra> muestras);

	public void addBusqueda(BusquedaMuestra busqueda) {
		this.listaBusquedas.add(busqueda);
	}
	
	public void removeBusquea(BusquedaMuestra busqueda) {
		this.listaBusquedas.remove(busqueda);
	}
	
	private List<Muestra> formatoBusqueda(List<Muestra> listaUno, List<Muestra> listaDos){
		List<Muestra> resultado = this.listaInicioFormato(listaUno);
		for(Muestra muestra:listaDos) {
			if(this.condicionFormato(listaUno,muestra)) {
				resultado.add(muestra);
			}
		}		
		return resultado;
	}

	protected abstract boolean condicionFormato(List<Muestra> lista, Muestra elemento);
	
	@Override
	protected boolean aplicaBusqueda(Muestra muestra) {
		return true;
	}
}
