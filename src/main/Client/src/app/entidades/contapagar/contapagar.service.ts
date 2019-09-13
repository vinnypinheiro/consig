
import {Injectable} from "@angular/core";
import {ContaPagar} from './contaPagar';
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class ContaPagarService extends CommonsService<ContaPagar> {

     getPathModule(): string { 
         return 'contaPagar';
     } 
} 
