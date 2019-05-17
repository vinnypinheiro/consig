
import {Injectable} from "@angular/core";
import {Corretor} from './corretor';
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class CorretorService extends CommonsService<Corretor> {

     getPathModule(): string { 
         return 'corretor';
     } 
} 
