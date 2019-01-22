import {DomainBase} from '../../utils/utils'; 

export interface UnidadeGestora extends DomainBase { 

  unidadegestora_id: number;
  unidadegestora_codigo: string;
  unidadegestora_nome: string;
  unidadegestora_cnpjcpf: string;
  unidadegestora_email: string;
  unidadegestora_telefone1: string;
  unidadegestora_telefone2: string;
  unidadegestora_datainscricao: Date;
  unidadegestora_cep: string;
  unidadegestora_logradouro: string;
  unidadegestora_numero: string;
  unidadegestora_complemento: string;
  unidadegestora_bairro: string;

  tipologradouro_id :  number;
  tipologradouro_nome :  string;
  tipologradouro_codigo :  string;

  logradouro_id :  number;
  logradouro_nome :  string;
  logradouro_codigo :  string;

  bairro_id :  number;
  bairro_nome :  string;
  bairro_codigo :  string;

  cidade_id :  number;
  cidade_nome :  string;
  cidade_codigo :  string;
}
