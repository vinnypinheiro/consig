
import {Injectable} from "@angular/core";
import {ContaReceber} from './contaReceber';
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class ContaReceberService extends CommonsService<ContaReceber> {

     getPathModule(): string { 
         return 'contaReceber';
     } 
} 
