import {CommonsService} from '../../commons-service'; 
import {Injectable} from "@angular/core";
import { Boleto} from './boleto'

@Injectable({ providedIn: 'root' }) 
export class BoletoService extends CommonsService<Boleto> {

     getPathModule(): string { 
         return 'boleto';
     } 
} 
