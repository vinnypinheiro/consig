import {DomainBase} from '../../utils/utils';

export interface Ocorrencia extends DomainBase {

     descricao: string;
     observacao: string;
     usuario: string;
     dats: Date;
}