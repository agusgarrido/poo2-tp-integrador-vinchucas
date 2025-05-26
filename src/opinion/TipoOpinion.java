package opinion;

public enum TipoOpinion {
    VINCHUCA("Vinchuca"),
    CHINCHEFOLIADA("Chinche foliada"),
    PHTIACHINCHE("Ph-tiachinche"),
	NINGUNA("Ninguna"),
	IMAGENPOCOCLARA("Imagen poco clara");

    private final String descripcion;

    Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

