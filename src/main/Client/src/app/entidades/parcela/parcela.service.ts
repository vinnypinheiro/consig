
import {Injectable} from "@angular/core";
import {Parcela} from './parcela';
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class ParcelaService extends CommonsService<Parcela> {

     getPathModule(): string { 
         return 'parcela'; 
     } 
} 
