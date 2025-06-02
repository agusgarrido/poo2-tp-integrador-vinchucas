package organizacion;

import java.util.ArrayList;
import java.util.List;

import zonaDeCobertura.ZonaDeCobertura;

public class Organizacion{
	private Ubicacion ubicacion;
	private String tipoOrganizacion;
	private int cantidadEmpleados;
	
	private List<ZonaDeCobertura> zonasRegistradas = new ArrayList<ZonaDeCobertura>();
	
	public Organizacion(Ubicacion ubicacion, String tipoOrganizacion, int cantidadEmpleados) {
		this.ubicacion = ubicacion;
		this.tipoOrganizacion = tipoOrganizacion;
		this.cantidadEmpleados = cantidadEmpleados;
	}
	
	/*TODO: Implementar el uso de las funcionalidades externas (Aviso a las organizaciones)
	 NOTA: ¿FuncionalidadExterna es el observer?
	 */
	
	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}
	
	public String getTipoOrganizacion() {
		return this.tipoOrganizacion;
	}
	
	public int getCantidadEmpleados() {
		return this.cantidadEmpleados;
	}
	
	/* ¿Debo hacer este registro? */
	public void registrarseEnZona(ZonaDeCobertura zona) {
		zona.registrarOrganizacion(this);
		this.zonasRegistradas.add(zona);
	}
	
	public void darseDeBajaEnZona(ZonaDeCobertura zona) {
		zona.removerOrganizacion(this);
		this.zonasRegistradas.remove(zona);
	}
}
