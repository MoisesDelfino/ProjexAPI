package com.projexapi.projexapi.Usuario;

import com.projexapi.projexapi.Setor.Setor;
import com.projexapi.projexapi.Setor.SetorRepresentation;
import com.projexapi.projexapi.Setor.SetorService;
import com.projexapi.projexapi.Utils.Message;
import com.projexapi.projexapi.Utils.Paginacao;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/usuario")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@Component
public class UsuarioController {

    UsuarioService usuarioService;

    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarUsuarios(
            @QuerydslPredicate(root = Setor.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Usuario> usuarioList = Objects.isNull(filtroURI) ?
                this.usuarioService.buscarTodos(pageable):
                this.usuarioService.buscarTodos(filtroURI, pageable);

        List<UsuarioRepresentation.Lista> listaFinal =
                UsuarioRepresentation.Lista.from(usuarioList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(usuarioList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Message> inativarUsuario(
            @PathVariable Long idUsuario) {

        if (usuarioService.verificaSeUsuarioTemProjetos(idUsuario)){

            this.usuarioService.inativar(idUsuario);

            return ResponseEntity.ok(new Message("Usuário inativado com sucesso!"));
        } else {
            return ResponseEntity.badRequest().body(new Message("O usuário não pode ser inativado pois está associado a um ou mais projetos."));
        }
    }


    @PutMapping("/{idUsuario}")
    public ResponseEntity<Message> atualizar(
            @PathVariable Long idUsuario,
            @RequestBody @Valid UsuarioRepresentation.Atualizar atualizar) {


        Usuario ususarioAtualizado = this.usuarioService.atualizar(idUsuario,atualizar);

        UsuarioRepresentation.Detalhes detalhes =
                UsuarioRepresentation.Detalhes.from(ususarioAtualizado);

        return ResponseEntity.ok(new Message("Usuário atualizado com sucesso!"));

    }

    @PutMapping("/{idUsuario}/deviceToken")
    public ResponseEntity<Message> adicionaDeviceToken(
            @PathVariable Long idUsuario,
            @RequestBody @Valid UsuarioRepresentation.AtualizarDeviceToken atualizar){

        this.usuarioService.adicionaDeviceToken(idUsuario, atualizar);

        return ResponseEntity.ok(new Message("Device token atualizado com sucesso!"));
    }

    @PutMapping("/{idUsuario}/atualizaImagem/{fileName}")
    public ResponseEntity<Message> atualizaImagem(
            @PathVariable Long idUsuario,
            @PathVariable String fileName){

        this.usuarioService.atualizaImagem(idUsuario, fileName);

        return ResponseEntity.ok(new Message("Imagem atualizada com sucesso!"));
    }


    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioRepresentation.Detalhes> buscarUmUsuario(
            @PathVariable Long idUsuario) {

        Usuario usuario = this.usuarioService.buscarUmUsuario(idUsuario);

        UsuarioRepresentation.Detalhes detalhes =
                UsuarioRepresentation.Detalhes
                        .from(usuario);

        return ResponseEntity
                .ok(detalhes);
    }
}
