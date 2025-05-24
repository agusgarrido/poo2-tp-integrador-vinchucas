package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private TipoUsuario tipo;
	private List<Muestra> muestrasEnviadas;
	
	public Usuario() {
		this.tipo = new UsuarioBasico();
		this.muestrasEnviadas = new ArrayList<Muestra>();
		}
	
	/* TODO: Implementar listado de opiniones en una muestra. */
	public void enviarMuestra() {}
	
	/* TODO: Implementar listado de opiniones en una muestra. */
	/* ¿Guardo referencia a mis opiniones? */
	public void darOpinion() {}
	
	//private boolean puedeOpinar() { }
	/* Depende de si en la muestra opinó un experto
	 * Si opinó un experto y yo lo soy, puedo seguir opinando*/
	
	public void cambiarCategoria(LocalDate fecha) {
		this.getTipo().cambiarCategoria(this, fecha);
	}

	/* TODO: Evaluar esta lógica */
	public int cantidadMuestrasEnviadas() {
	    return (int) muestrasEnviadas.stream()
	        .filter(muestra -> enUltimos30Dias(muestra.getFecha()))
	        .count();
	}

	/* TODO: Revisar implementación y ver si sirve para cantidadObservacionesEnviadas*/
	private boolean enUltimos30Dias(LocalDate fecha) {
	    LocalDate hoy = LocalDate.now();
	    LocalDate hace30Dias = hoy.minusDays(30);
	    return !fecha.isBefore(hace30Dias) && !fecha.isAfter(hoy);
	}
	
	public int cantidadOpinionesEnviadas(LocalDate fecha) {
		/* Tomar como referencia la fecha actual */
		return 0;
	}
	
	public TipoUsuario getTipo() {
		return this.tipo;
	}
	
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
}