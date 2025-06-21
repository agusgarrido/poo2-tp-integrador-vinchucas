package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;
import opinion.Opinion;
import opinion.TipoOpinion;
import sistemaWeb.SistemaWeb;
import ubicacion.Ubicacion;


public class Usuario {
	private TipoUsuario tipo;
	private List<Opinion> opinionesEnviadas;
	private SistemaWeb sistema;

	public Usuario(SistemaWeb sistema) {
		this.tipo = new UsuarioBasico();
		this.opinionesEnviadas = new ArrayList<Opinion>();
		this.sistema=sistema;
	}

	public void enviarMuestra(String foto, Ubicacion ubicacion, TipoOpinion tipoOpinion) {
		Opinion opinionInicial = new Opinion(tipoOpinion, tipo, this);
		Muestra muestra = new Muestra(this, foto, ubicacion, opinionInicial);
		this.sistema.addMuestra(muestra);
	}

	public void darOpinion(TipoOpinion tipoOpinion, Muestra muestra) {
	    Opinion opinion = new Opinion(tipoOpinion, tipo, this);
	    this.sistema.agregarOpinionAMuestra(muestra, opinion);
	    opinionesEnviadas.add(opinion);
	}




	public void cambiarCategoria(LocalDate fecha) {
		this.getTipo().cambiarCategoria(this, fecha);
	}

	public int cantidadMuestrasEnviadas() {
		return (int) this.sistema.getMuestras().stream().filter(muestra -> enUltimos30Dias(muestra.getFechaDeCreacion())).count();
	}

	private boolean enUltimos30Dias(LocalDate fecha) {
		LocalDate hoy = LocalDate.now();
		LocalDate hace30Dias = hoy.minusDays(30);
		return !fecha.isBefore(hace30Dias) && !fecha.isAfter(hoy);
	}

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
