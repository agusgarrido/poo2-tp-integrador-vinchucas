package organizacion;

import muestra.Muestra;
import zonaDeCobertura.ZonaDeCobertura;

public interface OrganizacionObserver {
	public void muestraValidada(ZonaDeCobertura zona, Muestra muestra);
	public void nuevaMuestra(ZonaDeCobertura zona, Muestra muestra);
}
