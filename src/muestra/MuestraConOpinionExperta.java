package muestra;

import opinion.TipoOpinion;
import usuario.Usuario;

public class MuestraConOpinionExperta implements EstadoMuestra {

	@Override
	public void puedeOpinar(Usuario user) {
	    if (!user.esExperto()) { // Hecho!
	        throw new IllegalArgumentException("El usuario no es experto. No puede opinar");
	    }
	}

	@Override
	public void evaluarTransicion(Muestra muestra) {
	    muestra.tipoOpinionVerificada()
	           .ifPresent(tipo -> muestra.setEstadoMuestra(new MuestraVerificada(tipo)));
	}

	@Override
    public TipoOpinion resultadoActual(Muestra muestra) {
		/* Leila agregó getTipoOpinion */
        return muestra.getUltimaOpinion().getTipo();
    }

}
