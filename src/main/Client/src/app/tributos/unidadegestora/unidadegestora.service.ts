import {CommonsService} from '../../commons-service'; 
import {Injectable} from "@angular/core";
import {UnidadeGestora} from './unidadegestora'; 

@Injectable({ providedIn: 'root' }) 
export class UnidadeGestoraService extends CommonsService<UnidadeGestora> { 

     getPathModule(): string { 
         return 'unidadegestora'; 
     } 
} 
