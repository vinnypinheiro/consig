
import {Injectable} from "@angular/core";
import {Correspondente} from './correspondente'; 
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class CorrespondenteService extends CommonsService<Correspondente> { 

     getPathModule(): string { 
         return 'correspondente'; 
     } 
} 
