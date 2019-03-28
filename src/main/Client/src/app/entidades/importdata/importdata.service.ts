
import {Injectable} from "@angular/core";
import {Importdata} from './importdata'; 
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class ImportdataService extends CommonsService<Importdata> { 

     getPathModule(): string { 
         return 'importdata'; 
     } 
} 
