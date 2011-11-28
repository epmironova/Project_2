import javax.servlet.*;

public class ServletContListener implements ServletContextListener{

  public void contextInitialized(ServletContextEvent event){

		ServletContext sc =event.getServletContext();
		String mylink= sc.getInitParameter("repUrl");
		sc.setAttribute("reportUrl",mylink);
	}
    
  
  public void contextDestroyed(ServletContextEvent event){
  //
  }
}