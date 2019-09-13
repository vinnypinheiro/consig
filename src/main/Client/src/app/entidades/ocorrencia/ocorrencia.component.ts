import { Component, OnInit, AfterViewInit } from '@angular/core';

declare var $:any;

@Component({
  selector: 'app-ocorrencia',
  templateUrl: './ocorrencia.component.html'
})
export class OcorrenciaComponent implements OnInit, AfterViewInit {

  constructor() { }

  ngOnInit() {}

  ngAfterViewInit() {
    $('#ocorrenciatable').DataTable({
        pageLength: 10,
        fixedHeader: true,
        responsive: true,
        "sDom": 'rtip',
        columnDefs: [{
            targets: 'no-sort',
            orderable: false
        }]
    });

    var table = $('#ocorrenciatable').DataTable();
    $('#key-search').on('keyup', function() {
        table.search(this.value).draw();
    });
    $('#type-filter').on('change', function() {
        table.column(4).search($(this).val()).draw();
    });
  }

}
