package usuario;

import java.time.LocalDate;

public abstract class TipoUsuario {
	public abstract void cambiarCategoria(Usuario usuario, LocalDate fecha);
	public boolean cumpleRequisitosParaSerExperto(Usuario usuario, LocalDate fecha) {
		return usuario.cantidadMuestrasEnviadas() > 10 && usuario.cantidadOpinionesEnviadas(fecha) > 20;
	}
}