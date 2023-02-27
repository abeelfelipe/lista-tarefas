import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable, onErrorResumeNext } from 'rxjs';
import { ListaTarefasService } from 'src/app/lista-tarefas.service';
import { TarefaService } from 'src/app/tarefa.service';
import { ListaTarefas } from '../../model/ListaTarefas';
import { Tarefa } from '../Tarefa';

@Component({
  selector: 'app-lista-tarefas-form',
  templateUrl: './lista-tarefas-form.component.html',
  styleUrls: ['./lista-tarefas-form.component.css']
})
export class ListaTarefasFormComponent implements OnInit {

  listaTarefa!:ListaTarefas;
  novaTarefa!: Tarefa;
  idLista!: number;
  tarefas: Tarefa[] = [];
  tarefaSelecionada!: Tarefa;
  id!: number;
  sucesso: boolean = false;
  mensagemErro?: string = "";

  constructor(private serviceLista : ListaTarefasService, private serviceTarefa : TarefaService, private router:Router, private activatedRoute: ActivatedRoute) { 
    this.listaTarefa = new ListaTarefas();
    this.novaTarefa = new Tarefa();
    this.tarefaSelecionada = new Tarefa();
  }

  ngOnInit(): void {
    let params :Observable<Params> = this.activatedRoute.params;
    params.subscribe(urlParams => {
      this.id = urlParams['id'];
      if(this.id){
        this.serviceLista.getListaById(this.id)
        .subscribe(response => {
          this.listaTarefa = response; 
          this.consultarTarefasDaLista(this.listaTarefa.id!)
        },
          errorResponse => this.listaTarefa = new ListaTarefas());
      }
    });
  }

  salvarLista(){
    if(this.listaTarefa.id){
      this.serviceLista.alterar(this.listaTarefa)
      .subscribe(response => {
        this.salvarTarefas(this.listaTarefa); 
        this.consultarTarefasDaLista(this.listaTarefa.id!);
        this.sucesso = true;
        this.mensagemErro = "";
      },
      errorResponse => {
        this.sucesso = false;
        this.mensagemErro = "Erro ao alterar Lista";
      }
      
      );
    } else {
      this.serviceLista.salvarLista(this.listaTarefa)
      .subscribe(response => {
        this.salvarTarefas(response); 
        this.listaTarefa = response;
        this.consultarTarefasDaLista(this.listaTarefa.id!);
        this.sucesso = true;
        this.mensagemErro = "";
      },
      errorResponse => {
        this.sucesso = false;
        this.mensagemErro = "Campo nome é obrigatorio.";
      }
      );
    }
  }

  voltarParaListagem(){
    this.router.navigate(['/lista-tarefas-lista']);
  }

  // daqui pra baixo só funcoes referentes a tarefa
  salvarTarefas(lista: ListaTarefas){
    for (let i = 0; i < this.tarefas.length; i++) {
      if(this.tarefas[i].id){
        this.alterarTarefa(this.tarefas[i]);
      } else {
        this.tarefas[i].idLista = lista.id;
        this.serviceTarefa.salvarTarefa(this.tarefas[i])
        .subscribe(response => {
          this.tarefas[i] = response;
          console.log(response);
        }, 
        errorResponse => {
          this.sucesso = false;
          this.mensagemErro = "Erro ao salvar tarefa";
        }
        );
      }
    }
  }

  alterarStatus(tarefa:Tarefa){
    if(tarefa.id){
      tarefa.concluido = tarefa.concluido == null ? true : !tarefa.concluido;
      this.alterarTarefa(tarefa);
    } else {
      for (let i = 0; i < this.tarefas.length; i++) {
        if(this.tarefas[i].nomeTarefa === tarefa.nomeTarefa){
          this.tarefas[i].concluido = this.tarefas[i].concluido == null ? true : !this.tarefas[i].concluido;
        }
      }
    }
    
  }

  preparaTarefa(tarefa:Tarefa){
    this.tarefaSelecionada = tarefa;
  }

  deletarTarefa(tarefa: Tarefa) {

    if(this.tarefaSelecionada.id){
      this.serviceTarefa.deletarTarefa(this.tarefaSelecionada)
      .subscribe(
        response => {
          this.ngOnInit();
          this.sucesso = true;
          this.mensagemErro = "";},
        errorResponse => {
          this.sucesso = false;
          this.mensagemErro = "Erro ao deletar Tarefa";
        }
      );
      this.tarefaSelecionada = new Tarefa();
    } else {
      this.removerTarefaDaLista(this.tarefaSelecionada);
      this.tarefaSelecionada = new Tarefa();
    }
  }

  removerTarefaDaLista(tarefa:Tarefa){
    for (let i = 0; i < this.tarefas.length; i++) {
      if(this.tarefas[i].nomeTarefa === tarefa.nomeTarefa){
        this.tarefas.splice(i,1);
        break;
      }
    }
  }

  alterarTarefaDaLista(tarefa:Tarefa){
    for (let i = 0; i < this.tarefas.length; i++) {
      if(this.tarefas[i].nomeTarefa === tarefa.nomeTarefa){
        this.tarefas[i].nomeTarefa = tarefa.nomeTarefa;
        this.tarefas[i].concluido = tarefa.concluido;
        break;
      }
    }
  }

  alterarTarefa(tarefa: Tarefa) {
    if(tarefa.id){
      this.serviceTarefa.alterarTarefa(tarefa)
        .subscribe(response => {
          this.ngOnInit();
          this.sucesso = true;
          this.mensagemErro = "";
        },
        errorResponse => {
          this.sucesso = false;
          this.mensagemErro = "Erro ao alterar tarefa";
        }
        );
      this.tarefaSelecionada = new Tarefa();
    } else {
      this.alterarTarefaDaLista(this.tarefaSelecionada);
      this.tarefaSelecionada = new Tarefa();
    }
  }

  consultarTarefasDaLista(idLista:number) {
    this.serviceTarefa.consultarPorLista(idLista)
    .subscribe(response => this.tarefas = response);
  }

  adicionar(){
    if(this.novaTarefa.nomeTarefa == undefined){
      this.mensagemErro = "Nome da tarefa é obrigatorio.";
      return;
    }

    this.tarefas.push(this.novaTarefa);
    this.novaTarefa = new Tarefa();
    this.novaTarefa.nomeTarefa = "";
  }

}
