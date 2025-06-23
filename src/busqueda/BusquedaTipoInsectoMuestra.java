package busqueda;

import muestra.Muestra;
import opinion.TipoOpinion;

public class BusquedaTipoInsectoMuestra extends BusquedaMuestra {
	private TipoOpinion tipoOpinionB;

	public BusquedaTipoInsectoMuestra(TipoOpinion tipoOpinion) {
		super();
		this.tipoOpinionB = tipoOpinion;
	}
	
	@Override 
	protected boolean aplicaBusqueda(Muestra muestra) {
		return muestra.resultadoActual().equals(tipoOpinionB);
	}
}
