package AluguelCarros;

import AluguelCarros.Models.*;
import AluguelCarros.Services.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Programa {
	/* Método Main da Solução */
	public static void main(String[] args) {
		try {
			// Recebe um HashMap cuja Key é o Nome da Loja e a Loja é o Objeto que a Representa
			HashMap<String, Loja> map = Loja.CriarLojas();
			// Carrega as Reservas do Arquivo reserva.txt da Area de Trabalho para ser analisado e processado
			List<Reserva> reservas = Reserva.CarregarPedidos();
			
			// Se a Lista de Reservas tiver pelo menos uma Reserva é analisada e processada
			if(reservas.size() > 0) { Reserva.ApresentarMelhoresPropostas(reservas, map); }
			
			System.out.println("\nPressione qualquer tecla para encerrar...");
			new Scanner(System.in).nextLine();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}