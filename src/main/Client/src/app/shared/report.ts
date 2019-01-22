import {DomainBase} from '../utils/utils';
import {FilterData} from "../components/interfaces";
import {ReportFilterItem} from "./report-filter-item";


export interface Report extends DomainBase
{
  nome: string;
  label: string;
  needFilter: boolean;
  itemList: ReportFilterItem[];
  filterData: FilterData;
}
