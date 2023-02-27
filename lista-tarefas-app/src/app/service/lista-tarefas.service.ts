import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ListaTarefas } from '../model/ListaTarefas';

@Injectable({
  providedIn: 'root'
})
export class ListaTarefasService {

  constructor( private http: HttpClient) { 

  }

  salvarLista(lista: ListaTarefas) : Observable<ListaTarefas> {
    return this.http.post<ListaTarefas>('http://localhost:8080/api/lista-tarefas',lista)
  }

  getListaTarefas() : Observable<ListaTarefas[]> {
    return this.http.get<ListaTarefas[]>('http://localhost:8080/api/lista-tarefas');
  }

  deletar(lista:ListaTarefas) : Observable<ListaTarefas> {
    return this.http.delete<ListaTarefas>(`http://localhost:8080/api/lista-tarefas/${lista.id}`);
  }

  getListaById(id:number) : Observable<ListaTarefas> {
    return this.http.get<ListaTarefas>(`http://localhost:8080/api/lista-tarefas/${id}`);
  }

  alterar(lista:ListaTarefas) : Observable<ListaTarefas> {
    return this.http.put<ListaTarefas>(`http://localhost:8080/api/lista-tarefas/${lista.id}`, lista);
  }
}
