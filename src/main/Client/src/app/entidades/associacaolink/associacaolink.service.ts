
import {Injectable} from "@angular/core";
import { CommonsService } from "../../commons-service";
import {Associacaolink} from "./associacaolink";

@Injectable({ providedIn: 'root' }) 
export class AssociacaolinkService extends CommonsService<Associacaolink> {

     getPathModule(): string { 
         return 'associacaolink';
     } 
} 
