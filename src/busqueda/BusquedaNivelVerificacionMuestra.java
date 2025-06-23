package busqueda;

import muestra.Muestra;
import muestra.TipoEstadoMuestra;

public class BusquedaNivelVerificacionMuestra extends BusquedaMuestra{
	private TipoEstadoMuestra estado;

	public BusquedaNivelVerificacionMuestra(TipoEstadoMuestra estado) {
		super();
		this.estado = estado;
	}
	
	@Override 
	protected boolean aplicaBusqueda(Muestra muestra) {
		return muestra.nivelDeValidacion().equals(estado);
	}
	
}