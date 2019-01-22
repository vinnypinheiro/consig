
import {Injectable} from "@angular/core";
import {Convenio} from './convenio';
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class ConvenioService extends CommonsService<Convenio> {

     getPathModule(): string { 
         return 'convenio';
     } 
} 
