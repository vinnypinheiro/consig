import {CommonsService} from '../../commons-service'; 
import {Injectable} from "@angular/core";
import {Usuario} from './usuario';

@Injectable({ providedIn: 'root' }) 
export class UsuarioService extends CommonsService<Usuario> {

     getPathModule(): string { 
         return 'usuario';
     } 
} 
