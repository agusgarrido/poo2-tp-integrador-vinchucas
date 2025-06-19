package usuario;

import java.time.LocalDate;

import sistema.Sistema;

public class UsuarioExperto extends TipoUsuario {
	public void cambiarCategoria(Usuario usuario, LocalDate fecha, Sistema sistema) {
		if (!cumpleRequisitosParaSerExperto(usuario, fecha, sistema)) {
			usuario.setTipo(new UsuarioBasico());
		}
	}
	
	@Override
	public boolean esExperto() { return true; }
}
