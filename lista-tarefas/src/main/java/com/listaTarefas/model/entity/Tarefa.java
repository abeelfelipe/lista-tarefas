package com.listaTarefas.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_lista")
    @NotNull
    private ListaTarefas listaTarefas;

    @Column(nullable = false, length = 150)
    @NotEmpty(message = "Campo nome é obrigatório")
    private String nomeTarefa;

    @Column(nullable = false, length = 150)
    private Boolean concluido;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataUltimaAlteracao;

    @PrePersist
    public void prePersist () {
        setDataUltimaAlteracao(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate (){
        setDataUltimaAlteracao(LocalDateTime.now());
    }
}
