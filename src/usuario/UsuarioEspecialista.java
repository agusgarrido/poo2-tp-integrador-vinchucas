package usuario;

import java.time.LocalDate;

public class UsuarioEspecialista extends UsuarioExperto {

	@Override
	public void cambiarCategoria(Usuario usuario, LocalDate fecha) {
		throw new UnsupportedOperationException("Un usuario especialista no puede cambiar de categoría.");
	}
}
