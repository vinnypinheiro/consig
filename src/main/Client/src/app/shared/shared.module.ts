import {NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
//import {SideBarComponent} from '../side-bar/side-bar.component';
//import {TopBarComponent} from '../top-bar/top-bar.component';
//import {FooterPageComponent} from '../footer-page/footer-page.component';
//import {MainPageComponent} from '../main-page/main-page.component';
import {LoginComponent} from '../security/login/login.component';
import {LogoutComponent} from '../security/logout/LogoutComponent';
import {CommonModule} from '@angular/common';
import {AuthGuard} from '../security/auth.guard';
import {LoginService} from '../security/login/login.service';
//import {MainPageService} from '../main-page/main-page.service';
import {SharedService} from './shared.service';
import {RouterModule} from '@angular/router';
import {CustomHttpInterceptor} from '../security/CustomHttpInterceptor';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    //AppRoutingModule,
    RouterModule,
    HttpClientModule
  ],
  declarations: [
    LoginComponent,
    LogoutComponent,
  ],
  exports: [
    CommonModule,
    FormsModule,
  ],
  entryComponents: [],
  schemas: [],
  providers: [
    AuthGuard,
    SharedService,
    LoginService,
    {provide: HTTP_INTERCEPTORS, useClass: CustomHttpInterceptor, multi: true},
  ],
  bootstrap: []
})
export class SharedModule {
}
