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
 * Servlet implementation class PuertosServlet
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	//conectar a la base de datos
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		ServletOutputStream out=null;
		try {
			out = response.getOutputStream();
			String puertoId=request.getParameter("id");
			ClubNauticoDAO clubNauticoDAO= new ClubNauticoDAOMysql();
			Gson gson=new Gson();
			if(puertoId == null){
				out.print("Error: no se ha especificado el par�metro id");
			} else if( puertoId.equals("all")){
				List<ClubNautico> puertos=clubNauticoDAO.recuperarClubesNauticos();
				// transformar puertos a json y utilizar la variable out para construir la respuesta
				// a enviar al cliente
				
				out.print(gson.toJson(puertos));
			}else{
				try{
					Integer id=Integer.parseInt(puertoId);		
					ClubNautico puerto=clubNauticoDAO.recuperaClubNautico(id);
					// transformar puerto a json y utilizar la variable out para construir la respuesta
					// a enviar al cliente
					out.print(gson.toJson(puerto));
				}catch(NumberFormatException ex){
					out.print("Error: el valor del par�metro id no es un n�mero");
				}
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
