
import {Injectable} from "@angular/core";
import {Associado} from './associado'; 
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class AssociadoService extends CommonsService<Associado> { 

     getPathModule(): string { 
         return 'associado'; 
     } 
} 
