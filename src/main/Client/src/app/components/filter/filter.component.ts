import {AfterContentInit, Component, Input, OnInit} from "@angular/core";
import {FilterData, WhereClause} from "../interfaces";
import {isUndefined} from "util";
import {FieldSearch} from "../../utils/utils";
import {CommonsGrid} from "../../commons-grid";

@Component({
  selector: 'gov-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements AfterContentInit {


  @Input()
  grid: CommonsGrid<any>;

  searchableFields: any;
  filterData: FilterData;
  tableName: string;

  ngAfterContentInit():void {
    this.searchableFields = this.grid.getFieldList();
    this.filterData = this.grid.getFilterData();
    this.tableName = this.grid.getTableName();
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

    if (this.filterData.whereClauses.length < 1) {
      this.addBlankWhereClause();
    }
  }

  addBlankWhereClause(){
    this.filterData.whereClauses.push(<WhereClause>{
      table: '', name: '', dataType: '', matchType: 'LIKE', whereType: 'AND'
    });
  }

  removeWhereClause(index: number){
      this.filterData.whereClauses.splice(index, 1);
  }

  prepareFilter(filter: FilterData){
    for( let whereclause of filter.whereClauses){
      whereclause.name = whereclause.field.name;
      whereclause.dataType = whereclause.field.type;
      whereclause.table = this.tableName;
    }
    return filter;
  }

  customCompareField(o1: FieldSearch, o2: FieldSearch) {
    let s1 = o1?o1.name:'';
    let s2 = o2?o2.name:'';
    return s1 == s2;
  }



}
