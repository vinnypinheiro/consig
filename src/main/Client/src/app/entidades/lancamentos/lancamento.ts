import {DomainBase} from '../../utils/utils';
import {Emprestimo} from "../emprestimo/emprestimo";
import {Correspondente} from "../correspondente/correspondente";
import {Auxilio} from "../auxilio/auxilio";
import {Mensalidade} from "../mensalidade/mensalidade";
import {Ocorrencia} from "../ocorrencia/ocorrencia";

export interface Lancamento extends DomainBase {

     nome: string;
}