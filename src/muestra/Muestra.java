package muestra;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import opinion.Opinion;
import opinion.TipoOpinion;
import usuario.Usuario;

public class Muestra {

	private EstadoMuestra estadoMuestra;
	private List<Opinion> opiniones;
	private Usuario created_by;
	private LocalDateTime dateCreated;
	private String fotoMuestra;
	private String ubicacion; // lo dejo asi para poder modelarlo mas tarde

	public Muestra(Usuario user, String foto, String ubi) {
		this.estadoMuestra = new MuestraInicial();
		this.opiniones = new ArrayList<Opinion>();
		this.created_by= user;
		this.dateCreated = LocalDateTime.now();
		this.fotoMuestra = foto;
		this.ubicacion = ubi;

	}
	

	public Usuario getCreated_by() {
		return created_by;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public String getFotoMuestra() {
		return fotoMuestra;
	}

	public String getUbicacion() {
		return ubicacion;
	}
	
	public void addOpinion(Usuario usuario, Opinion opinion) {
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
		return opiniones.stream().anyMatch(op -> op.esDeExperto());
	}
	
	public Optional<TipoOpinion> tipoOpinionVerificada() {
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
