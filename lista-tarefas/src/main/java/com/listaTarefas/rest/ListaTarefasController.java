package com.listaTarefas.rest;

import com.listaTarefas.model.entity.ListaTarefas;
import com.listaTarefas.model.repository.ListaTarefasRepository;
import com.listaTarefas.service.ListaTarefasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lista-tarefas")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
@Api(value="API Lista de Tarefas - Lista de Tarefas")
public class ListaTarefasController {

    private final ListaTarefasService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva uma lista de tarefas")
    public ListaTarefas salvar(@RequestBody @Valid ListaTarefas listaTarefas) {
        return service.salvar(listaTarefas);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Consulta uma lista de tarefas por ID")
    public ListaTarefas consultarPorId(@PathVariable Integer id) {
        return service.findById(id.toString());
    }

    @GetMapping
    @ApiOperation(value = "Consulta todas as lista de tarefas existentes")
    public List<ListaTarefas> obterTodasAsListas(){
        return service.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta uma lista de terefas")
    public void deletar(@PathVariable Integer id) {
        service.excluir(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Altera uma lista de Tarefas")
    public void alterar(@PathVariable Integer id, @RequestBody @Valid ListaTarefas listaTarefasAtualizada) {
        service.alterar(id, listaTarefasAtualizada);
    }
}
