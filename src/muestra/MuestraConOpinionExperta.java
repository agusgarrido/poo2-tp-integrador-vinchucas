package muestra;

import opinion.TipoOpinion;
import usuario.Usuario;

public class MuestraConOpinionExperta extends EstadoMuestra {

	@Override
	public void puedeOpinar(Muestra muestra, Usuario user) {
		super.puedeOpinar(muestra, user);
		if (!user.esExperto()) {
			throw new IllegalArgumentException("El usuario no es experto. No puede opinar");
		}
	}

	@Override
	public void evaluarTransicion(Muestra muestra) {
		muestra.tipoOpinionMasVotada().ifPresent(tipo -> muestra.setEstadoMuestra(new MuestraVerificada(tipo)));
	}

	@Override
	public TipoOpinion resultadoActual(Muestra muestra) {
		return muestra.getUltimaOpinion().getTipoOpinion();
	}

}
