import {CommonsService} from '../../commons-service'; 
import {Injectable} from "@angular/core";
import {Operador} from './operador'; 

@Injectable({ providedIn: 'root' }) 
export class OperadorService extends CommonsService<Operador> { 

     getPathModule(): string { 
         return 'operador'; 
     } 
} 
