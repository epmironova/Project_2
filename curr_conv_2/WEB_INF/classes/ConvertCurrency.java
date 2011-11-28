import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ConvertCurrency extends HttpServlet {

    public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
                    throws IOException {
					
			String amountParam = request.getParameter("amount");		
			String baseParam = request.getParameter("base");	
			String targetParam = request.getParameter("target");
			
			response.setContentType("text/html");
								
            PrintWriter out = response.getWriter();
			
			if(amountParam.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")){
			
				String res;
				
				if(baseParam.equals(targetParam)){
					res=amountParam;
				}
				else{
				
					String url = "http://www.google.com/ig/calculator?hl=en&q=" + amountParam + baseParam + "=?" + targetParam;
					
					
					URL u;
					InputStream is = null;
					DataInputStream dis;
					String s;
					//List<String> content = new ArrayList<String>(); 
					StringBuffer content = new StringBuffer();

					  try {

						 //u = new URL("http://www.google.com/ig/calculator?hl=en&q=100EUR=?USD");
						 u = new URL(url);

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
					
					int index = con.indexOf(":", 5);
					String subCon = con.substring(index);
					StringBuffer result = new StringBuffer();
					for(int i=0;i<subCon.length();i++){
						if(Character.isDigit(subCon.charAt(i)) || subCon.charAt(i)=='.'){
							result.append(subCon.charAt(i));
						}
					}
					res = result.toString();
				}
				
				String inp = "<input type=\"text\" name=\"result\" readonly=\"readonly\" value=\""+res+"\"/>";
				String p = "<form method=\"POST\" action=\"ConvertCurr\">";
				String am = "<input type=\"text\" id=\"amount\" name=\"amount\" value=\""+amountParam+"\"/>";
				String btn = "<input id=\"btn\" type=\"SUBMIT\" value=\"Convert\"/>";
				String selBase = "<select id=\"base\" name=\"base\">";
				String selTarget = "<select id=\"target\" name=\"target\">";
				String btnTd = "<td colspan=\"2\">";
				String btnReport="<input type=\"SUBMIT\" value=\"Get Report\"/>";
				String btnCode="<input type=\"SUBMIT\" value=\"Get Code\"/>";
				String formReport="<form method=\"Get\" action=\"GetReport\">";
				String formCode="<form method=\"Get\" action=\"GetCode\">";
				
				List<String> currencies = new ArrayList<String>();
				currencies.add("AED,United Arab Emirates Dirham");
				currencies.add("ARS,Argentine Peso");
				currencies.add("AUD,Australian Dollar");
				currencies.add("BGN,Bulgarian Lev");
				currencies.add("EUR,Euro");
				
				Iterator itBase = currencies.iterator();
				Iterator itTarget = currencies.iterator();
				
				out.println("<html> " +
							"<body>" +
							"<div>"+
				"<p>Type an amount and choose the desirable base and target currencies:</p>"+
					p+
						"<table>"+
						"<tr>"+
							"<td>"+"<label>Amount:</label>"+"</td>"+
							"<td>"+am+"</td>"+
						"</tr>"+
						"<tr>"+
							"<td><label>From:</label></td>"+
							"<td>"+
							selBase);

							
				while(itBase.hasNext()){
					String currency = (String)itBase.next();
					if(currency.substring(0,3).equals(baseParam)){
						String optn = "<option selected value=\""+currency.substring(0,3)+"\">"+currency+"</option>";
						out.println(optn);
					}
					else{
						String optn = "<option value=\""+currency.substring(0,3)+"\">"+currency+"</option>";
						out.println(optn);
					}
				}
														
								
				out.println("</select>"+
							"</td>"+
						"</tr>"+
						"<tr>"+
							"<td><label>To:</label></td>"+
							"<td>"+
							selTarget);
							
				while(itTarget.hasNext()){
					String currency = (String)itTarget.next();
					if(currency.substring(0,3).equals(targetParam)){
						String optn = "<option selected value=\""+currency.substring(0,3)+"\">"+currency+"</option>";
						out.println(optn);
					}
					else{
						String optn = "<option value=\""+currency.substring(0,3)+"\">"+currency+"</option>";
						out.println(optn);
					}
				}	
							
								
				out.println("</select>"+
							"</td>"+
						"</tr>"+
						"<tr>"+
							btnTd+btn+"</td>"+
						"</tr>"+
						"<tr>"+
							"<td><label>Result:</label></td>"+
							"<td>"+inp+"</td>"+
						"</tr>"+
					"<table>"+
					
				"</form>"+ 
				formReport + btnReport + "</form>" +
				formCode + btnCode + "</form>"+
							"</div>"+
							"</body>" + 
							"</html>");
							
		}
		else{
		
			out.println("<html>"+"<body>"+ "<p>You have to type a number for the amount!</p>"+"</body>"+"</html>");
		}
    }
}