package usuario;

import java.time.LocalDate;

public class UsuarioExperto extends TipoUsuario {
	public void cambiarCategoria(Usuario usuario, LocalDate fecha) {
		if (!cumpleRequisitosParaSerExperto(usuario, fecha)) {
			usuario.setTipo(new UsuarioBasico());
		}
	}
	
	@Override
	public boolean esExperto() { return true; }
}
