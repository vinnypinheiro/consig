
import {Injectable} from "@angular/core";
import {Espelhoretorno} from './espelhoretorno';
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class EspelhoRetornoService extends CommonsService<Espelhoretorno> {

     getPathModule(): string { 
         return 'espelhoretorno';
     } 
} 
