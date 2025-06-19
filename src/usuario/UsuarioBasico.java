package usuario;

import java.time.LocalDate;

import sistema.Sistema;

public class UsuarioBasico extends TipoUsuario {
	public void cambiarCategoria(Usuario usuario, LocalDate fecha, Sistema sistema) {
		if (cumpleRequisitosParaSerExperto(usuario, fecha, sistema)) {
			usuario.setTipo(new UsuarioExperto());
		}
	}
}
