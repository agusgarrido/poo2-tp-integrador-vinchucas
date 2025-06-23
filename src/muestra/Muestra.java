package muestra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import opinion.Opinion;
import opinion.TipoOpinion;
import ubicacion.Ubicacion;
import usuario.Usuario;

public class Muestra {

	private EstadoMuestra estadoMuestra;
	private List<Opinion> opiniones;
	private Usuario created_by;
	private LocalDate dateCreated;
	private String fotoMuestra;
	private Ubicacion ubicacion; 

	public Muestra(Usuario user, String foto, Ubicacion ubi, Opinion opinionInicial) {
		this.estadoMuestra = new MuestraInicial();
		this.opiniones = new ArrayList<Opinion>();
		this.created_by = user;
		this.dateCreated = LocalDate.now();
		this.fotoMuestra = foto;
		this.ubicacion = ubi;
		this.opiniones.add(opinionInicial);
	}

	public Usuario getCreated_by() {
		return created_by;
	}

	public String getFotoMuestra() {
		return fotoMuestra;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void addOpinion(Opinion opinion) {
		this.estadoMuestra.puedeOpinar(this, opinion.getUsuario());
		this.opiniones.add(opinion);
		this.estadoMuestra.evaluarTransicion(this);
	}

	public List<Opinion> getOpiniones() {
		return opiniones;
	}

	public EstadoMuestra getEstadoMuestra() {
		return this.estadoMuestra;
	}

	public void setEstadoMuestra(EstadoMuestra nuevoEstado) {
		this.estadoMuestra = nuevoEstado;
	}

	public boolean tieneOpinionDeExperto() {
		return opiniones.stream().anyMatch(op -> op.esDeExperto());
	}

	public Optional<TipoOpinion> tipoOpinionMasVotada() {
		for (TipoOpinion tipo : TipoOpinion.values()) {
			long cantidad = opiniones.stream().filter(op -> op.esDeExperto() && op.getTipoOpinion().equals(tipo))
					.count();

			if (cantidad >= 2) {
				return Optional.of(tipo);
			}
		}
		return Optional.empty();
	}

	public Opinion getUltimaOpinion() {
		return opiniones.get(opiniones.size() - 1);
	}
	
	public TipoOpinion resultadoActual() {
		return this.estadoMuestra.resultadoActual(this);
	}

	public TipoEstadoMuestra nivelDeValidacion() {
		return estadoMuestra.nivelDeValidacion();
	}

	public LocalDate getFechaDeCreacion() {
		return dateCreated;
	}

	public LocalDate getFechaUltimaVotacion() {
		return this.getUltimaOpinion().getFecha();
	}

}
