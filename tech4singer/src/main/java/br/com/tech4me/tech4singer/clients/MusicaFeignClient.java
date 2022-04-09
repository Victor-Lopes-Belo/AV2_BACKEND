package br.com.tech4me.tech4singer.clients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.tech4me.tech4singer.shared.Musica; 

@FeignClient(name="tech4music", fallback = MusicaFeingClientFallBack.class)
public interface MusicaFeignClient {

    @GetMapping(path= "/api/musicas/artista/{artista}")
    List<Musica> obterMusicaPorArtista(@PathVariable String artista);
}

@Component
class MusicaFeingClientFallBack implements MusicaFeignClient {

    @Override
    public List<Musica> obterMusicaPorArtista(String artista) {
        return new ArrayList<>();
    }

}