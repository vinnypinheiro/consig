import {CommonsService} from '../../commons-service'; 
import {Injectable} from "@angular/core";
import {Setor} from './setor';

@Injectable({ providedIn: 'root' }) 
export class SetorService extends CommonsService<Setor> {

     getPathModule(): string { 
         return 'setor';
     } 
} 
