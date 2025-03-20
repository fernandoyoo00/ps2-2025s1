import java.math.BigDecimal;
import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println("Boa noite!");
        String url = "jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.rjsrjqlpodufmtdezzyt&password=Lemafevis2jesus!";
        Connection conexao = DriverManager.getConnection(url);
        String sql = "SELECT * FROM contas";
        PreparedStatement stm = conexao.prepareStatement(sql);
        ResultSet rset = stm.executeQuery();
        while (rset.next()) {
            long nroConta = rset.getLong("nro_conta");
            double saldo = rset.getDouble("saldo");
            System.out.print("Número da conta: " + nroConta);
            System.out.println(" tem saldo de R$ " + saldo);
        }
    }

    public static void read() throws SQLException {
        String url = System.getenv("URL");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");
    
        Connection c = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM contas";
        PreparedStatement stm = c.prepareStatement(sql);
    
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            long nro = rs.getLong("nro_conta");
            BigDecimal saldo = rs.getBigDecimal("saldo");
            System.out.println("Conta número: " + nro + " tem saldo de R$ " + saldo);
        }
    
        c.close();
    }

    public static void create() throws SQLException {
    System.out.print("Número para a nova conta: ");
    long nro = Long.parseLong(System.console().readLine());
    
    System.out.print("Saldo da nova conta: ");
    BigDecimal saldo = new BigDecimal(System.console().readLine());
    
    String url = System.getenv("jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.rjsrjqlpodufmtdezzyt&password=Lemafevis2jesus!");
    String username = System.getenv("USERNAME");
    String password = System.getenv("PASSWORD");
    
    Connection c = DriverManager.getConnection(url, username, password);
    String sql = "INSERT INTO contas VALUES (?,?)";
    
    PreparedStatement prepstm = c.prepareStatement(sql);
    prepstm.setLong(1, nro);
    prepstm.setBigDecimal(2, saldo);
    
    int ret = prepstm.executeUpdate();
    System.out.println("Número de registros inseridos: " + ret);
    
    c.close();
}

    public static void update() throws SQLException {
        System.out.print("Número de uma conta já existente: ");
        long nro = Long.parseLong(System.console().readLine());

        System.out.print("Novo saldo para esta conta: ");
        BigDecimal saldo = new BigDecimal(System.console().readLine());

        String url = System.getenv("URL");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");

        Connection c = DriverManager.getConnection(url, username, password);
        String sql = "UPDATE contas SET saldo=? WHERE nro_conta=?";
        
        PreparedStatement prepstm = c.prepareStatement(sql);
        prepstm.setBigDecimal(1, saldo);
        prepstm.setLong(2, nro);
        
        int ret = prepstm.executeUpdate();
        System.out.println("Número de registros alterados: " + ret);
        
        c.close();
    }
    public static void delete() throws SQLException {
        System.out.print("Número de uma conta já existente: ");
        long nro = Long.parseLong(System.console().readLine());

        String url = System.getenv("URL");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");

        Connection c = DriverManager.getConnection(url, username, password);
        String sql = "DELETE FROM contas WHERE nro_conta=?";

        PreparedStatement prepstm = c.prepareStatement(sql);
        prepstm.setLong(1, nro);

        int ret = prepstm.executeUpdate();
        System.out.println("Número de registros apagados: " + ret);

        c.close();
    }
}