import {Component, OnInit } from '@angular/core';

@Component({
  selector: '[app-sidebar]',
  templateUrl: './app-sidebar.component.html'
})
export class AppSidebar  implements OnInit {

  usuario: any ;
  activeUser: any;
  activeUserNome: any;
  activeUserId: number;
  activeUserRole: string;

  ngOnInit() {

    this.usuario = JSON.parse(localStorage.getItem( "loginResponse"));

    this.activeUser = this.usuario.usuario;
    this.activeUserNome = this.activeUser.nome;
    this.activeUserId = this.activeUser.id;
    this.activeUserRole = this.activeUser.setor;

  }
}
