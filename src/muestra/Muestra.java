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

	public Muestra(Usuario user, String foto, Ubicacion ubi, Opinion opinionInicial) {
		this.estadoMuestra = new MuestraInicial();
		this.opiniones = new ArrayList<Opinion>();
		this.created_by= user;
		this.dateCreated = LocalDate.now();
		this.fotoMuestra = foto;
		this.ubicacion = ubi;
		this.opiniones.add(opinionInicial);
	}

	public Usuario getCreated_by() {
		return created_by;
	}

	public LocalDate getFechaDeCreacion() {
		return dateCreated;
	}

	public String getFotoMuestra() {
		return fotoMuestra;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	public void addOpinion(Opinion opinion) {
		this.estadoMuestra.puedeOpinar(this ,opinion.getUsuario());//getUsuario hay que implementarlo lei jejeje
        this.opiniones.add(opinion);
        this.estadoMuestra.evaluarTransicion(this);
        //rompo encapsulamiento si le paso el objeto entero? deberia de pasarle el estado de la miestra y ya
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
		return opiniones.stream().anyMatch(op -> op.esDeExperto());//lei, implementame este metodo porfi porque me facilitas la vida jajaja
	}
	
	public Optional<TipoOpinion> tipoOpinionMasVotada() {
	    for (TipoOpinion tipo : TipoOpinion.values()) {
	        long cantidad = opiniones.stream()
	            .filter(op -> op.esDeExperto() && op.getTipoOpinion().equals(tipo))
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
	public String nivelDeValidacion() {
		return estadoMuestra.nivelDeValidacion()
	}
	
	Fecha de creación de la muestra.
	● Fecha de la última votación.
	● Tipo de insecto detectado en la muestra.
	● Nivel de verificación (votada o verificada)
	

}
