package com.projexapi.projexapi.Setor;

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
import java.util.Set;

;

@RestController
@RequestMapping("api/setor")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@Component
public class SetorController {

    SetorService setorService;

    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarSetores(
            @QuerydslPredicate(root = Setor.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Setor> setorList = Objects.isNull(filtroURI) ?
                this.setorService.buscarTodos(pageable):
                this.setorService.buscarTodos(filtroURI, pageable);

        List<SetorRepresentation.Lista> listaFinal =
                SetorRepresentation.Lista.from(setorList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(setorList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }

    @DeleteMapping("/{idSetor}")
    public ResponseEntity<Message> inativarSetor(
            @PathVariable Long idSetor) {

        if (setorService.verificaSeSetorTemProjetos(idSetor)){

            this.setorService.inativar(idSetor);

            return ResponseEntity.ok(new Message("Setor inativado com sucesso!"));
        } else {
            return ResponseEntity.badRequest().body(new Message("O setor não pode ser inativado pois está associado a um ou mais projetos."));
        }
    }


    @PutMapping("/{idSetor}")
    public ResponseEntity<Message> atualizar(
            @PathVariable Long idSetor,
            @RequestBody @Valid SetorRepresentation.Atualizar atualizar) {


        Setor setorAtualizado = this.setorService.atualizar(idSetor,atualizar);

        SetorRepresentation.Detalhes detalhes =
                SetorRepresentation.Detalhes.from(setorAtualizado);

        return ResponseEntity.ok(new Message("Setor atualizado com sucesso!"));

    }

    @PutMapping("/{idSetor}/deviceToken")
    public ResponseEntity<Message> adicionaDeviceToken(
            @PathVariable Long idSetor,
            @RequestBody @Valid SetorRepresentation.AtualizarDeviceToken atualizar){

            this.setorService.adicionaDeviceToken(idSetor, atualizar);

        return ResponseEntity.ok(new Message("Device token atualizado com sucesso!"));
    }

    @PutMapping("/{idSetor}/atualizaImagem/{fileName}")
    public ResponseEntity<Message> atualizaImagem(
            @PathVariable Long idSetor,
            @PathVariable String fileName){

            this.setorService.atualizaImagem(idSetor, fileName);

        return ResponseEntity.ok(new Message("Imagem atualizada com sucesso!"));
    }


    @GetMapping("/{idSetor}")
    public ResponseEntity<SetorRepresentation.Detalhes> buscarUmSetor(
            @PathVariable Long idSetor) {

        Setor setor = this.setorService.buscarUmSetor(idSetor);

        SetorRepresentation.Detalhes detalhes =
                SetorRepresentation.Detalhes
                        .from(setor);

        return ResponseEntity
                .ok(detalhes);
    }
}
