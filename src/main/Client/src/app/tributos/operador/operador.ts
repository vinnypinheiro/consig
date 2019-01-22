import {DomainBase} from '../../utils/utils'; 

export interface Operador extends DomainBase { 

  operador_id: number;
  operador_senha: string;
  operador_datacadastro: Date;
  operador_datavalidade: Date;
  operador_ativo: boolean;
  operador_nome: string;
  operador_rg: string;
  operador_cnpjcpf: string;
  operador_nit: string;
  operador_email: string;
  operador_datalancamento: Date;
  operador_telefonefixo: string;
  operador_telefonemovel: string;
  operador_logradouro: string;
  operador_numero: string;
  operador_complemento: string;
  operador_bairro: string;
  operador_cep: string;

  tipooperador_id :  number;
  tipooperador_nome :  string;
  tipooperador_codigo :  string;

  departamento_id :  number;
  departamento_nome :  string;
  departamento_codigo :  string;

  naturezajuridica_id :  number;
  naturezajuridica_nome :  string;
  naturezajuridica_codigo :  string;

  nacionalidade_id :  number;
  nacionalidade_nome :  string;
  nacionalidade_codigo :  string;

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
