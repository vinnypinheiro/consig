
import {Injectable} from "@angular/core";
import {Associacao} from './associacao'; 
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class AssociacaoService extends CommonsService<Associacao> { 

     getPathModule(): string { 
         return 'associacao'; 
     } 
} 
