import { Component, OnInit, AfterViewInit } from '@angular/core';

import { ScriptLoaderService } from '../../_services/script-loader.service';

declare var $:any;


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile-style.css']
})
export class ProfileComponent implements OnInit, AfterViewInit {

  constructor(private _script: ScriptLoaderService) { }

  ngOnInit() {
  }

  ngAfterViewInit() { 
    this._script.load('./assets/js/scripts/form-plugins.js');

     // Select 2
    
     $(".select2_tipo").select2({
         placeholder: "Pesquisar...",
         allowClear: true,
         width:"100%",
     });

    $('#date_1 .input-group.date').datepicker({
        todayBtn: "linked",
        orietation: 'bottom',
        language: 'pt-BR',
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: true,
        autoclose: true
    });

    $('#dataguia.input-group.date').datepicker({
        todayBtn: "linked",
        orietation: 'bottom',
        language: 'pt-BR',
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: true,
        autoclose: true
    });

    $('#datatable').DataTable({
      pageLength: 10,
      fixedHeader: true,
      responsive: true,
      "sDom": 'rtip',
      columnDefs: [{
          targets: 'no-sort',
          orderable: false
      }]
  });

  

  $('#guias-table').DataTable({
    fixedHeader: true,
    responsive: true,
    "bFilter" : false,               
"bLengthChange": false,
"bInfo": false,
"bPaginate": false
});
  
  $('#ocorrencias').DataTable({
    pageLength: 10,
    fixedHeader: true,
    responsive: true,
    "sDom": 'rtip',
    columnDefs: [{
        targets: 'no-sort',
        orderable: false
    }]
});

  var table = $('#datatable').DataTable();
  $('#key-search').on('keyup', function() {
      table.search(this.value).draw();
  });
  $('#type-filter').on('change', function() {
      table.column(4).search($(this).val()).draw();
  });
  }

}
