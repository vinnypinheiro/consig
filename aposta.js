function addApostaNova(campeonato, partida, opcao, xml, botao_clicado, cotacao) {
    $('.bbox-subtotal').prepend('<li id="aposta' + opcao + '"><a id="excluirAposta" data-aposta="' + opcao + '" class="btn btn-flat ui-tooltip" data-toggle="tooltip" data-placement="left" title="" data-original-title="Remover aposta"><i class="fa fa-times" style="margin-right: 0 !important;"></i></a><a href="#">' + botao_clicado.attr('data-partida') + '</a><div class="bslip-market">Vencedor do Encontro</div><div class="bslip-option">' + botao_clicado.attr('data-original-title') + ' <span>' + cotacao + '</span></div></li>');
    var parameters = "{'partida':'" + partida + "','campeonato':'" + campeonato + "','opcao':'" + opcao + "','xml':'" + xml + "','cotacao':'" + cotacao + "'}";
    $.ajax({
        async: false,
        type: "GET",
        url: '/scripts/ajax/addApostaNova.php?partida=' + partida + '&cotacao=' + cotacao + '&campeonato=' + campeonato + '&opcao=' + opcao + '&xml=' + xml + '',
        data: parameters,
        success: function (data) {
            if (data == '1') {
                listaHTMLApostasLateral('.cupomApostasInterno');
                $('[data-match="' + partida + '"]').removeClass('btn-success');
                botao_clicado.html(cotacao).addClass('btn-success');
                return '1';
            }
            else if (data == '2') {
                listaHTMLApostasLateral('.cupomApostasInterno');
                botao_clicado.html(cotacao).removeClass('btn-success');
            }
            else {
                alert(data);
            }
            botao_clicado.html(cotacao);
        }
    });
}

function addApostaManual(partida, opcao, botao_clicado, cotacao) {
    $.get('/scripts/ajax/addApostaManual.php?partida=' + partida + '&cotacao=' + cotacao + '&opcao=' + opcao + '', function (data) {
        if (data == '1') {
            listaHTMLApostasLateral('.cupomApostasInterno');
            botao_clicado.html(cotacao).addClass('btn-success');
            return '1';
        }
        else if (data == '2') {
            listaHTMLApostasLateral('.cupomApostasInterno');
            botao_clicado.html(cotacao).removeClass('btn-success');
        }
        else {
            alert(data);
        }
        botao_clicado.html(cotacao);
    });
}

function addAposta(campeonato, opcao, xml, botao_clicado, cotacao) {
    botao_clicado.html(cotacao).toggleClass('btn-success');
    $.ajax({
        type: "POST",
        url: "/scripts/ajax/addAposta.php",
        data: {cotacao: cotacao, campeonato: campeonato, opcao: opcao, xml: xml},
        success: function (data) {
            if (data == '1') {
                listaHTMLApostasLateral('.cupomApostasInterno');
                $('[id="criaAposta"]').removeClass('btn-success');
                botao_clicado.html(cotacao).addClass('btn-success');
                $('[data-id="' + opcao + '"]').html(cotacao).addClass('btn-success');
                return '1';
            }
            else if (data == '2') {
                listaHTMLApostasLateral('.cupomApostasInterno');
                botao_clicado.html(cotacao).removeClass('btn-success');
                $('[id="criaAposta"]').removeClass('btn-success');
            }
            else {
                alert(data);
                $('[id="criaAposta"]').html(cotacao).removeClass('btn-success');
            }
        },
        error: function (data) {
            alert(data);
        }
    });
}

function addApostaAovivo(opcao, posicao, botao_clicado, cotacao) {
    $.get('/scripts/ajax/addApostaAovivo.php?posicao=' + posicao + '&opcao=' + opcao + '', function (data) {
        if (data == '1') {
            listaHTMLApostasLateral('#cupomApostasInterno');
            botao_clicado.html(cotacao).addClass('btn-success');
            return '1';
        }
        else {
            alert(data);
        }
        botao_clicado.html(cotacao);
    });
}

function listaHTMLApostas(div) {
    var distanciaTopo = $(document).scrollTop();
    $(div).html('<center><img src="/dist/img/loading-blue.gif" /></center>');
    $.get('/scripts/ajax/listaHTMLApostas.php', function (data) {
        if (data == "") {
        }
        else {
            $(div).html(data);
            $('#cupomApostas').show();
            atualizaValorAposta();
            $(document).scrollTop(distanciaTopo);
        }
    });
}

