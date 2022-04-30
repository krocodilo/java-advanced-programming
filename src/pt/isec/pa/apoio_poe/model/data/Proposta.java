package pt.isec.pa.apoio_poe.model.data;

public class Proposta {

    private String id;
    private String ramoDestino;
    private String titulo;


    public Proposta(String id, String ramoDestino, String titulo) {
        this.id = id;
        this.ramoDestino = ramoDestino;
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return String.join("    ",  id, ramoDestino, titulo );
    }
}
