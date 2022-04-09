package br.com.tech4me.tech4music.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.tech4music.dto.MusicaDto;
import br.com.tech4me.tech4music.model.Musica;
import br.com.tech4me.tech4music.repository.MusicaRepository;

@Service
public class MusicaserviceImpel implements MusicaService {
    ModelMapper mapper = new ModelMapper();

    @Autowired
    private MusicaRepository repositorio;
    
    // GET
    @Override
    public List<MusicaDto> obterTodasAsMusicas() {
        List<Musica> musica = repositorio.findAll();

        return musica.stream()
                .map(l -> mapper.map(l, MusicaDto.class))
                .collect(Collectors.toList());
    }

    // GET id
    @Override
    public Optional<MusicaDto> obterMusicaPorId(String id) {

        Optional<Musica> musica = repositorio.findById(id);
        
        if(musica.isPresent()) {
            return Optional.of(mapper
            .map(musica.get(), MusicaDto.class));
        }
        return Optional.empty();
    }

    // POST
    @Override
    public MusicaDto criarMusica(MusicaDto musica) {

        Musica musicaCriada = mapper.map(musica, Musica.class);

        musicaCriada = repositorio.save(musicaCriada);

        return mapper.map(musicaCriada, MusicaDto.class);
    }

    // PUT
    @Override
    public MusicaDto atualizarMusicaPorId(String id, MusicaDto musicaNova) {

        Musica musicaAtualizar = mapper.map(musicaNova, Musica.class);

        musicaAtualizar.setId(id);

        musicaAtualizar = repositorio.save(musicaAtualizar);

        return mapper.map(musicaAtualizar, MusicaDto.class);
    }

    // DELETE
    @Override
    public void excluirMusicaPorId(String id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<MusicaDto> obterMusicaPorArtista(String artista) {

        List<Musica> musica = repositorio.findByArtista(artista);

        return musica.stream()
        .map(m -> mapper.map(m, MusicaDto.class)).collect(Collectors.toList());
    }
}