function listaHTMLApostasLateral(div) {
    var distanciaTopo = $(document).scrollTop();
    $.get('/scripts/ajax/listaHTMLApostasLateral.php', function (data) {
        if (data == "") {
        }
        else {
            $(div).html(data);
            $('#cupomApostas').show();
            atualizaValorAposta();
            $(document).scrollTop(distanciaTopo);
        }
    });
}

function carregaCartao(div) {
    if (div == '#cupomApostasInterno') {
        div = '*[id="cupomApostasInterno"]';
    }
    var distanciaTopo = $(document).scrollTop();
    $(div).html('<center><img src="/dist/img/loading-blue.gif" /></center>');
    $.get('/scripts/ajax/carregaCartao.php', function (data) {
        if (data == "") {
        }
        else {
            $(div).html(data);
            $('#cupomApostas').show();
            atualizaValorAposta();
            $("html, body").stop().animate({scrollTop: 0}, '500', 'swing');
        }
    });
}

function listaHTMLApostasAovivo(div) {
    $(div).html('<center><img src="/dist/img/loading-blue.gif" /></center>');
    $.get('/scripts/ajax/listaHTMLApostas.php', function (data) {
        if (data == "") {
        }
        else {
            $(div).html(data);
            $('#cupomApostas').show();
            atualizaValorAposta();
            $("html, body").stop().animate({scrollTop: 0}, '500', 'swing');
        }
    });
}

function listaJogosAovivo(div) {
    $(div).html('<br /><center><img src="/dist/img/loading-yellow.gif" width="70" /><br /><br /><b>Carregando jogos ao vivo...</b></center><br />');
    $.get('/scripts/ajax/listaJogosAovivo.php', function (data) {
        $(div).html(data);
    });
}

function listaJogosAovivoSemLoader(div) {
    $.get('/scripts/ajax/listaJogosAovivo.php', function (data) {
        $(div).html(data);
    });
}

function atualizaValorAposta() {
    $('.valorAposta').keyup(function () {
        var aposta = $(this).attr('data-aposta');
        var valor = $(this).val();
        $.get('/scripts/ajax/atualizaValorAposta.php?token=' + aposta + '&valor=' + valor + '', function (data) {
            $('#pr' + aposta + '').html(data);
        });
    });
}

function atualizaValorApostaMultipla() {
    $('.valorApostaMultipla').keyup(function () {
        var valor = $(this).val();
        $.get('/scripts/ajax/atualizaValorApostaMultipla.php?valor=' + valor + '', function (data) {
            $('.valApostaMultipla').html(data);
        });
    });
}

function atualizaValorApostaAdicionada() {
    var valor = $('.valorApostaMultipla').val();
    $.get('/scripts/ajax/atualizaValorApostaMultipla.php?valor=' + valor + '', function (data) {
        $('.valApostaMultipla').html(data);
    });
}

function listaJogosData(data, div, xml) {
    $.get('/scripts/ajax/listaJogosData.php?data=' + data + '&xml=' + xml, function (data) {
        $(div).html(data);
    });
}

function pesquisar(data, busca, div, xml) {
    $.get('/scripts/ajax/pesquisar.php?data=' + data + '&busca=' + busca + '&xml=' + xml, function (data) {
        $(div).html(data);
    });
}

function carregaAEM(jogo_id, div) {
    $.get('/scripts/ajax/carregaAEM.php?jogo=' + jogo_id + '', function (data) {
        $(div).html(data);
    });
}

function listaOpcoesJogoAovivo(div, jogo) {
    $(div).html('<br /><center><img src="/dist/img/loading-yellow.gif" width="70" /><br /><br /><b>Carregando opÃ§Ãµes disponÃ­veis...</b></center><br />');
    $.get('/scripts/ajax/listaOpcoesJogoAovivo.php?jogo=' + jogo, function (data) {
        $(div).html(data);
    });
}

function listaOpcoesJogoAovivoSemLoader(div, jogo) {
    $.get('/scripts/ajax/listaOpcoesJogoAovivo.php?jogo=' + jogo, function (data) {
        $(div).html(data);
    });
}

function listaJogosAovivoSemLoader(div) {
    $.get('/scripts/ajax/listaJogosAovivo.php', function (data) {
        $(div).html(data);
    });
}