var SimpleCrud = SimpleCrud || {};

SimpleCrud.Excluir = (function () {
	
	function Excluir() {
		this.btnExcluir = $('.js-excluir');
	}
	
	Excluir.prototype.iniciar = function() {
		this.btnExcluir.on('click', onExcluirClidado.bind(this));
		
		if(window.location.search.indexOf('excluido') > -1) {
			swal('Pronto', 'Excluido com sucesso!', 'success');
		}
		
	}
	
	function onExcluirClidado(evento) {
		evento.preventDefault();
		var botaoClicado = $(evento.currentTarget);
		var url = botaoClicado.data('url');
		var objeto = botaoClicado.data('objeto');
		
		swal({
			title: 'Tem certeza?',
			text:'Excluir "' + objeto + '"  o registro não poderá ser recuperado!!!',
			buttons: true,
			buttons: ['Mudei de ideia.', 'Excluir agora'],
			dangerMode: true,
			closeModal: true
		}).then((deletar) => {
			if(deletar) {
				onExcluirConfirmado.call(this, url);
			} else {
				swal("Exclusão cancelada");
			}
		});
		
	}
	
	function onExcluirConfirmado(url) {
		$.ajax({
			url:url,
			method: 'DELETE',
			success: onExcluidoComSucesso.bind(this),
			error: onErrorAoExcluir.bind(this)
		});
	}
	
	function onExcluidoComSucesso(){
		var urlAtual = window.location.href;
		var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
		var novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido'; 
		window.location = novaUrl;
	}
	
	function onErrorAoExcluir(e) {
		swal('Vish! :(', e.responseText, 'error');
	}
	
	return Excluir;
	
	
})();

$(function() {
	var excluir = new SimpleCrud.Excluir();
	excluir.iniciar();
});
