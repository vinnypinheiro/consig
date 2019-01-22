import {AfterContentInit, Component, Input, OnInit} from "@angular/core";
import {FilterData, WhereClause} from "../interfaces";
import {isUndefined} from "util";
import {ReportGroup} from "../../shared/report-group";
import {environment} from "../../../environments/environment";
import {map} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {Report} from "../../shared/report";
import {SharedService} from "../../shared/shared.service";

@Component({
  selector: 'gov-report-filter',
  templateUrl: './report-filter.component.html',
  styleUrls: ['./report-filter.component.css']
})
export class ReportFilterComponent implements AfterContentInit {

  @Input()
  reportGroups: ReportGroup[];
  selectedReport: Report = <Report>{};

  filterData: FilterData;


  constructor(private http: HttpClient, private sharedService: SharedService) {
  }

  ngAfterContentInit(): void {
    this.buildFilter();

  }

  buildFilter() {
    if (isUndefined(this.filterData)) {
      this.filterData = <FilterData>{
        page: 1,
        limit: 15,
        whereClauses: []
      };
    }

    if (isUndefined(this.filterData.whereClauses)) {
      this.filterData.whereClauses = [];
    }

    let reportGroup: any;
    let report: any;
    let item: any;

    for (reportGroup in this.reportGroups) {
      for (report in reportGroup.reportList) {
        for (item in report.itemList) {
          report.filterData.whereClauses.push(<WhereClause>{table: item.table,
                                                            Label: item.label,
                                                            name: item.field,
                                                            iniValue: "",
                                                            endValue: "",
                                                            matchType: "",
                                                            caseSensitive: false,
                                                            dataType: "",
                                                            whereType: "",
                                                            dataTypeName: item.dataTypeName,
                                                            group: 0,
                                                            field:null});
        }
      }
    }

  }

  callReport(report: Report) {
    this.printReport(report.nome);
  }

  printReport(reportName: string) {
    this.sharedService.printReport(null,0);
  }

  downloadPDF(reportName: string): any {
    let filter: FilterData = <FilterData>{
      page: 1,
      limit: 15,
      whereClauses: []
    };

    return this.http.post(environment.getBaseURL() + '/reports/' + reportName, filter, {responseType: 'blob'})
      .pipe(map(res => {
        return new Blob([res], {type: 'application/pdf',});
      }));
  }

}
