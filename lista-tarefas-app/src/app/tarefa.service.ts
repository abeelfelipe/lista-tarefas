import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tarefa } from './lista-tarefas/Tarefa';

@Injectable({
  providedIn: 'root'
})
export class TarefaService {

  constructor(private http: HttpClient) { }

  salvarTarefa(tarefa: Tarefa) : Observable<Tarefa> {
    return this.http.post<Tarefa>('http://localhost:8080/api/tarefa',tarefa)
  }

  alterarTarefa (tarefa: Tarefa) : Observable<any> {
    return this.http.put<Tarefa>(`http://localhost:8080/api/tarefa/${tarefa.id}`, tarefa);
  }

  deletarTarefa(tarefa:Tarefa) : Observable<Tarefa> {
    return this.http.delete<Tarefa>(`http://localhost:8080/api/tarefa/${tarefa.id}`);
  }

  consultarPorLista(idLista:number) : Observable<Tarefa[]>{
    const httpParams = new HttpParams().set("idLista", idLista.toString());
    return this.http.get<Tarefa[]>('http://localhost:8080/api/tarefa?' + httpParams);
  }
}
