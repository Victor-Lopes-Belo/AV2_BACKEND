package br.com.tech4me.tech4singer.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.tech4me.tech4singer.shared.Musica;

public class CantorDto {
    
    private String id;
    @NotBlank(message = "O TÍTULO DEVE SER PREENCHIDO")
    private String nome;
    @NotBlank(message = "O TÍTULO DEVE SER PREENCHIDO")
    private String nacionalidade;
    @Min(1)
    private Integer idade;
    private String genero;
    private List<Musica> musica;
    

    // GET`S AND SET`S
    public String getId() {
        return id;
    }
    public List<Musica> getMusica() {
        return musica;
    }
    public void setMusica(List<Musica> musica) {
        this.musica = musica;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNacionalidade() {
        return nacionalidade;
    }
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
    public Integer getIdade() {
        return idade;
    }
    public void setIdade(Integer idade) {
        this.idade = idade;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
}
