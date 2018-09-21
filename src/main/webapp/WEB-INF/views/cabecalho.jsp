<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
                        <li>
                        	<!-- Troca o idioma -->
	                        <a href="?locale=pt" rel="nofollow"><fmt:message key="menu.pt"/></a>
                        </li>
                        <li>
	                        <a href="?locale=en_US" rel="nofollow"><fmt:message key="menu.en"/></a>
                        </li>
                        <li>
	                        <a href="/ProjetoSpringMVC/carrinho" rel="nofollow">
	                        	<fmt:message key="meu.carrinho"/> (${carrinhoCompras.quantidade })
	                        </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>

    <nav class="categories-nav">
        <ul class="container">
            <li class="category"><a href="http://www.casadocodigo.com.br"><fmt:message key="navegacao.categoria.home"/></a></li>
            <li class="category"><a href="/collections/livros-de-agile"><fmt:message key="navegacao.categoria.agile"/></a></li>
            <li class="category"><a href="/collections/livros-de-front-end"><fmt:message key="navegacao.categoria.frontend"/></a></li>
            <li class="category"><a href="/collections/livros-de-games"><fmt:message key="navegacao.categoria.games"/></a></li>
            <li class="category"><a href="/collections/livros-de-java"><fmt:message key="navegacao.categoria.java"/></a></li>
            <li class="category"><a href="/collections/livros-de-mobile"><fmt:message key="navegacao.categoria.mobile"/></a></li>
            <li class="category"><a href="/collections/livros-desenvolvimento-web"><fmt:message key="navegacao.categoria.web"/></a></li>
            <li class="category"><a href="/collections/outros"><fmt:message key="navegacao.categoria.outros"/></a></li>
        </ul>
    </nav>