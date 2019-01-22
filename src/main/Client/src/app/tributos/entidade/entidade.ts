import {DomainBase} from '../../utils/utils'; 

export interface Entidade extends DomainBase { 

  entidade_id: string;
  entidade_codigo: string;
  entidade_nome: string;
  entidade_cnpj: string;

  cidade_id: number;
  cidade_nome: string;
  cidade_codigo: string;

  uf_id: number;
  uf_nome: string;
  uf_codigo: string;
}
