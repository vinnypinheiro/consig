
import {Injectable} from "@angular/core";
import {Auxilio} from './auxilio';
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class AuxilioService extends CommonsService<Auxilio> {

     getPathModule(): string { 
         return 'auxilio';
     } 
} 
