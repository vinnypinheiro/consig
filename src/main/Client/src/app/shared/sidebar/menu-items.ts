import { RouteInfo } from './sidebar.metadata';

export const ROUTES: RouteInfo[] = [
  {
    path: '',
    title: 'Cadastros',
    icon: 'mdi mdi-view-dashboard',
    class: 'has-arrow',
    ddclass: '',
    extralink: false,
    submenu: [

      {
        path: '/associacao',
        title: 'Associações',
        icon: 'fas fa-university',
        class: '',
        ddclass: '',
        extralink: false,
        submenu: []
      },
        {
            path: '/convenio',
            title: 'Convênios',
            icon: ' fas fa-hands-helping',
            class: '',
            ddclass: '',
            extralink: false,
            submenu: []
        },
      {
        path: '/correspondente',
        title: 'Correspondentes',
        icon: 'fas fa-briefcase',
        class: '',
        ddclass: '',
        extralink: false,
        submenu: []
      },
       {
        path: '/associado',
        title: 'Cadastro de Associados',
        icon: ' fas fa-user',
        class: '',
        ddclass: '',
        extralink: false,
        submenu: []
      },

    ]
  },
    {
        path: '',
        title: 'Financeiro',
        icon: ' far fa-money-bill-alt\n',
        class: 'has-arrow',
        ddclass: '',
        extralink: false,
        submenu: [
            {
                path: '/balanco',
                title: 'Balanço',
                icon: 'fas fa-retweet',
                class: '',
                ddclass: '',
                extralink: false,
                submenu: []
            },
            {
                path: '/associacao',
                title: 'Resumo financeiro',
                icon: 'fas fa-retweet',
                class: '',
                ddclass: '',
                extralink: false,
                submenu: []
            },
            {
                path: '/associacao',
                title: 'Comissões',
                icon: 'fas fa-retweet',
                class: '',
                ddclass: '',
                extralink: false,
                submenu: []
            },
            {
                path: '/associacao',
                title: 'Receitas e Despesas',
                icon: 'fas fa-retweet',
                class: '',
                ddclass: '',
                extralink: false,
                submenu: []
            },
            {
                path: '/lancamento',
                title: 'Lançamentos',
                icon: 'fas fa-upload',
                class: '',
                ddclass: '',
                extralink: false,
                submenu: []
            },
            {
                path: '/associacao',
                title: 'Relatórios',
                icon: 'fas fa-retweet',
                class: '',
                ddclass: '',
                extralink: false,
                submenu: []
            }

        ]
    },
    {
        path: '',
        title: 'Relatórios',
        icon: 'fas fa-file-alt',
        class: 'has-arrow',
        ddclass: '',
        extralink: false,
        submenu: [
            {
                path: '/convenio',
                title: 'Convênios',
                icon: ' fas fa-hands-helping',
                class: '',
                ddclass: '',
                extralink: false,
                submenu: []
            },
            {
                path: '/associacao',
                title: 'Resumo Financeiro',
                icon: 'fas fa-file-alt',
                class: '',
                ddclass: '',
                extralink: false,
                submenu: []
            },
            {
                path: '/correspondente',
                title: 'Resumo de Associados',
                icon: 'fas fa-file-alt',
                class: '',
                ddclass: '',
                extralink: false,
                submenu: []
            },
            {
                path: '/associado',
                title: 'Correspondentes',
                icon: 'fas fa-file-alt',
                class: '',
                ddclass: '',
                extralink: false,
                submenu: []
            },

        ]
    },
    {
        path: '',
        title: 'Importação de tabelas',
        icon: 'fas fa-table',
        class: 'has-arrow',
        ddclass: '',
        extralink: false,
        submenu: [
            {
                path: '/espelhoretorno',
                title: 'Tabela Consiglog',
                icon: ' fas fa-th-list',
                class: '',
                ddclass: '',
                extralink: false,
                submenu: []
            },
            {
                path: '/importdata',
                title: 'Tabela Excel',
                icon: ' fas fa-th-list',
                class: '',
                ddclass: '',
                extralink: false,
                submenu: []
            },



        ]
    },
    {
        path: '',
        title: 'Configurações',
        icon: 'fas fa-cogs',
        class: 'has-arrow',
        ddclass: '',
        extralink: false,
        submenu: [
            {
                path: '/associacao',
                title: 'Cadastro de usuários',
                icon: ' fas fa-cog',
                class: '',
                ddclass: '',
                extralink: false,
                submenu: []
            },
        ]
    }


];
