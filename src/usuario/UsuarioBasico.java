package usuario;

import java.time.LocalDate;


public class UsuarioBasico extends TipoUsuario {
	public void cambiarCategoria(Usuario usuario, LocalDate fecha) {
		if (cumpleRequisitosParaSerExperto(usuario, fecha)) {
			usuario.setTipo(new UsuarioExperto());
		}
	}
}

