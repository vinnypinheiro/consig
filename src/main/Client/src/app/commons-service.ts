import {FilterData} from './components/interfaces';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/index';
import {Injectable} from '@angular/core';
import {DomainBase} from './utils/utils';
import {map} from "rxjs/operators";
import { environment  } from "./environments/environment";
import * as stringifyCustom from 'json-stringify-safe';

export class ServerResponse<T extends DomainBase> {
  content: any;
  filterData: FilterData;
  success: boolean;
  message: string;
}

@Injectable()
export abstract class CommonsService<T extends DomainBase> {

  protected API_ROOT_URL = environment.getBaseURL();
  reportName = "";

  constructor(private http: HttpClient) {
  }

  abstract getPathModule(): string;

  public loadByFilter(filterData: FilterData): Observable<ServerResponse<T>> {
    return this.http.post<ServerResponse<T>>(`${this.API_ROOT_URL}/${this.getPathModule()}/`, filterData);
  }

  public save(bean: DomainBase): Observable<ServerResponse<T>> {
    console.log(bean);
    return this.http.post<ServerResponse<T>>(`${this.API_ROOT_URL}/${this.getPathModule()}/save`, JSON.parse(stringifyCustom(bean)));
  }

  public loadQuery(filterData: FilterData, queryId: string): Observable<ServerResponse<T>> {
    return this.http.post<ServerResponse<T>>(`${this.API_ROOT_URL}/${this.getPathModule()}/q/${queryId}`, filterData);
  }

  public findById(id: string): Observable<ServerResponse<T>> {
    return this.http.get<ServerResponse<T>>(`${this.API_ROOT_URL}/${this.getPathModule()}/${id}`);
  }

  public delete(id: string): any {
    return this.http.delete<ServerResponse<T>>(`${this.API_ROOT_URL}/${this.getPathModule()}/${id}`);
  }

  public hashCode(value): any {
    var h = 0, l = value.length, i = 0;
    if (l > 0)
      while (i < l)
        h = (h << 5) - h + value.charCodeAt(i++) | 0;
    return h;
  };

  printReport(reportName: string){
    this.reportName = reportName;

    if(this.reportName !== null && this.reportName !== ""){
      this.downloadPDF().subscribe(
        (res) => {
          var fileURL = URL.createObjectURL(res);
          window.open(fileURL);
        }
      );
    }
  }


  downloadPDF(): any {
    let filter: FilterData = <FilterData>{
      page: 1,
      limit: 15,
      whereClauses: []
    };

    return this.http.post(this.API_ROOT_URL+'/reports/' + this.reportName, filter,{ responseType: 'blob'})
      .pipe(map(res => {
        return new Blob([res], { type: 'application/pdf', });
      }));
  }
}
