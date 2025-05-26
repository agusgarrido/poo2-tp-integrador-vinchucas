package opinion;

public class Opinion {
	private tipoOpinion tipoOpinion;
	private String tipoUsuario;
	
	public Opinion(String tipoOpinion, String tipoUsuario) {
		this.tipoOpinion = tipoOpinion;
		this.tipoUsuario = tipoUsuario;
	}

	public String getTipoOpinion() {
		return tipoOpinion;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}
	

}
