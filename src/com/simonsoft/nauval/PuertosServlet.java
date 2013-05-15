package com.simonsoft.nauval;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.simonsoft.nauval.dao.ClubNauticoDAO;
import com.simonsoft.nauval.dao.ClubNauticoDAOMysql;
import com.simonsoft.nauval.modelo.ClubNautico;

/**
 * Este servlets procesa las peticiones entrantes http de los clientes a la url
 * con sufijo PuertosServlet Para ello utiliza el méto processque que hace lo
 * siguiente: Obtiene el valor del parámetro id y en función de su valor, si es
 * all, recupera el listado de puertos y genera una cadena json a partir de
 * estos. Si es un número, recupera el puerto asociado a ese identicador y
 * genera la cadena json que lo representa. En cualquier otro caso se produce un
 * error y se muestra el mensaje correspondiente.
 * 
 */
@WebServlet("/PuertosServlet")
public class PuertosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PuertosServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * 
	 * 
	 * 
	 * @param request
	 *            objecto que representa la peticion http
	 * @param response
	 *            objecto que representa la respuesta http
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			String puertoId = request.getParameter("id");
			ClubNauticoDAO clubNauticoDAO = new ClubNauticoDAOMysql();
			Gson gson = new Gson();
			if (puertoId == null) {
				out.print("Error: no se ha especificado el parámetro id");
			} else if (puertoId.equals("all")) {
				List<ClubNautico> puertos = clubNauticoDAO
						.recuperarClubesNauticos();
				// transformar puertos a json y utilizar la variable out para
				// construir la respuesta
				// a enviar al cliente

				out.print(gson.toJson(puertos));
			} else {
				try {
					Integer id = Integer.parseInt(puertoId);
					ClubNautico puerto = clubNauticoDAO.recuperaClubNautico(id);
					// transformar puerto a json y utilizar la variable out para
					// construir la respuesta
					// a enviar al cliente
					out.print(gson.toJson(puerto));
				} catch (NumberFormatException ex) {
					out.print("Error: el valor del parámetro id no es un número");
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
