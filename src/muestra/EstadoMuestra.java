package muestra;

import opinion.TipoOpinion;
import usuario.Usuario;

public abstract class EstadoMuestra {
	
	public void puedeOpinar(Muestra muestra, Usuario usuario){
		if(muestra.getCreated_by().equals(usuario) || 
		   muestra.getOpiniones().stream().anyMatch(op -> op.getUsuario().equals(usuario))){
			throw new IllegalArgumentException ("El usuario no puede opinar sobre esta muestra, ya lo hizo"); 	
		}
	}
	
	public abstract void evaluarTransicion(Muestra muestra);
	
	public abstract TipoOpinion resultadoActual(Muestra muestra);
	public TipoEstadoMuestra nivelDeValidacion() {
		return TipoEstadoMuestra.VOTADA;
	}
				
}
