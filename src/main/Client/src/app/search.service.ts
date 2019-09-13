import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map, tap} from 'rxjs/operators';
import {User, IUserResponse} from "./utils/profiss.class";
import { Associado } from './entidades/associado/associado';
import { AssociadoService } from './entidades/associado/associado.service';

@Injectable()
export class SearchService {
  selectProfisData: Associado[];

  constructor(private http: HttpClient, associadoService: AssociadoService) {}

  search(filter: {nome: string} = {nome: ''}, page = 1): Observable<IUserResponse> {
    return this.selectProfisData =  JSON.parse(localStorage.getItem( "profissionais1"))
            .map(user => new User(user.id, user.nome))
            // Not filtering in the server since in-memory-web-api has somewhat restricted api
            .filter(user => user.nome.includes(filter.nome))


  }
}
