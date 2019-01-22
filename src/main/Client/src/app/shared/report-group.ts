import {DomainBase} from '../utils/utils';
import {Report} from "./report";


export interface ReportGroup extends DomainBase
{
  label: string;
  reportList: Report[];
}
