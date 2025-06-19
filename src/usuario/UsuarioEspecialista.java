package usuario;

import java.time.LocalDate;

import sistema.Sistema;

public class UsuarioEspecialista extends UsuarioExperto{
	
	@Override
	public void cambiarCategoria(Usuario usuario, LocalDate fecha, Sistema sistema) {
		throw new UnsupportedOperationException("Un usuario especialista no puede cambiar de categoría.");
	}
}