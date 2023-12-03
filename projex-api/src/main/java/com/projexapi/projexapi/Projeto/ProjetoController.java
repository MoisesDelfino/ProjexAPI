package com.projexapi.projexapi.Projeto;

import com.projexapi.projexapi.Auditoria.Projeto.ProjetoAuditoriaRepresentation;
import com.projexapi.projexapi.Dashboard.DashboardRepresentation;
import com.projexapi.projexapi.Enums.Status;
import com.projexapi.projexapi.Setor.Setor;
import com.projexapi.projexapi.Setor.SetorService;
import com.projexapi.projexapi.Utils.Message;
import com.projexapi.projexapi.Utils.Paginacao;
import com.projexapi.projexapi.Utils.UploadArquivoController;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/projeto")
@CrossOrigin("*")
@AllArgsConstructor
public class ProjetoController {
    private ProjetoService projetoService;

    private SetorService setorService;

    @PostMapping("/{idSetor}")
    public Long createProjeto(
            @PathVariable Long idSetor,
            @RequestBody @Valid ProjetoRepresentation.Criar criar) {

        Projeto projeto = this.projetoService.criarProjeto(setorService, idSetor, criar);

        return projeto.getId();
    }

    @GetMapping("/setor/{idSetor}")
    public ResponseEntity<Paginacao> buscarProjetosPorSetor(
            @PathVariable Long idSetor,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Projeto> projetoList = this.projetoService.buscarProjetosPorSetor(idSetor, pageable);

        List<ProjetoRepresentation.Detalhes> listaFinal =
                ProjetoRepresentation.Detalhes.from(projetoList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(projetoList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);
    }



    @GetMapping("/{idProjeto}/principal")
    public ResponseEntity<ProjetoRepresentation.AbaPrincipal> buscarAbaPrincipal(
            @PathVariable Long idProjeto) {

        Projeto projeto = this.projetoService.buscarUmProjeto(idProjeto);

        ProjetoRepresentation.AbaPrincipal principal =
                ProjetoRepresentation.AbaPrincipal
                        .from(projeto);

        return ResponseEntity
                .ok(principal);
    }
    @GetMapping("/{idProjeto}/detalhes")
    public ResponseEntity<ProjetoRepresentation.AbaDetalhes> buscarAbaDetalhes(
            @PathVariable Long idProjeto) {

        Projeto projeto = this.projetoService.buscarUmProjeto(idProjeto);

        ProjetoRepresentation.AbaDetalhes detalhes =
                ProjetoRepresentation.AbaDetalhes
                        .from(projeto);

        return ResponseEntity
                .ok(detalhes);
    }

    @GetMapping("/{idProjeto}/responsavel")
    public ResponseEntity<ProjetoRepresentation.AbaResponsavel> buscarAbaResponsavel(
            @PathVariable Long idProjeto) {

        Projeto projeto = this.projetoService.buscarUmProjeto(idProjeto);

        ProjetoRepresentation.AbaResponsavel responsavel =
                ProjetoRepresentation.AbaResponsavel
                        .from(projeto);

        return ResponseEntity
                .ok(responsavel);
    }

    @GetMapping("/{idProjeto}/acoes")
    public ResponseEntity<ProjetoRepresentation.AbaAcoes> buscarAbaAcoes(
            @PathVariable Long idProjeto) {

        Projeto projeto = this.projetoService.buscarUmProjeto(idProjeto);

        ProjetoRepresentation.AbaAcoes acao =
                ProjetoRepresentation.AbaAcoes
                        .from(projeto);

        return ResponseEntity
                .ok(acao);
    }

    @GetMapping("/{idProjeto}/riscos")
    public ResponseEntity<ProjetoRepresentation.AbaRiscos> buscarAbaRiscos(
            @PathVariable Long idProjeto) {

        Projeto projeto = this.projetoService.buscarUmProjeto(idProjeto);

        ProjetoRepresentation.AbaRiscos risco =
                ProjetoRepresentation.AbaRiscos
                        .from(projeto);

        return ResponseEntity
                .ok(risco);
    }

    @GetMapping("/{idProjeto}/contratos")
    public ResponseEntity<ProjetoRepresentation.AbaContratos> buscarAbaContratos(
            @PathVariable Long idProjeto) {

        Projeto projeto = this.projetoService.buscarUmProjeto(idProjeto);

        ProjetoRepresentation.AbaContratos contrato =
                ProjetoRepresentation.AbaContratos
                        .from(projeto);

        return ResponseEntity
                .ok(contrato);
    }

    @GetMapping("/{idProjeto}/comentarios")
    public ResponseEntity<ProjetoRepresentation.AbaComentarios> buscarAbaComentarios(
            @PathVariable Long idProjeto) {

        Projeto projeto = this.projetoService.buscarUmProjeto(idProjeto);

        ProjetoRepresentation.AbaComentarios comentario =
                ProjetoRepresentation.AbaComentarios
                        .from(projeto);

        return ResponseEntity
                .ok(comentario);
    }

    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarProjetos(
            @QuerydslPredicate(root = Projeto.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Projeto> projetoList = Objects.isNull(filtroURI) ?
                this.projetoService.buscarTodos(pageable) :
                this.projetoService.buscarTodos(filtroURI, pageable);

        List<ProjetoRepresentation.Detalhes> listaFinal =
                ProjetoRepresentation.Detalhes.from(projetoList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(projetoList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }

    @PostMapping("/{idProjeto}/finalizar")
    public ResponseEntity<Message> finalizarPojeto(@PathVariable Long idProjeto){

        Projeto projeto = this.projetoService.buscarUmProjeto(idProjeto);

        Boolean acoesEmAberto = this.projetoService.existemAcoesEmAberto(idProjeto);

        if (projeto.getStatus() == Status.F){
            return ResponseEntity.badRequest().body(new Message("Projeto finalizado!"));
        } else if (projeto.getStatus() == Status.C ) {
            return ResponseEntity.badRequest().body(new Message("Projeto cancelado!"));
        } else if (acoesEmAberto) {
            return ResponseEntity.badRequest().body(new Message("Existem ações em aberto no projeto."));
        }else {
            this.projetoService.finalizarProjeto(idProjeto);
            return ResponseEntity.ok(new Message("Projeto finalizado com sucesso!"));
        }
    }




    @GetMapping("/{idProjeto}")
    public ResponseEntity<ProjetoRepresentation.Detalhes> buscarUmProjeto(
            @PathVariable Long idProjeto) {

        Projeto projeto = this.projetoService.buscarUmProjeto(idProjeto);

        ProjetoRepresentation.Detalhes detalhes =
                ProjetoRepresentation.Detalhes
                        .from(projeto);

        return ResponseEntity
                .ok(detalhes);
    }
    @PutMapping("/{idProjeto}")
    public ResponseEntity<Message> atualizarProjeto(
            @PathVariable Long idProjeto,
            @RequestBody ProjetoRepresentation.Atualizar atualizar) {

        if (this.projetoService.verificaSePodeEditarProjetoPorStatus(idProjeto)){
            Projeto projetoAtualizado =
                    this.projetoService.atualizar(idProjeto, atualizar);


            return ResponseEntity.ok(new Message("Projeto atualizado com sucesso"));

        } else {
           return ResponseEntity.badRequest().body(new Message("Projeto não pode ser editado pois está cancelado ou finalizado"));
        }

    }
    @DeleteMapping("/{idProjeto}")
    public ResponseEntity<Message> inativarProjeto(
            @PathVariable Long idProjeto) {

       this.projetoService.inativar(idProjeto);

       return ResponseEntity.ok(new Message("Projeto inativado com sucesso!"));

    }
}
