import {CommonsService} from '../../commons-service'; 
import {Injectable} from "@angular/core";
import {Ocorrencia} from "./Ocorrencia";


@Injectable({ providedIn: 'root' }) 
export class OcorrenciaService extends CommonsService<Ocorrencia> {

     getPathModule(): string { 
         return 'ocorrencia';
     } 
}
