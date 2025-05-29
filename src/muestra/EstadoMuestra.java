package muestra;

import opinion.TipoOpinion;
import usuario.Usuario;

public interface EstadoMuestra {
	
	public void puedeOpinar(Usuario usuario);
	
	public void evaluarTransicion(Muestra muestra);
	
	public TipoOpinion resultadoActual(Muestra muestra);
	
}
