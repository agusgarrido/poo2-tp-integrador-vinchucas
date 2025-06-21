package zonaDeCobertura;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import muestra.Muestra;
import organizacion.Organizacion;
import ubicacion.Ubicacion;
import ubicacion.calculadoraDeDistancia;

public class ZonaDeCobertura implements ZonaDeCoberturaSubject{
	private String nombre;
	private Ubicacion epicentro;
	private double radio;
	private List<Muestra> muestrasReportadas = new ArrayList<Muestra>();
	private List<Organizacion> organizacionesRegistradas = new ArrayList<Organizacion>();
	
	public ZonaDeCobertura(String nombre, Ubicacion epicentro, double radio) {
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.radio = radio;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Ubicacion getEpicentro() {
		return this.epicentro;
	}
	
	public double getRadio() {
		return this.radio;
	}
	
	/* Zonas solapadas */
	public List<ZonaDeCobertura> zonasSolapadas(List<ZonaDeCobertura> zonas){
		return zonas.stream().filter(zona -> this.solapadaCon(zona)).collect(Collectors.toList());
	}
	
	private boolean solapadaCon(ZonaDeCobertura zona) {
		double distancia = calculadoraDeDistancia.distanciaEntreDosUbicaciones(this.getEpicentro(), zona.getEpicentro());
	    return distancia <= (this.radio + zona.getRadio());
	}
	
	/* Observers */
	public List<Organizacion> getOrganizacionesRegistradas(){
		return this.organizacionesRegistradas;
	}
	
	public void registrarOrganizacion(Organizacion organizacion) {
		organizacionesRegistradas.add(organizacion);
	}
	
	public void removerOrganizacion(Organizacion organizacion) {
		organizacionesRegistradas.remove(organizacion);
	}
	
	/* Nueva muestra */
	public void registrarMuestra(Muestra muestra) {
		if(this.muestraCercana(muestra.getUbicacion())) {
			muestrasReportadas.add(muestra);
			this.notificarNuevaMuestra(muestra);
		}
	}
	
	public boolean contiene(Muestra muestra) {
	    return muestraCercana(muestra.getUbicacion());
	}
	
	private boolean muestraCercana(Ubicacion ubicacion) {
		return calculadoraDeDistancia.distanciaEntreDosUbicaciones(ubicacion, this.epicentro) < radio;
	}
	
	public void notificarNuevaMuestra(Muestra muestra) {
		this.getOrganizacionesRegistradas().forEach(organizacion -> organizacion.nuevaMuestra(this, muestra));
	};
	
	/* Muestra validada */
	public void notificarMuestraValidada(Muestra muestra) {
		this.getOrganizacionesRegistradas().forEach(organizacion -> organizacion.muestraValidada(this, muestra));
	};
	
}
