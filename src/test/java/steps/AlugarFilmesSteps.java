package steps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Assert;

import entidades.Filme;
import entidades.NotaAluguel;
import entidades.TipoAluguel;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import servicos.AluguelService;
import utils.DateUtils;

public class AlugarFilmesSteps {
	
	private Filme filme;
	private AluguelService aluguel = new AluguelService();
	private NotaAluguel nota;
	private String erro;
	private TipoAluguel tipoAluguel;
	
	@Dado("um filme com estoque de {int} unidades")
	public void umFilmeComEstoqueDeUnidades(int int1) {
		filme = new Filme();
	    filme.setEstoque(int1);
	}

	@Dado("que o preço de aluguel seja R$ {int}")
	public void queOPreçoDeAluguelSejaR$(int int1) {
	    filme.setAluguel(int1);
	}

	@Dado("um filme")
	public void umFilme(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		filme = new Filme();
	    filme.setEstoque(Integer.parseInt( map.get("estoque")));
	    filme.setAluguel(Integer.parseInt( map.get("preco")));
	}
	
	@Quando("alugar")
	public void alugar() {
	    try {
			nota = aluguel.alugar(filme, tipoAluguel);
		} catch (RuntimeException e) {
			erro = e.getMessage();
		}
	}

	@Então("o preço do aluguel será R$ {int}")
	public void oPreçoDoAluguelSeráR$(int int1) {
	    Assert.assertEquals(int1, nota.getPreco());
	}
	



	@Então("o estoque do filme será {int} unidade")
	public void oEstoqueDoFilmeSeráUnidade(int int1) {
	    Assert.assertEquals(int1, filme.getEstoque());
	}
	
	@Então("não será possível por falta de estoque")
	public void nãoSeráPossívelPorFaltaDeEstoque() {
		Assert.assertEquals("Filme sem estoque", erro);
	}
	
	@Dado("que o tipo de aluguel seja {string}")
	public void queOTipoDeAluguelSejaExtendido(String tipo) {
	    tipoAluguel = tipo.equals("semanal")? TipoAluguel.SEMANAL : 
	    	tipo.equals("extendido")? TipoAluguel.EXTENDIDO : TipoAluguel.COMUM;
	}

	@Então("a data de entrega será em {int} dia(s?)")
	public void aDataDeEntregaSeráEmDias(Integer int1) {
	    Date dataEsperada = DateUtils.obterDataDiferencaDias(int1);
	    Date dataReal = nota.getDataEntrega();
	    
	    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    
	    Assert.assertEquals(format.format(dataEsperada), format.format(dataReal));
	}

	@Então("a pontuação recebida será de {int} pontos")
	public void aPontuaçãoRecebidaSeráDePontos(int int1) {
	    Assert.assertEquals(int1, nota.getPontuacao());
	}


}
