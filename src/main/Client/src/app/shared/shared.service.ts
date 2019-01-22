import {LoginResponse} from "../security/login-response";
import {Inject, Injectable} from "@angular/core";
import {FilterData, WhereClause} from "../components/interfaces";
import {map} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import { DOCUMENT } from '@angular/platform-browser';
import {Report} from "./report";
import {DataType, FieldSearch, MatchType} from "../utils/utils";


@Injectable()
export class SharedService {

  public loginResponse:LoginResponse;
  public isLogged: boolean;
  public theme:string = "";

  public static beanIdSelected: any;

  constructor(private http: HttpClient,@Inject(DOCUMENT) private document) {
    this.updateLoginDetails();

  }

  public theme1(){
    console.log( this.document.getElementById('theme'));
    this.document.getElementById('theme').setAttribute('href', '../assets/css/theme1.css');
  }

  public defaultTheme(){
    console.log( this.document.getElementById('theme'));
    this.document.getElementById('theme').setAttribute('href', '');
  }

  public updateLoginDetails(){
    this.isLogged = localStorage.getItem('isLogged') === 'true';
    this.loginResponse = JSON.parse(localStorage.getItem("loginResponse"));
  }

  printReport(reportname:string,id:number){
      this.downloadPDF(reportname,id).subscribe(
        (res) => {
          let fileURL = URL.createObjectURL(res);
          window.open(fileURL);
        }
      );
  }

  downloadPDF(reportname:string,id:number): any {

    //if (report.needFilter){
     // report.filterData = this.builderFilterData(report.nome);
    //}


    return this.http.get(environment.getBaseURL()+'/report/' + reportname+"/"+ id,{ responseType: 'blob'})
      .pipe(map(res => {
        return new Blob([res], { type: 'application/pdf', });
      }));
  }

  builderFilterData(nome: string): FilterData{

    let filter: FilterData;

    switch (nome) {

      default:
        filter = <FilterData>{
          limit: 1,
          whereClauses: <WhereClause[]>[{
            field: <FieldSearch>{label: "id", name: "id", type: "long"},
            dataType: DataType.LONG,
            matchType: MatchType.EQUALS,
            iniValue: SharedService.beanIdSelected
          }]
        };
        break;
    }

    return filter;
  }
}
