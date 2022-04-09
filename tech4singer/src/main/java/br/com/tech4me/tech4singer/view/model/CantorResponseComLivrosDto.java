package br.com.tech4me.tech4singer.view.model;

import java.util.List;

import br.com.tech4me.tech4singer.shared.Musica;

public class CantorResponseComLivrosDto {
    
    private String id;
    private String nome;
    private String genero;
    private List<Musica> musica;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<Musica> getMusica() {
        return musica;
    }
    public void setMusica(List<Musica> musica) {
        this.musica = musica;
    }
}
