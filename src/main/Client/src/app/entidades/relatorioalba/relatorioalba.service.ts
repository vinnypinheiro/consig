
import {Injectable} from "@angular/core";
import {RelatorioAlba} from './relatorioAlba';
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class RelatorioalbaService extends CommonsService<RelatorioAlba> {

     getPathModule(): string { 
         return 'relatorioAlba';
     } 
} 
