# Projeto-Lmf

<h2>Observações Sobre o Projeto</h2>
<ul>
	
</ul>

<h2>Requisitos:</h2>
<ol>
	<li>Instalador funciona apenas no Windows</li>
	<li>A unidade principal deve ser a C. O instalador busca essa unidade.</li>
	<ul>
		<li>Se não for é necessário mudar as seguintes linhas do instalador:</li>
		cd c:<br>
		c:<br>
		<li>Deve alterar o 'c' pela letra que representa sua unidade</li>
	</ul>
	<li>Ter o Git instalado no PC</li>
	<ul>
		<li>Link para Download:</br>
		https://git-scm.com/downloads</li>
	</ul>
	<li>Ter o JDK instalado</li>
	<ul>
		<li>Link para Download:</br>
		https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html</li>
	</ul>
	<li>Ter o JRE instalado</li>
	<ul>
		<li>Link para Download:</br>
		https://www.oracle.com/technetwork/pt/java/javase/downloads/jre8-downloads-2133155.html</li>
	</ul>
</ol>

<h2>Instruções:</h2>
<ol>
	<li>Faça o clone do Repositorio executando o comando:
	git clone https://github.com/fernandorrmartins/Projeto-Lmf.git</li>
	<li>Acesse a pasta onde foi feito o clone do aplicativo</li>
	<li>Execute o arquivo "Instalador.bat" dentro da pasta</li>
	<li>Será criado uma pasta "Projeto Lmf" na Unidade C</li>
	<li>Será criado um arquivo "Projeto Lmf.bat" na Área de Trabalho</li>
	<li>Será criado um arquivo "reserva.txt" na Área de Trabalho</li>
	<li>O usuário deve adicionar uma linha ao arquivo "reserva.txt" no formato da reserva para ser analisado e processado</li>
	<ul>
		<li>Exemplo de Reserva:</li>
		esportivo:2:13/06/2019,13/06/2019</br>
		compacto:4:02/12/2019,03/12/2019,04/12/2019,05/12/2019,06/12/2019,07/12/2019</br>
		suv:6:22/10/2030
		<li>Obs:</li>
		Tipo de Carro, Qtd de Pessoas e Data devem ser Separados por ':'</br>
		Já as Datas entre sí, devem ser separadas por ','
	</ul>
	<li>Com o arquivo "reserva.txt" preenchido, é só executar o arquivo "Projeto Lmf.bat" na Área de Trabalho ou na pasta localizada em "c:/Projeto Lmf"</li>
</ol>