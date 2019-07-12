
import {Injectable} from "@angular/core";
import {Dashboard} from './dashboard';
import { CommonsService } from "../../commons-service";

@Injectable({ providedIn: 'root' }) 
export class DashboardService extends CommonsService<Dashboard> {

     getPathModule(): string { 
         return 'dashboard';
     } 
} 
