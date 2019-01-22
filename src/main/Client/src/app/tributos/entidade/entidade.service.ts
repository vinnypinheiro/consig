import {CommonsService} from '../../commons-service'; 
import {Injectable} from "@angular/core";
import {Entidade} from './entidade'; 

@Injectable({ providedIn: 'root' }) 
export class EntidadeService extends CommonsService<Entidade> { 

     getPathModule(): string { 
         return 'entidade'; 
     } 
} 
