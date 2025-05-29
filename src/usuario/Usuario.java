package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;

public class Usuario {
	private TipoUsuario tipo;
	/* ¿Lo guardo acá o en un sistema? */
	private List<Muestra> muestrasEnviadas;

	public Usuario() {
		this.tipo = new UsuarioBasico();
		this.muestrasEnviadas = new ArrayList<Muestra>();
	}

	/* TODO: Implementar listado de opiniones en una muestra. */
	/* NOTA: Ubicación, por ahora es un string */
	/* ¿Qué hago con la opinión de la muestra enviado? */
	public void enviarMuestra(String foto, String ubicacion, TipoOpinion tipoOpinion) {
		Muestra muestra = new Muestra(this, foto, ubicacion);
		/* Paso: Agregar la muestra en el sistema */
		/* Evaluar si esto sucede acá */
		this.darOpinion(tipoOpinion, muestra);
	}
	
	/* TODO: Implementar listado de opiniones en una muestra. */
	/* ¿Guardo referencia a mis opiniones? */
	public void darOpinion(TipoOpinion tipoOpinion, Muestra muestra) {
		Opinion opinion = new Opinion(tipoOpinion, LocalDate.now(), this.getTipo());
		muestra.addOpinion(opinion);
		/* ¿Agrego la validación de abajo en la clase Muestra? -> resuelto en el addOpinion*/
		/* Falta evaluar que no haya opinado */
	}
	
	//private boolean puedeOpinar() { }
	/* Depende de si en la muestra opinó un experto
	 * Si opinó un experto y yo lo soy, puedo seguir opinando*/

	public void cambiarCategoria(LocalDate fecha) {
		this.getTipo().cambiarCategoria(this, fecha);
	}

	/* TODO: Evaluar esta lógica */
	public int cantidadMuestrasEnviadas() {
		return (int) muestrasEnviadas.stream().filter(muestra -> enUltimos30Dias(muestra.getDateCreated())).count();
	}

	/*
	 * TODO: Revisar implementación y ver si sirve para
	 * cantidadObservacionesEnviadas
	 */
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