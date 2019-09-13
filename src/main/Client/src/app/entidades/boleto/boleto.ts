import {DomainBase} from '../../utils/utils';
import * as moment from "moment";
import _date = moment.unitOfTime._date;

export interface Boleto extends DomainBase {


  linhaDigitavel : string;
  codigoBarras : string;
  url : string;
  dataDocumento: Date;
  dataProcessamento: Date;
  dataVencimento: Date;
  dataPagamento: Date;
  numeroDocumento : string;
  nossoNumero : string;
  valorDocumento : number;
  cobranca_id: number;
  banco_id: number;


}
