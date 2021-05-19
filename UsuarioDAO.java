package Banco;
import Tabelas.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {
	private Connection c = null;

	public UsuarioDAO() {
		this.c = new ConnectionFactory().getConnection();
	}

	public void insere(Users u) {

		try {
			if (this.c == null) {
				this.c = new ConnectionFactory().getConnection();
			}
			String sql = "INSERT INTO USERS" + " (ID_USER,NAME_USER,SECTOR_USER,POSITION_USER,SALARY_USER)"
					+ " values (?,?,?,?,?)";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, u.getId_user());
			ps.setString(2, u.getName_user());
			ps.setString(3, u.getSector_user());
			ps.setString(4, u.getPosition_user());
			ps.setDouble(5, u.getSalary_user());

			ps.execute();
			this.c.close();

		} catch (SQLException e) {
			System.out.println("Erro ao inserir Usuario: " + e);
		}
	}

	public void consultaUser(int id) {
		try {
		if (this.c == null) {
			this.c = new ConnectionFactory().getConnection();
		}
		System.out.println();
		String sql = "SELECT * FROM USERS WHERE ID_USER = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		this.c.close();
		
		}catch (SQLException e) {
			System.out.println("Erro ao inserir Usuario:  da" + e);
		}
	}
	
	/**
	 * R - Read - Método responsável por consultar todos os registros de Usuario no Banco de
	 * dados
	 */
	public List<Users> consultaTodos() {
		try {

			// Se não tiver uma conexão ela será criada
			if (this.c == null) {
				this.c = new ConnectionFactory().getConnection();
			}

			// clausula SQL
			String sql = "SELECT * FROM Usuarios";

			// Carregamento do sql para o PS
			PreparedStatement ps = c.prepareStatement(sql);
			
			// Executa o comando que consulta todos os registros de usuarios do BD e armazena no rs
			ResultSet rs = ps.executeQuery();

			ArrayList<Users> lista = new ArrayList<Users>();
			
			//percorre cada linha que retornou da consulta do BD e cria um
			//usuario em memoria e adiciona na lista
			while(rs.next()) {
				Users u = new Users();//cria um Bean em memoria
				u.setId_user(rs.getInt("IdUsuario"));
				u.setName_user(rs.getString("NomeUsuario"));
				u.setSector_user(rs.getString("Setor"));
				u.setPosition_user(rs.getString("Cargo"));
				u.setSalary_user(rs.getDouble("Salario"));
				lista.add(u);//adiciona esse objeto na lista
			}
			
			// Fecha a conexão a cada operação
			this.c.close();
			return lista;

		} catch (SQLException e) {
			System.out.println("Erro ao inserir Usuario: " + e);
		}
		return null;
	}

	/**
	 * U - Update - Método responsável por atualizar um objeto Usuario no Banco de
	 * dados
	 */
	public void atualiza(Users u) {
	}

	public void apaga(Users u) {
	}
}