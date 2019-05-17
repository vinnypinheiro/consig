import {DomainBase} from '../../utils/utils';
import {Associado} from "../associado/associado";
import {Parcela} from "../parcela/parcela";
import {Associacao} from "../associacao/associacao";
import {Convenio} from "../convenio/convenio";
import {Correspondente} from "../correspondente/correspondente";
import {VerbaDesconto} from "../verbadesconto/verbadesconto";

export interface Associacaolink extends DomainBase {


    associacao_id: Associacao;
    associado_id: Associado;

}