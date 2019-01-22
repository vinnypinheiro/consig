import {Component, OnInit} from '@angular/core'; 
import {ActivatedRoute, Router} from '@angular/router'; 
import {CommonsForm} from '../../../commons-form'; 
import {FilterData} from '../../../components/interfaces'; 
import {CommonsService} from '../../../commons-service'; 
import {Usuario} from '../usuario';
import {UsuarioService} from '../usuario.service';

@Component({
 selector: 'gov-usuario-form', 
 templateUrl: './usuario-form.component.html', 
 //styleUrls: ['./usuario-form.component.css'] 
}) 
export class UsuarioFormComponent extends CommonsForm<Usuario> implements OnInit {

    constructor(apiService: UsuarioService,
                route: ActivatedRoute, 
                router: Router ) {
         super(apiService, route, router); 
    }

    ngOnInit() {
        super.ngOnInit();
    }

    getLookupService(lookupName: string): CommonsService<any> {
        switch (lookupName) {
            default: {
                console.log('serviço não disponibilizado para ', lookupName)
                return this.apiService;
            }
        }
    }

    getDeLookupFilter(lookupValue: any): FilterData {
        switch (lookupValue.name) {
            default: {
                console.log('filtro não disponibilizado para ', lookupValue.name)
            }
        }
        return null;
    }

    onButtonActionClick(): void {
        //console.log(this.activeBean.usuario.id);
    }
} 

