package com.listaTarefas.model.repository;

import com.listaTarefas.model.entity.ListaTarefas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaTarefasRepository extends JpaRepository<ListaTarefas, Integer> {
}
