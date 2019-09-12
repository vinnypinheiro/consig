import {DomainBase, Operation} from './utils/utils';
import {AfterViewInit, HostListener, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CommonsService} from './commons-service';
import {FilterData, WhereClause} from './components/interfaces';
import {LookupFieldComponent} from './components/lookup-field/lookup-field.component';


declare var $: any;

export abstract class CommonsForm<T extends DomainBase> implements OnInit, AfterViewInit {
    Operation = Operation;
    protected beanSubscribe: any;
    protected beanComponent: any;
    protected beanType: any;
    protected beanId: string;
    public activeBean = <T> {};
    public operation: Operation;
    protected lookupFilters = {};
    stickyMenu: boolean = false;


    constructor(public apiService: CommonsService<T>,
                protected route: ActivatedRoute,
                protected router?: Router) {

    }
 
    ngOnInit(): void {

          this.beanSubscribe = this.route.params.subscribe(params => {
              this.beanId = params['id'];
              if(this.beanId === "novo"){
                this.beanId = "";
                this.operation = Operation.CREATE;
              }else {
                this.operation = Operation.SELECT;
                this.apiService.findById(this.beanId).subscribe(response => {
                  this.activeBean = (<any>response);
                });
              }

          });
          
      this.iCheckGreenLoad();
      this.ControlCollapse();
      }

    save(): void {
        this.apiService.save(this.activeBean).subscribe(response => {
            console.log(response);
            this.openGrid();

        });
    }

    update(): void {
      this.apiService.save(this.activeBean).subscribe(response => {
        console.log(response);

      });
    }

   

    ngAfterViewInit(): void {

    }

    onLookupCodeChange(value: any): void {

          if (!this.lookupFilters[value.name]) {
              this.lookupFilters[value.name] = this.getDeLookupFilter(value);
          }

          let filter = this.lookupFilters[value.name];
          filter.page = value.page;
          for (let whereClause of  filter.whereClauses) {
              whereClause.iniValue = value.code;
          }

          this.getLookupService(value.name).loadQuery(filter, `${value.name}Lkp`).subscribe(response => {
              (<LookupFieldComponent>value.lookup).evaluateQueryReturn(response.content, response.filterData.page, response.filterData.totalPages);
          });

    }

    getDefaultFilter() {
        return <FilterData>{
            page: 1,
            limit: 300,
            whereClauses: []
        };
    }

    buildFilter(param: any): FilterData {
        let filter = this.getDefaultFilter();
        filter.page = 1;
        filter.limit = param.limit ? param.limit : 300;
        filter.order = param.order ? param.order : undefined;
        for(let whereClause of param.whereClauses){
            filter.whereClauses.push(whereClause);
        }
        return filter;
    }

    buildLookupFilter(tableName: string): FilterData {
        let filter = this.getDefaultFilter();

        filter.limit = 10;
        filter.order = `${tableName}.nome`;

        filter.whereClauses.push(<WhereClause>{
            table: tableName, name: filter.codigoField == undefined ? 'codigo' : filter.codigoField, dataType: 'STRING', matchType: 'LIKE'
        });

        filter.whereClauses.push(<WhereClause>{
            table: tableName, name: filter.nomeField == undefined ? 'nome' : filter.nomeField, dataType: 'STRING', matchType: 'LIKE', whereType: 'OR'
        });

        return filter;
      }



    abstract getDeLookupFilter(lookupValue: any): FilterData;

    abstract getLookupService(lookupName: string): CommonsService<any>;

    ControlCollapse(){

      // Collapse ibox function
      $('.collapse-link').on('click', function (e) {
        e.preventDefault();
        var ibox = $(this).closest('div.ibox');
        var button = $(this).find('i');
        var content = ibox.children('.ibox-content');
        content.slideToggle(200);
        button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
        ibox.toggleClass('').toggleClass('border-bottom');
        setTimeout(function () {
          ibox.resize();
          ibox.find('[id^=map-]').resize();
        }, 50);
      });

      // Close ibox function
      $('.close-link').on('click', function (e) {
        e.preventDefault();
        var content = $(this).closest('div.ibox');
        content.remove();
      });

      // Fullscreen ibox function
      $('.fullscreen-link').on('click', function (e) {
        e.preventDefault();
        var ibox = $(this).closest('div.ibox');
        var button = $(this).find('i');
        $('body').toggleClass('fullscreen-ibox-mode');
        button.toggleClass('fa-expand').toggleClass('fa-compress');
        ibox.toggleClass('fullscreen');
        setTimeout(function () {
          $(window).trigger('resize');
        }, 100);
      });
    }

  @HostListener('window:scroll', ['$event'])
  handleScroll() {
    const windowScroll = window.pageYOffset;
    if (windowScroll >= 150) {
      this.stickyMenu = true;
    } else {
      this.stickyMenu = false;
    }
  }

  enableEdit(): void {
      if(this.canEdit()){
        this.operation = Operation.UPDATE;
      }else{
        alert("Não autorizado.")
      }

  }

  delete(beanName: string) {
    if(this.canDelete()){
      if (confirm("Tem certeza que quer deletar " + beanName+" ?")) {
        this.apiService.delete(this.activeBean.id).subscribe(response => {
          this.openGrid();
        });
      }
    }else{
      alert("Não autorizado.")
    }
  }

  canEdit(): boolean{
      return true;
  }

  canDelete(): boolean{
    return true;
  }

  openGrid(): void{
    this.router.navigateByUrl(`/${this.apiService.getPathModule()}`);
  }

  saveItems(): void{

  }

  loadItems(): void{

  }


  iCheckGreenLoad(){
    $('.i-checks').iCheck({
      checkboxClass: 'icheckbox_square-green',
      radioClass: 'iradio_square-green',
    });
  }
}
