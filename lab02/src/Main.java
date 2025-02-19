import banco.*;

public class Main {
    public static void main(String[] args) {
        Conta[] contas = { new ContaInfinita(1111, 50),
                           new ContaBancaria(2222, 99),
                           new ContaBancaria(3333, 1000),
                           new ContaEspecial(4444, 800, 200) };
        for (int i=0; i<contas.length; i++) {
            contas[i].depositar(10);           
            contas[i].sacar(900); 
            System.out.print(contas[i]);          
        }
    }
}
