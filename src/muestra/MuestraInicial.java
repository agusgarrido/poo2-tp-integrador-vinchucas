package muestra;

import opinion.TipoOpinion;
import usuario.Usuario;


public class MuestraInicial extends EstadoMuestra {

	@Override
	public void puedeOpinar(Muestra muestra, Usuario usuario) {
		super.puedeOpinar(muestra, usuario);
	}

	@Override
	public void evaluarTransicion(Muestra muestra) {
		 if (muestra.tieneOpinionDeExperto()) {
	            muestra.setEstadoMuestra(new MuestraConOpinionExperta());
	        }
	}

	@Override
	//Este metodo se puede arreglar...
    public TipoOpinion resultadoActual(Muestra muestra) {
        TipoOpinion tipoMasFrecuente = TipoOpinion.NO_DEFINIDO;
        int max = 0;
        boolean empate = false;

        for (TipoOpinion tipo : TipoOpinion.values()) {
            if (tipo == TipoOpinion.NO_DEFINIDO) continue;

            int count = (int) muestra.getOpiniones().stream()
                .filter(op -> op.getTipoOpinion().equals(tipo))
                .count();

            if (count > max) {
                max = count;
                tipoMasFrecuente = tipo;
                empate = false;
            } else if (count == max && count > 0) {
                empate = true;
            }
        }

        return empate ? TipoOpinion.NO_DEFINIDO : tipoMasFrecuente;
    }

}
