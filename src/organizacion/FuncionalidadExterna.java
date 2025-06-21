package organizacion;

import muestra.Muestra;
import zonaDeCobertura.ZonaDeCobertura;

public interface FuncionalidadExterna {
	public void nuevoEvento(Organizacion organizacion, ZonaDeCobertura zona, Muestra muestra);
}
