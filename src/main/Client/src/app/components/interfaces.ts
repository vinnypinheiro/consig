import {StateButons} from './componentsClass';
import {FieldSearch} from "../utils/utils";

export interface WhereClause {
    table: string;
    Label: string;
    name: string;
    iniValue: any;
    endValue: any;
    matchType: any;
    caseSensitive: boolean;
    dataType: any;
    whereType: string;
    dataTypeName: string;
    group: number;
    field:FieldSearch;
}

export interface FilterData {
    count: number;
    page: number;
    limit: number;
    totalPages: number;
    order: string;
    whereClauses: WhereClause[];
    codigoField: string;
    nomeField: string;
}


export interface ToolBarManager {

    onNavigateClick(filterData: FilterData): void;

    // onLocateClick(): void;
    //
    // onOpenClick(): void;
    //
    // onPrintClick(): void;
    //
    // onNewClick(): void;
    //
    // onCopyClick(): void;
    //
    // onDeleteClick(): void;
    //
    // onCloseClick(): void;

    getFilterData(): FilterData;

    getStateButtons(): StateButons;

    // updateStatusBar(): void;
}


