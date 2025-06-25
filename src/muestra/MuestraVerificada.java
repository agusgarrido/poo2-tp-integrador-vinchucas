package muestra;

import opinion.TipoOpinion;
import usuario.Usuario;

public class MuestraVerificada extends EstadoMuestra {

	private TipoOpinion resultadoFinal;
	

	public MuestraVerificada(TipoOpinion tipo) {
		super();
		this.resultadoFinal = tipo;
		this.nivelDeValidacion = TipoEstadoMuestra.VERIFICADA;
	}

	@Override
	public TipoOpinion resultadoActual(Muestra muestra) {
		return resultadoFinal;
	}

	@Override
	public void puedeOpinar(Muestra muestra, Usuario usuario) {
		throw new IllegalArgumentException("La muestra ya está verificada, no se aceptan más opiniones");
	}

	@Override
	public void evaluarTransicion(Muestra muestra) {
		// no hace nada

	}

	@Override
	public TipoEstadoMuestra nivelDeValidacion() {
		return TipoEstadoMuestra.VERIFICADA;
	}

}
