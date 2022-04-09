package br.com.tech4me.tech4music.services;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.tech4music.dto.MusicaDto;

public interface MusicaService {
    
    List<MusicaDto> obterTodasAsMusicas();

    Optional <MusicaDto> obterMusicaPorId(String id);

    MusicaDto criarMusica(MusicaDto musica);

    MusicaDto atualizarMusicaPorId(String id, MusicaDto musica);
    
    void excluirMusicaPorId(String id);

    List<MusicaDto> obterMusicaPorArtista(String artista);
}
