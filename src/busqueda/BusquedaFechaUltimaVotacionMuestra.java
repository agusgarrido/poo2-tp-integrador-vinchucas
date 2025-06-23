package busqueda;

import java.time.LocalDate;

import muestra.Muestra;

public class BusquedaFechaUltimaVotacionMuestra extends BusquedaMuestra{
	private LocalDate fechaUltimaVotacionB;

	public BusquedaFechaUltimaVotacionMuestra(LocalDate fechaUltimaVotacionB) {
		super();
		this.fechaUltimaVotacionB = fechaUltimaVotacionB;
	}
	
	@Override
	protected boolean aplicaBusqueda(Muestra muestra) {
	    LocalDate fechaUltima = muestra.getFechaUltimaVotacion();
	    return fechaUltima!= null && fechaUltima.equals(fechaUltimaVotacionB);
	}

}