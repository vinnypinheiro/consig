import {DomainBase} from '../../utils/utils'; 

export interface Agencia extends DomainBase { 

     bairro: string; 
     banco: string; 
     bancoagencia: string; 
     bancoconta: string; 
     cidade: string; 
     cnpj: string; 
     contato: string; 
     cpf: string; 
     datacadastro: Date; 
     email: string; 
     email1: string; 
     email2: string; 
     emailcontato: string; 
     endereco: string; 
     fax: string; 
     inscricaoestadual: string; 
     inscricaomunicipal: string; 
     nomefantasia: string; 
     obs: string; 
     razaosocial: string; 
     site: string; 
     telefone: string; 
     telefone2: string; 
     uf_id_id :  number; 
     uf_id_nome :  string; 
     uf_id_codigo :  string; 
}