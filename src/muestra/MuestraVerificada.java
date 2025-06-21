package muestra;

import opinion.TipoOpinion;
import usuario.Usuario;

public class MuestraVerificada extends EstadoMuestra {

	private TipoOpinion resultadoFinal;

	public MuestraVerificada(TipoOpinion tipo) {
		this.resultadoFinal = tipo;
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
