package zonaDeCobertura;

import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;
import organizacion.Organizacion;

public class ZonaDeCobertura implements ZonaDeCoberturaSubject{
	private String nombre;
	private Ubicacion epicentro;
	private double radio;
	
	private List<Muestra> muestrasReportadas = new ArrayList<Muestra>();
	private List<ZonaDeCobertura> zonasSolapadas = new ArrayList<ZonaDeCobertura>();
	private List<Organizacion> organizacionesRegistradas = new ArrayList<Organizacion>();
	
	public ZonaDeCobertura(String nombre, Ubicacion epicentro, double radio) {
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.radio = radio;
	}
	
	/*TODO: Implementar Notificar 
	 * 		Definir si calculo las zonas solapadas o las almaceno
	 */
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Ubicacion getEpicentro() {
		return this.epicentro;
	}
	
	public double getRadio() {
		return this.radio;
	}
	
	/* ¿Cuál es la lógica de las zonas solapadas? ¿Debo guardarlas o solo saberlo de vez en cuando? */
	public List<ZonaDeCobertura> getZonasSolapadas(){
		return this.zonasSolapadas;
	}
	
	public List<Organizacion> getOrganizacionesRegistradas(){
		return this.organizacionesRegistradas;
	}
	
	public void registrarOrganizacion(Organizacion organizacion) {
		organizacionesRegistradas.add(organizacion);
	}
	
	public void removerOrganizacion(Organizacion organizacion) {
		organizacionesRegistradas.remove(organizacion);
	}
	
	public void reportarMuestra(Muestra muestra) {
		muestrasReportadas.add(muestra);
	}
	
	/* ¿Qué significa el hecho de notificar sobre una muestra a las organizaciones?
	 * ¿Es necesaría la muestra?
	 */
	public void notificar(Muestra muestra);
}
