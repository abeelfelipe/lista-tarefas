package com.listaTarefas.service;

import com.listaTarefas.model.entity.ListaTarefas;
import com.listaTarefas.model.repository.ListaTarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ListaTarefasService {

    @Autowired
    private ListaTarefasRepository repository;

    public ListaTarefas salvar(ListaTarefas listaTarefas) {
        return repository.save(listaTarefas);
    }

    public ListaTarefas findById(String id) {
        return repository.findById(Integer.valueOf(id)).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista não existe"));
    }

    public List<ListaTarefas> findAll() {
        return repository.findAll();
    }

    public void excluir(Integer id) {
        repository.
                findById(id).
                map(listaTarefas -> {
                    repository.delete(listaTarefas);
                    return Void.TYPE;
                }).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void alterar(Integer id, ListaTarefas ListaTarefasAtualizada) {
        repository.
                findById(id).
                map(listaTarefas -> {
                    ListaTarefasAtualizada.setId(listaTarefas.getId());
                    return repository.save(ListaTarefasAtualizada);
                }).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
