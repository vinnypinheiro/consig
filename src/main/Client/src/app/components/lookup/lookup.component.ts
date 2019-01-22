import {AfterViewInit, Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {Subscription} from "rxjs";
import {CommonsService} from "../../commons-service";

declare var $: any;

@Component({
    selector: 'app-lookup',
    templateUrl: './lookup.component.html',
    styleUrls: ['./lookup.component.css']
})

export class LookupComponent implements OnInit, AfterViewInit {
    @ViewChild('lookup')
    private lookupEl: ElementRef;

    @Input()
    public fieldLabel: string;

    @Input()
    public isLocal: boolean;

    @Input()
    public searchableFields: string[] = [];

    private $field;
    private $dialog;
    private requestSubscription: Subscription;
    private searchTimeout;
    public sourceData: any[] = [];
    public localData: any[] = [];



    //
  public apiService: CommonsService<any>;


    constructor() {
    }

    ngOnInit(): void {
        this.$field = $(this.lookupEl.nativeElement).find('.lookup-field');
        this.$dialog = $(this.lookupEl.nativeElement).find('.lookup-dialog');

        if (this.isLocal) {
            this.initData();
        }

        $(window).on('resize.lookup', () => this.updateDialogPosition());
        $(document).on('click touchend', (e) => this.onClickOut(e));

        this.$field.find('.lookup-key')
            .on('input', (e) => this.onInput(e))
            .on('focus', (e) => this.onFocus(e))
            .on('blur', (e) => this.onBlur(e));
    }

    ngAfterViewInit(): void {
        this.updateDialogPosition();
    }

    onBlur(e): void {
        let $lookup = $(this.lookupEl.nativeElement);

        if (!$lookup.find(e.relatedTarget || e.target).length && this.isDialogVisible()) {
            this.hideDialog();
        }
    }

    onClickOut(e) {
        let $lookup = $(this.lookupEl.nativeElement);

        if (!$lookup.find(e.target).length && this.isDialogVisible()) {
            this.hideDialog();
        }
    }

    onFocus(e): void {
        // if (!!e.target.value.trim()) {
        //     this.showDialog();
        // }
      this.showDialog();
    }

    onInput(e): void {
        if (!!e.target.value.trim()) {

            if (this.isLocal) {

                this.sourceData = this.localData.filter((value) => {
                    return this.searchableFields.some((field) => {
                        return (value[field] && value[field].toLowerCase().indexOf(e.target.value.trim().toLowerCase()) > -1);
                    }, this);

                }, this);

                this.showDialog();

            } else {
                this.$field.find('.lookup-loading').show().siblings('.lookup-search').hide();

                let params = {
                    values: {
                        'fase': 'pagamento',
                        'modalidade': 'despesa',
                        'rowsPerPage': 10000,
                        'page': 1,
                        'pagamento.data': ['2018-01-01', '2018-09-30'],
                        'fornecedor.nome': e.target.value.trim()
                    }
                };

                if (this.requestSubscription) {
                    this.requestSubscription.unsubscribe();
                }

               /* this.requestSubscription = this.apiService.post('http://localhost:8080/1188/despesas/list', params).subscribe((response) => {
                    this.$field.find('.lookup-loading').hide().siblings('.lookup-search').show();
                    this.sourceData = response.data;

                    if (response.data.length == 0) {
                        this.hideDialog();
                        this.sourceData.length = 0;
                    } else {
                        this.sourceData = response.data;
                        this.showDialog();
                    }
                }, (error: any) => {
                    console.log('error: ', error)
                })*/
            }

        } else {
            this.$field.find('.lookup-loading').hide().siblings('.lookup-search').show();
            this.hideDialog();

            if (this.requestSubscription) {
                this.requestSubscription.unsubscribe();
            }
        }
    }

    isDialogVisible(): boolean {
        return !this.$dialog.is(':hidden');
    }

    hideDialog(): void {
        this.$field.toggleClass('open', false);
        this.$dialog.fadeOut(0);
    }

    showDialog(): void {
        this.updateDialogPosition();

        if (this.sourceData.length > 0) {
            if (!this.isDialogVisible()) {
                this.$field.toggleClass('open', true);
                this.$dialog.fadeIn(200);
                this.$dialog.scrollTop(0);
            }
        } else {
            this.hideDialog();
        }
    }

    onClick(e) {
        let index = $(e.target).closest('.detail').data('index'),
            data = this.sourceData[index];

        console.log(data);
        this.hideDialog();

        this.$field.find('.lookup-key').val(data.numero + " - " +data.descricao);
        this.$field.find('.lookup-description').val(data.descricao);
    }

    updateDialogPosition(): void {
        let offset: any = {},
            fieldOffset = this.$field.position();

        offset.top = fieldOffset.top + this.$field.height();
        offset.width = this.$field.width();
        offset.left = fieldOffset.left;

        this.$dialog.css(offset);
    }

    initData() {
        this.localData.push({
                "id": "1",
                "nome": "Afonso Cláudio",
                "codigo": "8"
            }, {
                "id": "2",
                "nome": "Água Doce do Norte",
                "codigo": "8"
            }, {
                "id": "3",
                "nome": "Águia Branca",
                "codigo": "8"
            }
        )
    }
}
