var SimpleCrud = SimpleCrud || {};
	
SimpleCrud.MascaraCpfCnpj = (function() {
	
	function MascaraCpfCnpj() {
		this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
		this.labelCpfCnpj = $('[for=cpf-cnpj]');
		this.inputCpfCnpj = $('#cpf-cnpj');
	}
	
	MascaraCpfCnpj.prototype.iniciar = function() {
		this.radioTipoPessoa.on("change", onTipoPessoaAlterado.bind(this));
	}
	
	function onTipoPessoaAlterado(evento) {
		this.inputCpfCnpj.val('');
		var tipoPessoaSelecionada  = $(evento.currentTarget);
		this.labelCpfCnpj.text(tipoPessoaSelecionada.data('documento'));
		this.inputCpfCnpj.mask(tipoPessoaSelecionada.data('mascara'));
		this.inputCpfCnpj.removeAttr('disabled');
		
	}
	
	return MascaraCpfCnpj;
	
}());

$(function () {
	var mascaraCpfCnpj = new SimpleCrud.MascaraCpfCnpj();
	mascaraCpfCnpj.iniciar();
}());