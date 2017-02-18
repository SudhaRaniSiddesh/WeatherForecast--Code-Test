package com.sudha;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/weatherServlet")
public class weatherServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//private PropertiesReader propertiesReader= new PropertiesReader("/application.properties");;

	
	@Override
	public void init() throws ServletException {
		 		try {
		
		} catch (Exception e) {
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
    //posting weather data to a file based on location
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		PrintWriter out = response.getWriter();
      
        String place=request.getParameter("place");
		
		PositionFinder pf=new PositionFinder();
		
		String[] locationParam=PositionFinder.getLatLonPositions(place);
		
		String latitude=locationParam[0];
		String longitude=locationParam[1];
		
		String elevation=PositionFinder.getElevation(latitude,longitude);
		
		String date=PositionFinder.getTime();
		
		/*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		Date date=new Date();
		*/
		
		//calculate temp , humidity, pressure based on lon, lat, ele
		
		String tempe=PositionFinder.getTemperature(latitude,longitude,elevation);
		String pressure=PositionFinder.getPressure(elevation);
		String humidity=PositionFinder.getHumidity(elevation);
		String condition=PositionFinder.getCondition(tempe, pressure, humidity);
		
		out.printf(place+"|"+latitude+","+longitude+","+elevation+"|"+date+"|"+condition+"|"+tempe+"|"+pressure+"|"+humidity);
		
		//storing it in file system
		  File file = new File ("test.txt");
		  BufferedWriter bufferout = new BufferedWriter(new FileWriter(file, true)); 
		  bufferout.write(place+"|"+latitude+","+longitude+","+elevation+"|"+date+"|"+condition+"|"+tempe+"|"+pressure+"|"+humidity);
		  bufferout.newLine();
		  bufferout.close();
		
	}
		
		


	/**
	 * fetching entire weather data that has been stored in local file system
	 */
	
	@Override
	protected  void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
	
		String storeddata =request.getParameter("fetchdata");
			
		if(request.getParameter("fetchdata").equals("true"))
			
		{			
			File f = new File ("test.txt");
			FileReader fileReader=new FileReader(f);
			
			BufferedReader buffReader = new BufferedReader(fileReader);
			
			String buffer = new String();
			while( (buffer = buffReader.readLine() ) != null)
            out.println(buffer);
			buffReader.close();
       
		}
		
	}

		@Override
		public void destroy() {
		super.destroy();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
