import { Component, AfterViewInit } from '@angular/core';

@Component({
  selector: '[app-header]',
  templateUrl: './app-header.component.html',
})
export class AppHeader implements AfterViewInit {

  constructor(
  ) { }

  usuario: any ;
  activeUser: any;
  activeUserNome: any;
  activeUserId: number;
  activeUserRole: string;

  ngAfterViewInit()  {

    this.usuario = JSON.parse(localStorage.getItem( "loginResponse"));
    console.log(this.usuario);
    this.activeUser = this.usuario.usuario;
    this.activeUserNome = this.activeUser.nome;
    this.activeUserId = this.activeUser.id;
    this.activeUserRole = this.activeUser.setor;
    console.log(this.activeUser.id);

	}

}
