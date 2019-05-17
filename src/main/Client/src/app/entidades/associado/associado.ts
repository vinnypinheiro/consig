import {DomainBase} from '../../utils/utils';
import {Emprestimo} from "../emprestimo/emprestimo";
import {Correspondente} from "../correspondente/correspondente";
import {Auxilio} from "../auxilio/auxilio";
import {Mensalidade} from "../mensalidade/mensalidade";
import {Ocorrencia} from "../ocorrencia/ocorrencia";
import {Associacaolink} from "../associacaolink/associacaolink";
import {Associacao} from "../associacao/associacao";

export interface Associado extends DomainBase {

     nome: string;
     cpf: string;
     matricula: number;
     cidade: number;
     bairro: number;
     uf: number;
     cep: number;
     situacao: string;
     lotacao: string;
     cargo: string;
     telefone: string;
     email: string;
     endereco: string;
     estado: string;
     municipio: string;
     datacadastro: Date;
     arquivo: string;
     orgao: string;
     associacao1: Associacao;
     associacao2: Associacao;
     obs: string;
     rg: string;

     //Dados Bancários
    // TODO: Adicionar no formulário
     banco: string;
     agencia: string;
     tipoconta: string;
     conta: string

    //dados para mensalidade
    vlrmensalidade: number;


     auxilioList: Auxilio;
     correspondente_id: Correspondente;
     mensalidadeList: Mensalidade;
     ocorrenciaList: Ocorrencia;
}