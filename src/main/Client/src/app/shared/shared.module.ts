import {NgModule} from "@angular/core";
import {ToolBarComponent} from "../components/tool-bar/tool-bar.component";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {LookupFieldComponent} from "../components/lookup-field/lookup-field.component";
import {LoginComponent} from "../security/login/login.component";
import {LogoutComponent} from "../security/logout/LogoutComponent";
import {CommonModule} from "@angular/common";
import {AuthGuard} from "../security/auth.guard";
import {LoginService} from "../security/login/login.service";
import {SharedService} from "./shared.service";
import {RouterModule} from "@angular/router";
import {CustomHttpInterceptor} from "../security/CustomHttpInterceptor";
import {FilterComponent} from "../components/filter/filter.component";


import {LookupComponent} from "../components/lookup/lookup.component";
import {ReportFilterComponent} from "../components/report-filter/report-filter.component";



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
    ToolBarComponent,
    LookupFieldComponent,
    FilterComponent,
      ReportFilterComponent,
    LookupComponent
  ],
  exports: [
    CommonModule,
    LookupFieldComponent,
    ToolBarComponent,
    FormsModule,
    FilterComponent,
    ReportFilterComponent,
    LookupComponent
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
