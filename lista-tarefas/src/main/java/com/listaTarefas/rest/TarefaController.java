package com.listaTarefas.rest;

import com.listaTarefas.model.entity.Tarefa;
import com.listaTarefas.model.entity.dto.TarefaDTO;
import com.listaTarefas.service.TarefasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tarefa")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
@Api(value="API Lista de Tarefas - Tarefas")
public class TarefaController {

    private final TarefasService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva uma tarefa para uma lista específica")
    public Tarefa salvar(@RequestBody TarefaDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Consulta uma tarefa por ID")
    public Tarefa consultarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping
    @ApiOperation(value = "Consulta uma tarefa pelo Id da Lista")
    public List<Tarefa> consultarPorLista(@RequestParam(value = "idLista", required = true) String idLista) {
        return service.findAllByListaTarefas(idLista);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta uma tarefa")
    public void deletar(@PathVariable Integer id) {
        service.excluir(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Altera uma tarefa já existente")
    public void alterar(@PathVariable Integer id, @RequestBody @Valid Tarefa tarefaAtualizada) {
        service.alterar(id, tarefaAtualizada);
    }

}
