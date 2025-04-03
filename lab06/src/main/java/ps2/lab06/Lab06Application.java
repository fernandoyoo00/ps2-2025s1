package ps2.lab06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import static java.lang.System.out;
import java.util.Scanner;

@SpringBootApplication
public class Lab06Application implements CommandLineRunner {

	private static Scanner entrada = new Scanner(System.in);

	@Autowired
	private FaculdadeRepo faculdadeRepo;

	@Autowired
	private ProfessorRepo professorRepo;

	public static void main(String[] args) {
		SpringApplication.run(Lab06Application.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Gerenciador de Professores e Faculdades");
		boolean sair = false;
		while (!sair) {

			out.println("(1) Criar Faculdade");
			out.println("(2) Listar Faculdades");
			out.println("(3) Criar Professores");
			out.println("(4) Listar Professores");
			out.println("(0) Sair");
			out.println("# Escolha uma opção: ");
			int opcao = Integer.parseInt(entrada.nextLine());
			switch(opcao) {
				case 1: criarFaculdade(); break;
				case 2: listarFaculdades(); break;
				case 3: criarProfessor(); break;
				case 4: listarProfessores(); break;
				case 0: sair = true; break;
				default:
					out.println("Opção inválida!");
			}
		}
	}

	public void criarFaculdade() {
		out.println("Nome da nova faculdade: ");
		String nome = entrada.nextLine();
		out.println("Ano da fundação desta faculade: ");
		int ano = Integer.parseInt(entrada.nextLine());
		Faculdade f = new Faculdade(nome, ano);
		faculdadeRepo.save(f);
	}

	public void listarFaculdades() {
		Iterable<Faculdade> faculdades = faculdadeRepo.findAll();
		for(Faculdade f : faculdades) {
			out.print(f.getNome() + "-" + f.getAnoFundacao());
		}
	}

	public void criarProfessor() {
		out.println("Nome do novo professor: ");
		String nome = entrada.nextLine();
		out.println("CPF do professor: ");
		String cpf = entrada.nextLine();
		out.println("Matrícula do professor: ");
		int m = Integer.parseInt(entrada.nextLine());
		Professor p = new Professor(nome, cpf, m);
		professorRepo.save(p);
	}

	public void listarProfessores() {

	}
}
