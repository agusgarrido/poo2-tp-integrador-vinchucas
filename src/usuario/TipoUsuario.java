package usuario;

import java.time.LocalDate;

import sistema.Sistema;

public abstract class TipoUsuario {
	
	/* ¿Cambia solo? ¿Lo envía alguien? */
	public abstract void cambiarCategoria(Usuario usuario, LocalDate fecha, Sistema sistema);

	public boolean cumpleRequisitosParaSerExperto(Usuario usuario, LocalDate fecha, Sistema sistema) {
		return usuario.cantidadMuestrasEnviadas(sistema) > 10 && usuario.cantidadOpinionesEnviadas(fecha) > 20;
	}
	
	public boolean esExperto() { return false; }
}