import {DomainBase} from '../../utils/utils';
import {Associado} from "../associado/associado";
import {Associacao} from "../associacao/associacao";

export interface Correspondente extends DomainBase { 

     razaosocial: string;
     nomefantasia: string;
     inscricaoestadual: string;
     inscricaomunicipal: string;
     cnpj: string;
     endereco: string; 
     cidade: string;
     uf: string;
     cep: string;
     bairro: string;
     telefone: string;
     email: string;
     site: string;
     associacao_id: Associacao;
     
}