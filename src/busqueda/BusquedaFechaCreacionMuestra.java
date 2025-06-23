package busqueda;

import java.time.LocalDate;

import muestra.Muestra;

public class BusquedaFechaCreacionMuestra extends BusquedaMuestra {
	private LocalDate fechaCreacionB;
	
	public BusquedaFechaCreacionMuestra(LocalDate fechaBusqueda) {
		this.fechaCreacionB = fechaBusqueda;
	}
	
	@Override
	protected boolean aplicaBusqueda(Muestra muestra) {
		return muestra.getFechaDeCreacion().equals(fechaCreacionB);
	}

}