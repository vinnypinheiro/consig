import {DomainBase} from '../../utils/utils';
import {Correspondente} from "../correspondente/correspondente";

export interface Balanco extends DomainBase {

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
     correspondenteList: Correspondente [];
     
}