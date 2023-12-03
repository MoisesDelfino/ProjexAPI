package com.projexapi.projexapi.Responsavel;


import com.projexapi.projexapi.Utils.Message;
import com.projexapi.projexapi.Utils.Paginacao;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/responsavel")
@CrossOrigin("*")
@AllArgsConstructor
public class ResponsavelController {
    private ResponsavelService responsavelService;

    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarResponsaveis(
            @QuerydslPredicate(root = Responsavel.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Responsavel> responsavelList = Objects.isNull(filtroURI) ?
                this.responsavelService.buscarTodos(pageable):
                this.responsavelService.buscarTodos(filtroURI, pageable);

        List<ResponsavelRepresentation.Lista> listaFinal =
                ResponsavelRepresentation.Lista.from(responsavelList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(responsavelList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }
    @GetMapping("/{idResponsavel}")
    public ResponseEntity<ResponsavelRepresentation.Detalhes> buscarUmResponsavel(
            @PathVariable Long idResponsavel) {

        Responsavel responsavel = this.responsavelService.buscarUmResponsavel(idResponsavel);

        ResponsavelRepresentation.Detalhes detalhes =
                ResponsavelRepresentation.Detalhes
                        .from(responsavel);

        return ResponseEntity
                .ok(detalhes);
    }
    @PostMapping("/")
    public ResponseEntity<Message> createResponsável(
            @RequestBody @Valid ResponsavelRepresentation.Criar criar) {

       this.responsavelService.criarResponsavel(criar);

       return ResponseEntity.ok(new Message("Responsável cadastrado com sucesso!"));


    }
    @PutMapping("/{idResponsavel}")
    public ResponseEntity<Message> atualizarResponavel(
            @PathVariable Long idResponsavel,
            @RequestBody ResponsavelRepresentation.Atualizar atualizar) {

        this.responsavelService.atualizar(idResponsavel, atualizar);


        return ResponseEntity.ok(new Message("Responsável atualizado com sucesso!"));

    }
    @DeleteMapping("/{idResponsavel}")
    public ResponseEntity<Message> inativarResponsavel(
            @PathVariable Long idResponsavel) {


        if (responsavelService.verificaSeResponsavelTemProjetos(idResponsavel)){

            this.responsavelService.inativar(idResponsavel);

            return ResponseEntity.ok(new Message("Responsável inativado com sucesso!"));
        } else {
            return ResponseEntity.badRequest().body(new Message("O responsável não pode ser inativado pois está associado a um ou mais projetos."));
        }

    }
}
