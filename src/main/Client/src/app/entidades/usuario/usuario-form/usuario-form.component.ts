import {Component, OnInit, AfterViewInit, Input, EventEmitter} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CommonsForm} from '../../../commons-form';
import {FilterData} from '../../../components/interfaces';
import {CommonsService} from '../../../commons-service';
import {Usuario} from '../Usuario';
import {UsuarioService} from '../usuario.service';

import {ToastrService} from 'ngx-toastr';


import {ScriptLoaderService} from '../../../_services/script-loader.service';

declare var $: any;

import {FormBuilder, FormGroup, FormArray, Validators, FormControl} from '@angular/forms';
import {UsuarioGridComponent} from "../usuario-grid/usuario-grid.component";


@Component({
  selector: 'gov-asasasas-form',
  templateUrl: './Usuario-form.component.html',
  //styleUrls: ['./Usuario-form.component.css']
})
export class UsuarioFormComponent extends CommonsForm<Usuario> implements OnInit, AfterViewInit {

  usuario: any;
  activeUser: any;
  @Input() activeEntity;

  constructor(apiService: UsuarioService,
              route: ActivatedRoute,
              private fb: FormBuilder,
              private _script: ScriptLoaderService,
              private toastr: ToastrService,
              router: Router) {
    super(apiService, route, router);
  }

  ngOnInit() {
    this.beanSubscribe = this.route.params.subscribe(params => {
      this.beanId = params['id'];
      this.getUsuarios(this.beanId);

    });

    this.usuario = JSON.parse(localStorage.getItem("loginResponse"));
    console.log(this.usuario);
    this.activeUser = this.usuario.usuario;
    console.log(this.activeUser.id);
  }


  getUsuarios(intityId) {
    this.apiService.findById(intityId).subscribe(response => {
      this.activeBean = (<any>response);

      this.usuarioForm.patchValue({
        id: this.activeBean.id,
        login: this.activeBean.login,
        nome: this.activeBean.nome,
        email: this.activeBean.email,
        senha:this.activeBean.senha,

        dataCadastro: this.activeBean.dataCadastro,
        dataExpiracao: this.activeBean.dataExpiracao,
        setor: this.activeBean.setor,


      });
    });

  }

  // Usuario Form
  usuarioForm = this.fb.group({
    login: [''],
    id: [''],
    nome: [''],
    email: [''],
    senha: [''],

    dataCadastro: [''],
    dataExpiracao: [''],
    setor: ['']

  });

  saveUsuario() {
    this.apiService.save(this.usuarioForm.value).subscribe(response => {
      console.log(response);

    });

    this.toastr.success('', 'Usuário atualizado com sucesso ');

  }

  ngAfterViewInit() {
    this._script.load('./assets/js/scripts/form-plugins.js');
    this._script.load('./assets/js/scripts/toastr-demo.js');


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
      case 'uf': {
        return this.buildLookupFilter('uf');
      }

      default: {
        console.log('filtro não disponibilizado para ', lookupValue.name)
      }
    }
    return null;
  }

  getDefaultFilter(): FilterData {
    const filterData = super.getDefaultFilter();
    return filterData;
  }

} 

