package br.com.tech4me.tech4music.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class MusicaDto {
    
    private String id;
    @NotBlank(message = "O TÍTULO DEVE SER PREENCHIDO")
    private String titulo;
    @NotBlank(message = "O ARTISTA DEVE SER PREENCHIDO")
    private String artista;
    private String genero;
    @Min(1)
    private int anoLancamento;
    private String compositor;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public int getAnoLancamento() {
        return anoLancamento;
    }
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
    public String getCompositor() {
        return compositor;
    }
    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }
}
