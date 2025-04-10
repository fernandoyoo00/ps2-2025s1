package ps2.lab07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import static java.lang.System.out;
import java.util.Scanner;

@SpringBootApplication
public class Lab07Application implements CommandLineRunner {

	private static Scanner entrada = new Scanner(System.in);

	@Autowired
	private MusicaRepo musicaRepo;

	public static void main(String[] args) {
		SpringApplication.run(Lab07Application.class, args);
		
	}

	@Override
	public void run(String... args) {
		System.out.println("Gerenciador de Professores e Faculdades");
		boolean sair = false;
		while (!sair) {

			out.println("(1) Criar Músicas");
			out.println("(2) Listar Músicas");
			out.println("(3) Atualizar Músicas");
			out.println("(4) Deletar Músicas");
			out.println("(0) Sair");
			out.println("# Escolha uma opção: ");
			int opcao = Integer.parseInt(entrada.nextLine());
			switch(opcao) {
				case 1: criarMusicas(); break;
				case 2: listarMusicas(); break;
				case 3: atualizarMusicas(); break;
				case 4: deletarMusicas(); break;
				case 0: sair = true; break;
				default:
					out.println("Opção inválida!");
			}
		}
	}

	public void criarMusicas() {
		out.println("Nome da música: ");
		String titulo = entrada.nextLine();
		out.println("Nome do compositor: ");
		String compositor = entrada.nextLine();
		out.println("Ano da música: ");
		int ano = Integer.parseInt(entrada.nextLine());
		Musica m = new Musica(titulo, compositor, ano);
		musicaRepo.save(m);
	}
	
	public void listarMusicas() {
        Iterable<Musica> musicas = musicaRepo.findAll();
        for(Musica m : musicas) {
            out.println("ID: " + m.getId()
                + " | Título: " + m.getTitulo() 
                + " | Compositor: " + m.getCompositor().getNome()
                + " | Ano: " + m.getAno().getValor());
        }
    }

    public void atualizarMusicas() {
        out.println("Informe o ID da música a ser atualizada: ");
        Long id = Long.parseLong(entrada.nextLine());
        Musica m = musicaRepo.findById(id).orElse(null);
        if(m != null) {
            out.println("Novo título (atual: " + m.getTitulo() + "): ");
            String titulo = entrada.nextLine();
            out.println("Novo compositor (atual: " + m.getCompositor().getNome() + "): ");
            String comp = entrada.nextLine();
            out.println("Novo ano (atual: " + m.getAno().getValor() + "): ");
            int anoValue = Integer.parseInt(entrada.nextLine());
            m.setTitulo(titulo);
            m.setCompositor(new Compositor(comp));
            m.setAno(new Ano(anoValue));
            musicaRepo.save(m);
            out.println("Música atualizada com sucesso!");
        } else {
            out.println("Música não encontrada!");
        }
    }

    public void deletarMusicas() {
        out.println("Informe o ID da música a ser deletada: ");
        Long id = Long.parseLong(entrada.nextLine());
        Musica m = musicaRepo.findById(id).orElse(null);
        if(m != null) {
            musicaRepo.delete(m);
            out.println("Música deletada com sucesso!");
        } else {
            out.println("Música não encontrada!");
        }
    }
}