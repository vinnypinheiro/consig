
import {Injectable} from "@angular/core";
import { CommonsService } from "../../commons-service";
import {Lancamento} from "./lancamento";

@Injectable({ providedIn: 'root' }) 
export class LancamentoService extends CommonsService<Lancamento> {

     getPathModule(): string { 
         return 'lancamento';
     } 
} 
