package com.projexapi.projexapi.Setor;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Exceptions.NotfoundException;
import com.projexapi.projexapi.Utils.Message;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.MemberSubstitution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

@Service
@AllArgsConstructor
@Slf4j
public class SetorService {
    private SetorRepository setorRepository;

    public Setor criarSetor(SetorRepresentation.Criar criar) {

        return this.setorRepository.save(Setor.builder()
                .username(criar.getUsername())
                .email(criar.getEmail())
                .password(criar.getPassword())
                .roles(criar.getRoles())
                .ativo(Ativo.A)
                .build());
    }
    public ResponseEntity<Message> atualizaImagem(Long idSetor, String fileName){
        Setor setor = this.buscarUmSetor(idSetor);

        setor.setImagem(fileName);

        this.setorRepository.save(setor);

        return ResponseEntity.ok(new Message("Imagem atualizada com sucesso!"));
    }


    public Setor atualizar(
            Long idSetor,
            SetorRepresentation.Atualizar atualizar) {

        Setor setor = this.getSetor(idSetor);

        Setor setorParaAtualizar = Setor.builder()
                .id(idSetor)
                .username(atualizar.getUsername())
                .email(atualizar.getEmail())
                .roles(atualizar.getRoles())
                .ativo((atualizar.getAtivo()))
                .password(setor.getPassword())
                .build();

        return this.setorRepository.save(setorParaAtualizar);

    }
    public Setor inativar(Long idSetor) {

        Setor setor = this.buscarUmSetor(idSetor);

        return this.setorRepository.save(Setor.builder()
                .id(idSetor)
                .username(setor.getUsername())
                .email(setor.getEmail())
                .password(setor.getPassword())
                .roles(setor.getRoles())
                .ativo(Ativo.I)
                .build());
    }

    public ResponseEntity<Message> adicionaDeviceToken(Long idSetor, SetorRepresentation.AtualizarDeviceToken atualizar) {
        Setor setor = this.buscarUmSetor(idSetor);

        List<String> tokens = setor.getTokensDispositivo();
        int maxTokens = 5; // Limite máximo de tokens

        if (tokens.size() >= maxTokens) {
            tokens.remove(0); // Remove o token mais antigo (primeiro elemento da lista)
        }

        if (!tokens.contains(atualizar.getToken_dispositivo())) {
            tokens.add(atualizar.getToken_dispositivo()); // Adiciona o novo token ao final da lista
        }


        this.setorRepository.save(setor);

        return ResponseEntity.ok(new Message("Device token inserido com sucesso!"));
    }


    public Boolean verificaSeSetorTemProjetos(Long idSetor) {
        Setor setor = this.buscarUmSetor(idSetor);
        if (Objects.isNull(setor.getProjetoList()) || setor.getProjetoList().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Setor buscarUmSetor(Long idSetor) {

        return this.getSetor(idSetor);

    }
    private Setor getSetor(Long idSetor) {
        Optional<Setor> setorAtual =
                this.setorRepository.findById(idSetor);

        if (setorAtual.isPresent()) {
                return setorAtual.get();
        } else {
            throw new NotfoundException("Setor não encontrado");
        }
    }
    public Page<Setor> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.setorRepository.findAll(filtroURI, pageable);

    }
    public Page<Setor> buscarTodos(Pageable pageable) {
        return this.setorRepository.findAll(pageable);

    }
}
