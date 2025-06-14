package muestra;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
	private Ubicacion ubicacion; // lo dejo asi para poder modelarlo mas tarde

	public Muestra(Usuario user, String foto, Ubicacion ubi) {
		this.estadoMuestra = new MuestraInicial();
		this.opiniones = new ArrayList<Opinion>();
		this.created_by= user;
		this.dateCreated = LocalDate.now();
		this.fotoMuestra = foto;
		this.ubicacion = ubi;

	}

	public Usuario getCreated_by() {
		return created_by;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public String getFotoMuestra() {
		return fotoMuestra;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	public void addOpinion(Usuario usuario, Opinion opinion) {
		/* En puedeOpinar falta saber si la muestra la envié yo y si ya opiné */
		this.estadoMuestra.puedeOpinar(usuario);
        this.opiniones.add(opinion);
        this.estadoMuestra.evaluarTransicion(this);//rompo encapsulamiento si le paso el objeto entero? deberia de pasarle el estado de la miestra y ya
    }

	
	public List<Opinion> getOpiniones() {
		return opiniones;
	}
	
	public EstadoMuestra getEstadoMuestra() {
		return this.estadoMuestra;
	}
	public void setEstadoMuestra(EstadoMuestra nuevoEstado) {
		this.estadoMuestra= nuevoEstado;
	}


	public boolean tieneOpinionDeExperto() {
		/* Leila agregó getTipoUsuario */
		return opiniones.stream().anyMatch(op -> op.esDeExperto());
	}
	
	public Optional<TipoOpinion> tipoOpinionVerificada() {
		/* Leila agregó getTipoOpinion y getTipoUsuario */
	    for (TipoOpinion tipo : TipoOpinion.values()) {
	        long cantidad = opiniones.stream()
	            .filter(op -> op.esDeExperto() && op.getTipo().equals(tipo))
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

	

}
