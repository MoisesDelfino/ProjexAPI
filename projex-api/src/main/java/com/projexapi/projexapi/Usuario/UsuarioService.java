package com.projexapi.projexapi.Usuario;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Exceptions.NotfoundException;
import com.projexapi.projexapi.Utils.Message;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(UsuarioRepresentation.Criar criar) {

        return this.usuarioRepository.save(Usuario.builder()
                .username(criar.getUsername())
                .email(criar.getEmail())
                .password(criar.getPassword())
                .roles(criar.getRoles())
                .ativo(Ativo.A)
                .build());
    }
    public ResponseEntity<Message> atualizaImagem(Long idUsuario, String fileName){
        Usuario usuario = this.buscarUmUsuario(idUsuario);

        usuario.setImagem(fileName);

        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok(new Message("Imagem atualizada com sucesso!"));
    }


    public Usuario atualizar(
            Long idUsuario,
            UsuarioRepresentation.Atualizar atualizar) {

        Usuario usuario = this.getUsuario(idUsuario);

        Usuario usuarioParaAtualizar = Usuario.builder()
                .id(idUsuario)
                .username(atualizar.getUsername())
                .email(atualizar.getEmail())
                .roles(atualizar.getRoles())
                .ativo((atualizar.getAtivo()))
                .password(usuario.getPassword())
                .build();

        return this.usuarioRepository.save(usuarioParaAtualizar);

    }
    public Usuario inativar(Long idUsuario) {

        Usuario usuario = this.buscarUmUsuario(idUsuario);

        return this.usuarioRepository.save(Usuario.builder()
                .id(idUsuario)
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.getRoles())
                .ativo(Ativo.I)
                .build());
    }

    public ResponseEntity<Message> adicionaDeviceToken(Long idUsuario, UsuarioRepresentation.@Valid AtualizarDeviceToken atualizar) {
        Usuario usuario = this.buscarUmUsuario(idUsuario);

        List<String> tokens = usuario.getTokensDispositivo();
        int maxTokens = 5; // Limite máximo de tokens

        if (tokens.size() >= maxTokens) {
            tokens.remove(0); // Remove o token mais antigo (primeiro elemento da lista)
        }

        if (!tokens.contains(atualizar.getToken_dispositivo())) {
            tokens.add(atualizar.getToken_dispositivo()); // Adiciona o novo token ao final da lista
        }


        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok(new Message("Device token inserido com sucesso!"));
    }


    public Boolean verificaSeUsuarioTemProjetos(Long idUsuario) {
        Usuario usuario = this.buscarUmUsuario(idUsuario);
        if (Objects.isNull(usuario.getProjetoList()) || usuario.getProjetoList().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Usuario buscarUmUsuario(Long idUsuario) {

        return this.getUsuario(idUsuario);

    }
    private Usuario getUsuario(Long idUsuario) {
        Optional<Usuario> usuarioAtual =
                this.usuarioRepository.findById(idUsuario);

        if (usuarioAtual.isPresent()) {
            return usuarioAtual.get();
        } else {
            throw new NotfoundException("Usuário não encontrado");
        }
    }
    public Page<Usuario> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.usuarioRepository.findAll(filtroURI, pageable);

    }
    public Page<Usuario> buscarTodos(Pageable pageable) {
        return this.usuarioRepository.findAll(pageable);

    }
}
