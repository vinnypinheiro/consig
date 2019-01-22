import {Component, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {NG_VALUE_ACCESSOR, NgModel} from '@angular/forms';
import {ValueAccessorBase} from '../value-accessor';
import {StateButons} from '../componentsClass';

declare var $: any;
let identifier = 0;
let modalIdentifier = 0;

@Component({
    selector: 'gov-lookup-field',
    templateUrl: './lookup-field.component.html',
    styleUrls: ['./lookup-field.component.css'],
    providers: [
        {provide: NG_VALUE_ACCESSOR, useExisting: LookupFieldComponent, multi: true}
    ],
})
export class LookupFieldComponent extends ValueAccessorBase<string> {
    @ViewChild(NgModel) model: NgModel;
    @Input() readonly: boolean;
    @Input() name: string;
    @Input() code: string;
    @Input() description: string;
    @Input() class: string;
    @Output() lookupCodeChange: EventEmitter<any> = new EventEmitter();
    @Output() descriptionChange: EventEmitter<any> = new EventEmitter();
    public edicao = true;
    public identifier = `gov-lookup-field-${identifier++}`;
    public modalIdentifier = `modal-form-${modalIdentifier++}`;
    rowSelected: any;
    page: number;
    total_pages: number;
    public dataList: any[];
    public stateButons = <StateButons>{};
    private codeOld: string = null;

    public evaluateQueryReturn(data: any, page: number, total_pages: number) {
        this.page = page;
        this.total_pages = total_pages;
        this.updateStatus(this.page, this.total_pages);
        if (data) {

            if ((data.length == 1) && (!$('#'+this.modalIdentifier).is(':visible'))) {
                this.value = data[0].id;
                this.code = data[0].codigo;
                this.description = data[0].nome;
                this.descriptionChange.emit(this.description);
            } else {
                this.dataList = data;
                $('#'+this.modalIdentifier).modal();
            }
        }
    }

    showDialog() {
        $('#'+this.modalIdentifier).modal();
    }

    public onCodeChange(value): void {
        if (!this.codeOld) {
            this.codeOld = this.code;
        }
        this.code = value;
        this.emitLookupCodeChange(this.code, 1);
    }

    onButtonActionClick(button: string): void {

        switch (button) {
            case 'first': {
                this.page = 1;
                this.emitLookupCodeChange(this.code, this.page);
                break;
            }
            case 'prior': {
                --this.page;
                this.emitLookupCodeChange(this.code, this.page);
                break;
            }
            case 'next': {
                ++this.page;
                this.emitLookupCodeChange(this.code, this.page);
                break;
            }
            case 'last': {
                this.page = this.total_pages;
                this.emitLookupCodeChange(this.code, this.page);
                break;
            }
        }
    }

    updateStatus(page: number, total_pages: number): void {
        this.stateButons.first = (page !== 1);
        this.stateButons.prior = (page !== 1);
        this.stateButons.next = (page !== total_pages) && (total_pages !== 0);
        this.stateButons.last = (page !== total_pages) && (total_pages !== 0);
    }

    onCancelClick() {
        this.code = this.codeOld;
        this.codeOld = null;
        $('#'+this.modalIdentifier).modal('hide');

    }

    onConfirmSelectRow() {
        this.value = this.rowSelected.id;
        this.code = this.rowSelected.codigo;
        this.description = this.rowSelected.nome;
        this.codeOld = null;
        $('#'+this.modalIdentifier).modal('hide');
      this.descriptionChange.emit(this.description);
    }

    onSelectRow(row: any) {
        this.rowSelected = (this.rowSelected == row) ? null : row;
    }

    private emitLookupCodeChange(value: string, page: number): void {
        this.lookupCodeChange.emit({lookup: this, name: this.name, code: value, page: page});
    }

}


