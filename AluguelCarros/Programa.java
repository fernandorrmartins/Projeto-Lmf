package AluguelCarros;

import AluguelCarros.Models.*;
import AluguelCarros.Services.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Programa {
	public static void main(String[] args) {
		try {
			HashMap<String, Loja> map = Util.CriarLojas();
			List<Reserva> reservas = Reserva.CarregarPedidos();
			
			
			
			System.out.println("Pressione qualquer tecla para encerrar...");
			new Scanner(System.in).nextLine();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}