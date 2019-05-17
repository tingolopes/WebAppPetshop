
$(document).ready(function(){
    $('.js-btn-menu').bind('click', function(){
        $('.js-barra-esquerda').toggleClass('esta-aberto');
        $('.js-conteudo').toggleClass('esta-aberto');
    });
});