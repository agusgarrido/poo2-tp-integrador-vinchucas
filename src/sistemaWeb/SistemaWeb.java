package sistemaWeb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

import muestra.Muestra;
import opinion.Opinion;
//import filtros.Filtro; dejo aca comentado lei para que implementes los filtros
import usuario.Usuario;
import zonaDeCobertura.ZonaDeCobertura;
import organizacion.Organizacion;

public class SistemaWeb {

    private List<Muestra> muestras;
    private List<Usuario> usuarios;
    private List<ZonaDeCobertura> zonas;
    private List<Organizacion> organizaciones;

    public SistemaWeb() {
        this.muestras = new ArrayList<Muestra>();
        this.usuarios = new ArrayList<Usuario>();
        this.zonas = new ArrayList<ZonaDeCobertura>();
        this.organizaciones = new ArrayList<Organizacion>();
    }

    public void addMuestra(Muestra muestra) {
        this.muestras.add(muestra);
        this.zonas.forEach(z -> z.notificarNuevaMuestra(muestra));
        this.notificarOrganizacionesNuevaMuestra(muestra);
    }

    public void agregarOpinionAMuestra(Muestra muestra, Opinion opinion) {
        muestra.addOpinion(opinion);
        if (muestra.getEstadoMuestra() instanceof muestra.MuestraVerificada) {
            this.validarMuestra(muestra);
        }
    }

   /* public List<Muestra> buscar(Filtro filtro) {
        return this.muestras.stream()
                .filter(filtro::cumple)
                .collect(Collectors.toList());
    }
    Este metodo lo dejo comentado lei! para implemetarlo cuando esten los filtros 
    */

    public void validarMuestra(Muestra muestra) {
        this.notificarOrganizacionesMuestraValidada(muestra);
    }

    public void actualizarUsuarios() {
        this.usuarios.forEach(u -> u.cambiarCategoria(LocalDate.now()));
    }

    private void notificarOrganizacionesNuevaMuestra(Muestra muestra) {
        this.zonas.stream()
            .filter(z -> z.contiene(muestra))
            .forEach(z -> z.notificarNuevaMuestra(muestra));
    }

    private void notificarOrganizacionesMuestraValidada(Muestra muestra) {
        this.zonas.stream()
            .filter(z -> z.contiene(muestra))
            .forEach(z -> z.notificarMuestraValidada(muestra));
    }

    
    public List<Muestra> getMuestras() {
        return this.muestras;
    }
    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }
    public List<ZonaDeCobertura> getZonas() {
        return this.zonas;
    }
    public List<Organizacion> getOrganizaciones() {
        return this.organizaciones;
    }
}
