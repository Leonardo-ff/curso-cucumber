package steps;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class AprenderCucumberSteps {
	

	@Dado("que criei o arquivo corretamente")
	public void queCrieiOArquivoCorretamente() {
	}
	@Quando("executá-lo")
	public void executáLo() {
	}
	@Então("a especificação deve finalizar com sucesso")
	public void aEspecificaçãoDeveFinalizarComSucesso() {
	}
	
	private int contador = 0;
	@Dado("que o valor do contador é {int}")
	public void queOValorDoContadoré(Integer int1) {
		contador = int1;
	}
	@Quando("eu incrementar {int}")
	public void euIncrementar(Integer int1) {
		contador = contador + int1;
	}
	@Então("o valor do contador será {int}")
	public void oValorDoContadorSerá(Integer int1) {
		System.out.println(int1);
		System.out.println(contador);
	}
	
	
	
	Date entrega = new Date();
//	@Dado("que o prazo é dia {data}")
//	public void que_o_prazo_é_dia(Date data) {
//	    entrega = data;
//	    System.out.println(entrega);
//	}

	@Quando("^a entrega atrasar em ((?:-?\\d+)|(?:\\d+)) (dia|dias|mes|meses)$")
	public void a_entrega_atrasar_em_dias(Integer int1, String tempo) {
	   Calendar cal = Calendar.getInstance();
	   cal.setTime(entrega);
	   if(tempo.equals("dias")) {
		   cal.add(Calendar.DAY_OF_MONTH, int1);		   
	   }
	   if(tempo.equals("meses")) {
		   cal.add(Calendar.MONTH, int1);
	   }
	   entrega = cal.getTime();
	}
	
	@Então("^a entrega será efetuada em (\\d{2}\\/\\d{2}\\/\\d{4})$")
	public void a_entrega_será_efetuada_em(String data) {
	    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    String dataFormatada = format.format(entrega);
	    Assert.assertEquals(data, dataFormatada);
	}

	//há cucumber expressions que é algo mais novo e a REGEX. Não podem ser misturados
	//regex inicia e termina com ^& e cucumber expr. é com {}
	

	
	
	@Dado("^que o ticket( especial)? é (A.\\d{3})$")
	public void que_o_ticket_é_af345(String t, String arg1) {
	    
	}

	@Dado("que o valor da passagem é R$ {double}")
	public void que_o_valor_da_passagem_é_r$(Double double1) {

	}

	@Dado("^que o nome do passageiro é \"(.{5,20})\"$")
	public void que_o_nome_do_passageiro_é(String string) {
	    
	}

	@Dado("^que o telefone do passageiro é (9\\d{3}-\\d{4})$")
	public void que_o_telefone_do_passageiro_é(String int1) {
	    
	}

	@Quando("criar os steps")
	public void criar_os_steps() {
	    
	}

	@Então("o teste vai funcionar")
	public void o_teste_vai_funcionar() {
	    
	}
	
	

}
