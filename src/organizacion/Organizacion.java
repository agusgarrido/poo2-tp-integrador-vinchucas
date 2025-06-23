package organizacion;

import muestra.Muestra;
import ubicacion.Ubicacion;
import zonaDeCobertura.ZonaDeCobertura;

public class Organizacion implements OrganizacionObserver {
	private Ubicacion ubicacion;
	private String tipoOrganizacion;
	private int cantidadEmpleados;
	private FuncionalidadExterna nuevaMuestra;
	private FuncionalidadExterna muestraValidada;

	public Organizacion(Ubicacion ubicacion, String tipoOrganizacion, int cantidadEmpleados) {
		this.ubicacion = ubicacion;
		this.tipoOrganizacion = tipoOrganizacion;
		this.cantidadEmpleados = cantidadEmpleados;
	}

	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}

	public String getTipoOrganizacion() {
		return this.tipoOrganizacion;
	}

	public int getCantidadEmpleados() {
		return this.cantidadEmpleados;
	}

	/*
	 * ¿Queda acá o debo implementar algo a partir de la interfaz
	 * FuncionalidadExterna?
	 */

	// Para muestra validada
	public void setFuncionalidadMuestraValidada(FuncionalidadExterna muestraValidada) {
		this.muestraValidada = muestraValidada;
	}

	public void muestraValidada(ZonaDeCobertura zona, Muestra muestra) {
		muestraValidada.nuevoEvento(this, zona, muestra);
	}

	// Para nueva muestra
	public void setFuncionalidadNuevaMuestra(FuncionalidadExterna nuevaMuestra) {
		this.nuevaMuestra = nuevaMuestra;
	}

	public void nuevaMuestra(ZonaDeCobertura zona, Muestra muestra) {
		nuevaMuestra.nuevoEvento(this, zona, muestra);
	}

	public void registrarseEnZona(ZonaDeCobertura zona) {
		zona.registrarOrganizacion(this);
	}

	public void darseDeBajaEnZona(ZonaDeCobertura zona) {
		zona.removerOrganizacion(this);
	}
}
