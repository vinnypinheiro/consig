
import {Injectable} from "@angular/core";
import {Emprestimo} from './emprestimo';
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class EmprestimoService extends CommonsService<Emprestimo> {

     getPathModule(): string { 
         return 'Emprestimo';
     } 
} 
