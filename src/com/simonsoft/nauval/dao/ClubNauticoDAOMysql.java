package com.simonsoft.nauval.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.simonsoft.nauval.modelo.ClubNautico;

/**
 * @author Sim�n 
 * Esta clase implementa la inteface ClubNauticoDAO recuperando la
 * informaci�n desde de una base de datos Mysql utilizando el
 * correspondiente driver jdbc.
 */
public class ClubNauticoDAOMysql implements ClubNauticoDAO {
	private Connection connection;
	private String sqlTodosClubes = "Select * from t_puertos";
	private String sqlClub = "Select * from t_puertos where ID_puerto = ";

	/**
	 * En el constructor de esta clase cargamos la clase reponsable de
	 * implementar el driver y obtenemos un objeto de la clase Connection
	 * especificando los datos de conexi�n. Este objeto ser� utilizado
	 * posteriormente en los diferentes m�todos de la clase para realizar las
	 * consultas pertinentes.
	 */
	public ClubNauticoDAOMysql() {
		try {
			// Cargamos la clase que implementa el driver JDBC
			// El nombre de la clase aparece en la documentaci�n del fabricante
			Class.forName("com.mysql.jdbc.Driver");
			// Obtenemos un objeto de tipo Connection. Debemos especificar una
			// cadena de conexi�n
			// con el valor del host, puerto y nombre de la base de datos adem�s
			// del usuario y password
//			connection = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/nauval", "nauval", "nauval");
			connection = DriverManager.getConnection(
			"jdbc:mysql://mysql-nauval.jelastic.dogado.eu/nauval", "nauval", "nauval");
	
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/* 
	 *  Este m�todo recupera una lista de objetos ClubNautico ejecutando la consulta
	 * correspondiente.
	 * @see com.simonsoft.nauval.dao.ClubNauticoDAO#recuperarClubesNauticos()
	 */
	@Override
	public List<ClubNautico> recuperarClubesNauticos() {
		try {
			// Creamos un statement a partir del objeto connection para poder
			// lanzar una consulta sobre la base de datos.
			Statement st = connection.createStatement();
			// utilizamos el m�todo executeQuery para ejecutar la sentencia
			// SQL.Este m�todo
			// devuelve un objeto de tipo ResultSet para poder procesar el
			// resultado
			ResultSet rs = st.executeQuery(sqlTodosClubes);
			// Creamos un List para ir almacenando los resultados devueltos
			List<ClubNautico> resultado = new ArrayList<ClubNautico>();

			while (rs.next()) {
				// Creamos un objeto ClubNautico e inicializamos el valor de sus
				// atributos
				// a partir del resultset indicando en cada caso el nombre y
				// el tipo de dato de la columna correspondiente
				ClubNautico clubNautico = new ClubNautico(
						rs.getInt("id_puerto"), rs.getString("nombre"),
						rs.getString("direccion"), rs.getString("telefono"),
						rs.getString("email"), rs.getString("URL"),
						rs.getDouble("lon"), rs.getDouble("lat"));

				// Vamos a�adiendo en cada iteraci�n un nuevo elemento a la
				// lista
				resultado.add(clubNautico);
			}
			rs.close();
			st.close();
			return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* 
	 *  Este m�todo recupera la informaci�n de un club n�utico ejecutando la consulta Mysql
	 * correspondiente.
	 * @see com.simonsoft.nauval.dao.ClubNauticoDAO#recuperaClubNautico(java.lang.Integer)
	 *
	 */
	@Override
	public ClubNautico recuperaClubNautico(Integer id) {
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sqlClub + id);
			rs.next();
			ClubNautico clubNautico = new ClubNautico();
			clubNautico.setId(rs.getInt("id_puerto"));
			clubNautico.setNombre(rs.getString("nombre"));
			clubNautico.setDireccion(rs.getString("direccion"));
			clubNautico.setTelefono(rs.getString("telefono"));
			clubNautico.setEmail(rs.getString("email"));
			clubNautico.setWeb(rs.getString("URL"));
			clubNautico.setLongitud(rs.getDouble("lon"));
			clubNautico.setLatitud(rs.getDouble("lat"));
			rs.close();
			st.close();
			return clubNautico;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
