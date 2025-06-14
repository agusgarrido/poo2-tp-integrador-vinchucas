package usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;
import opinion.Opinion;
import opinion.TipoOpinion;
import ubicacion.Ubicacion;

public class Usuario {
	private TipoUsuario tipo;
	/* ¿Las muestras las guardo acá o en un sistema? ¿Tiene sentido recordarlas? */
	/* ¿Las opiniones las guardo acá o en un sistema? ¿Tiene sentido recordarlas? */
	private List<Muestra> muestrasEnviadas;
	private List<Opinion> opinionesEnviadas;

	public Usuario() {
		this.tipo = new UsuarioBasico();
		this.muestrasEnviadas = new ArrayList<Muestra>();
		this.opinionesEnviadas = new ArrayList<Opinion>();
	}

	public void enviarMuestra(String foto, Ubicacion ubicacion, TipoOpinion tipoOpinion) {
		Muestra muestra = new Muestra(this, foto, ubicacion);
		/* ¿Dónde queda la muestra? */
	}

	public void darOpinion(TipoOpinion tipoOpinion, Muestra muestra) {
		Opinion opinion = new Opinion(this.getTipo(), tipoOpinion, this);
		muestra.addOpinion(this, opinion);
		opinionesEnviadas.add(opinion);
	}

	public void cambiarCategoria(LocalDate fecha) {
		this.getTipo().cambiarCategoria(this, fecha);
	}

	/* TODO: Falta ver quién almacena las muestras */
	public int cantidadMuestrasEnviadas() {
		return (int) muestrasEnviadas.stream().filter(muestra -> enUltimos30Dias(muestra.getDateCreated())).count();
	}

	private boolean enUltimos30Dias(LocalDate fecha) {
		LocalDate hoy = LocalDate.now();
		LocalDate hace30Dias = hoy.minusDays(30);
		return !fecha.isBefore(hace30Dias) && !fecha.isAfter(hoy);
	}

	/* TODO: Falta ver quién almacena las opiniones */
	public int cantidadOpinionesEnviadas(LocalDate fecha) {
		return (int) opinionesEnviadas.stream().filter(opinion -> enUltimos30Dias(opinion.getFecha())).count();
	}

	public TipoUsuario getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
	public boolean esExperto() {
	    return tipo.esExperto();
	}

}