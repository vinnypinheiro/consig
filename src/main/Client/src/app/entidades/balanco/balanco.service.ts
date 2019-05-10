
import {Injectable} from "@angular/core";
import {Balanco} from './balanco';
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class BalancoService extends CommonsService<Balanco> {

     getPathModule(): string { 
         return 'balanco';
     } 
} 
