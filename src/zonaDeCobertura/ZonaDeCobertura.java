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
	
	/* Según la consigna, debo saber cuáles se solapan, no recordarlas. */
	public List<ZonaDeCobertura> zonasSolapadas(List<ZonaDeCobertura> zonas){
		return zonas.stream().filter(zona -> this.solapadaCon(zona)).collect(Collectors.toList());
	}
	
	/* NOTA: Dos circulos se solapan si la distancia entre sus centros es <= a la suma de sus radios. */
	private boolean solapadaCon(ZonaDeCobertura zona) {
		double distancia = calculadoraDeDistancia.distanciaEntreDosUbicaciones(this.getEpicentro(), zona.getEpicentro());
	    return distancia <= (this.radio + zona.getRadio());
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
	
	/* Nueva muestra */
	public void registrarMuestra(Muestra muestra) {
		muestrasReportadas.add(muestra);
		this.notificarNuevaMuestra(muestra);
	}
	
	public void notificarNuevaMuestra(Muestra muestra) {
		this.getOrganizacionesRegistradas().forEach(organizacion -> organizacion.nuevaMuestra(this, muestra));
	};
	
	/* Muestra validada */
	public void notificarMuestraValidada(Muestra muestra) {
		this.getOrganizacionesRegistradas().forEach(organizacion -> organizacion.muestraValidada(this, muestra));
	};
}
