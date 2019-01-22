import {DomainBase} from '../utils/utils';


export interface ReportFilterItem extends DomainBase
{
  table: string;
  field: string;
  label: string;
  dataTypeName: string;
}
