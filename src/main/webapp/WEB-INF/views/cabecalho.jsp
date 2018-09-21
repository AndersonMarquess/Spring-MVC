<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

	<header id="layout-header">
        <div class="clearfix container">
            <a href="/ProjetoSpringMVC/" id="logo"> </a>
            <div id="header-content">
                <nav id="main-nav">
                    <ul class="clearfix">
                    	<!-- Só exibe os itens se o usuário estiver autenticado -->
                    	<security:authorize access="hasRole('ROLE_ADMIN')">
	                        <li><a href="/ProjetoSpringMVC/produtos" rel="nofollow">Listar produtos</a></li>
	                        <li><a href="/ProjetoSpringMVC/produtos/form" rel="nofollow">Cadastrar produto</a></li>
                    	</security:authorize>
                    </ul>
                    <ul class="clearfix">
                        <li><a href="/ProjetoSpringMVC/carrinho" rel="nofollow">Seu Carrinho (${carrinhoCompras.quantidade })</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>

    <nav class="categories-nav">
        <ul class="container">
            <li class="category"><a href="http://www.casadocodigo.com.br">Home</a></li>
            <li class="category"><a href="/collections/livros-de-agile"> Agile </a></li>
            <li class="category"><a href="/collections/livros-de-front-end"> Front End </a></li>
            <li class="category"><a href="/collections/livros-de-games"> Games </a></li>
            <li class="category"><a href="/collections/livros-de-java"> Java </a></li>
            <li class="category"><a href="/collections/livros-de-mobile"> Mobile </a></li>
            <li class="category"><a href="/collections/livros-desenvolvimento-web"> Web </a></li>
            <li class="category"><a href="/collections/outros"> Outros </a></li>
        </ul>
    </nav>