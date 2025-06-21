package usuario;

import java.time.LocalDate;

public abstract class TipoUsuario {

	/* ¿Cambia solo? ¿Lo envía alguien? */
	public abstract void cambiarCategoria(Usuario usuario, LocalDate fecha);

	public boolean cumpleRequisitosParaSerExperto(Usuario usuario, LocalDate fecha) {
		return usuario.cantidadMuestrasEnviadas() > 10 && usuario.cantidadOpinionesEnviadas(fecha) > 20;
	}

	public boolean esExperto() {
		return false;
	}
}
