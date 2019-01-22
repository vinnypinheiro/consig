import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {StateButons} from "../componentsClass";
import {HttpClient} from "@angular/common/http";
import {ReportGroup} from "../../shared/report-group";
import {SharedService} from "../../shared/shared.service";

@Component({
  selector: 'gov-tool-bar',
  templateUrl: './tool-bar.component.html',
  styleUrls: ['./tool-bar.component.css']
})
export class ToolBarComponent implements OnInit {

  @Input()
  reportGroups: ReportGroup[];

  @Input() title: string;

  @Output()
  buttonActionClick: EventEmitter<string> = new EventEmitter();

  @Output()
  filterRowValueChange: EventEmitter<string> = new EventEmitter();

  filterRowValue: string;


  private _page: string;
  private _pages: string;
  private _stateButons: StateButons;

  constructor(private http: HttpClient, public sharedService: SharedService) {
  }

  ngOnInit(): void {
    this._stateButons.new = true;
  }

  get page(): string {
    return this._page;
  }

  get pages(): string {
    return this._pages;
  }

  get stateButons(): StateButons {
    return this._stateButons;
  }

  @Input('stateButons')
  set stateButons(value: StateButons) {
    this._stateButons = value;
  }

  onButtonActionClick(button: string): void {
    this.buttonActionClick.emit(button);
  }

  updateStatus(total_pages: number, page: number): void {
    this._page = page.toString();
    this._pages = total_pages.toString();

    this.stateButons.first = (page !== 1);
    this.stateButons.prior = (page !== 1);
    this.stateButons.next = (page !== total_pages) && (total_pages !== 0);
    this.stateButons.last = (page !== total_pages) && (total_pages !== 0);
  }

  onFilterRowButtonClick() {
    //console.log(this.filterRowValue);
    this.filterRowValueChange.emit(this.filterRowValue);
  }
}
