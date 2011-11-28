import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class serv1 extends HttpServlet {

    public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
                    throws IOException {
            PrintWriter out = response.getWriter();
			
            java.util.Date today = new java.util.Date();
			
			URL u;
			InputStream is = null;
			DataInputStream dis;
			String s;
			//List<String> content = new ArrayList<String>(); 
			StringBuffer content = new StringBuffer();

			  try {

				 u = new URL("http://www.google.com/ig/calculator?hl=en&q=100EUR=?USD");

				 is = u.openStream();         // throws an IOException

				

				 dis = new DataInputStream(new BufferedInputStream(is));

				 while ((s = dis.readLine()) != null) {
					//content.add(s);
					content.append(s);
					//System.out.println(s);
				 }

			  } catch (MalformedURLException mue) {

				 System.out.println("Ouch - a MalformedURLException happened.");
				 mue.printStackTrace();
				 System.exit(1);

			  } catch (IOException ioe) {

				 System.out.println("Oops- an IOException happened.");
				 ioe.printStackTrace();
				 System.exit(1);

			  } finally {

				 try {
					is.close();
				 } catch (IOException ioe) {
					// just going to ignore this one
				 }

			  } 
			  
			String con = content.toString(); 
			
            out.println("<html> " +
                        "<body>" +
                        "<h1 align=center>HF\'s Chapter1 Servlet</h1>"
                        + "<br>" + today + "<br>" + 
						con + "</body>" + "</html>");
						
			
    }
}