package zonaDeCobertura;

import muestra.Muestra;
import organizacion.Organizacion;

public interface ZonaDeCoberturaSubject {
	public void registrarOrganizacion(Organizacion organizacion);

	public void removerOrganizacion(Organizacion organizacion);

	public void notificarNuevaMuestra(Muestra muestra);

	public void notificarMuestraValidada(Muestra muestra);
}
