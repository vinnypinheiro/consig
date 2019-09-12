import {Router} from '@angular/router';
import {ToolBarComponent} from './components/tool-bar/tool-bar.component';
import {OnInit, ViewChild} from '@angular/core';
import {StateButons} from './components/componentsClass';
import {FilterData, WhereClause} from './components/interfaces';
import {CommonsService} from './commons-service';
import {DomainBase, FieldSearch} from './utils/utils';
import {Report} from "./shared/report";
import {ReportFilterItem} from "./shared/report-filter-item";
import {ReportGroup} from "./shared/report-group";
import {SharedService} from "./shared/shared.service";


export abstract class CommonsGrid<T extends DomainBase> implements OnInit {


  public activeBean = <T> {};

  @ViewChild(ToolBarComponent)
  protected toolBar: ToolBarComponent;

  public filterData = <FilterData>{};

  constructor(public apiService: CommonsService<T>, protected router: Router) {
  }

  getFilterData(){
    return this.filterData;
  }

  ngOnInit(): void {
    this.toolBar.stateButons = new StateButons();
    this.loadByFilter(this.getDefaultFilter());
  }

  loadByFilter(filterData: FilterData) {
    this.apiService.loadByFilter(filterData).subscribe(response => {
      /*
      this.dataList = response.content;
      this.filterData = response.filterData;
      this.toolBar.updateStatus(this.filterData.totalPages, this.filterData.page);*/
      this.activeBean = null;
    });
  }

  onButtonActionClick(button: string): void {

    switch (button) {
      case 'first': {
        this.filterData.page = 1;
        this.loadByFilter(this.filterData);
        break;
      }
      case 'prior': {
        --this.filterData.page;
        this.loadByFilter(this.filterData);
        break;
      }
      case 'next': {
        ++this.filterData.page;
        this.loadByFilter(this.filterData);
        break;
      }
      case 'last': {
        this.filterData.page = this.filterData.totalPages;
        this.loadByFilter(this.filterData);
        break;
      }
      case 'open': {
        this.router.navigateByUrl(`/${this.apiService.getPathModule()}/${this.activeBean.id}`);
        break;
      }
      case 'new': {
        this.router.navigateByUrl(`/${this.apiService.getPathModule()}/novo`);
        break;
      }

      case 'print':{

        break;
      }
    }
  }

  onSelectionChange(checked: boolean, selectedBean: any) {
    this.activeBean = checked ? selectedBean : null;
    this.toolBar.stateButons.open = (this.activeBean != null);
    this.toolBar.stateButons.print = (this.activeBean != null);
    this.toolBar.stateButons.copy = (this.activeBean != null);
    this.toolBar.stateButons.delete = (this.activeBean != null);

    SharedService.beanIdSelected = this.activeBean.id;
  }

  onFilterValueChange(value: string) {
    this.filterData = this.getRowFilter();
    for (let whereClause of  this.filterData.whereClauses) {
      whereClause.iniValue = value;
    }
    this.loadByFilter(this.filterData);
  }

  getDefaultFilter() {
    return <FilterData>{
      page: 1,
      limit: 300,
      whereClauses: []
    };
  }

  buildRowFilter(tableName: string) {
    let filter = this.getDefaultFilter();

    filter.whereClauses.push(<WhereClause>{
      table: tableName,
      name: filter.codigoField == undefined ? 'codigo' : filter.codigoField,
      dataType: 'STRING',
      matchType: 'LIKE'
    });

    filter.whereClauses.push(<WhereClause>{
      table: tableName,
      name: filter.nomeField == undefined ? 'nome' : filter.nomeField,
      dataType: 'STRING',
      matchType: 'LIKE',
      whereType: 'OR'
    });

    return filter;
  }

  getFieldList(): FieldSearch[] {
    return [];
  }

  getTableName(): string {
    return 'sem tabela';
  }

  abstract getRowFilter(): FilterData;

  abstract getReportList():ReportGroup[]
}
