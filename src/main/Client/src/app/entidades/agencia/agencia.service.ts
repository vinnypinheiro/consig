
import {Injectable} from "@angular/core";
import {Agencia} from './agencia'; 
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class AgenciaService extends CommonsService<Agencia> { 

     getPathModule(): string { 
         return 'agencia'; 
     } 
} 
