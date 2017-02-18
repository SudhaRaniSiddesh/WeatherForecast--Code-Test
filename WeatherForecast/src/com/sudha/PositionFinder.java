package com.sudha;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.Random;

//import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

public class PositionFinder {
	
	
		private static final String googleUrl="https://maps.googleapis.com/maps/api";
	
    public static String[] getLatLonPositions(String location){
		
		
		String[] result=null;
	    int responseCode=0;
		try{
		
			String url=googleUrl+"/geocode/xml?address="+URLEncoder.encode(location, "UTF-8") + "&sensor=true";
						
		URL finalUrl= new URL(url);
		
		HttpURLConnection httpConnection = (HttpURLConnection)finalUrl.openConnection();
	    httpConnection.connect();
	    responseCode = httpConnection.getResponseCode();
	    if(responseCode == 200)
	    {
	   
	    	      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
	    	      Document document = builder.parse(httpConnection.getInputStream());
	    	      XPathFactory xPathfactory = XPathFactory.newInstance();
	    	      XPath xpath = xPathfactory.newXPath();
	    	      XPathExpression expr = xpath.compile("/GeocodeResponse/status");
	    	      String status = (String)expr.evaluate(document, XPathConstants.STRING);
	    	      if(status.equals("OK"))
	    	      {
	    	         expr = xpath.compile("//geometry/location/lat");
	    	         String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
	    	         expr = xpath.compile("//geometry/location/lng");
	    	         String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
	    	         result=new String[] {latitude, longitude};
	    }
	    	      else{
	    	    	  throw new Exception("Error from the API - response status: "+status);
	    	      }
	    }
	    	   
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		  return result;
		}


//get elevation using google API
public static String getElevation(String latitude, String longitude){
	
	
	String elevation=null;
    int responseCode=0;
	try{
	
		String location=latitude+","+longitude;
	    String url=googleUrl+"/elevation/xml?locations="+URLEncoder.encode((location), "UTF-8")+"&key=AIzaSyAOigPSF7fvX2G3eOHPO-PIUM5AZDLP-Co&sensor=true";
	    URL finalUrl= new URL(url);
	
	HttpURLConnection httpConnection = (HttpURLConnection)finalUrl.openConnection();
    httpConnection.connect();
    responseCode = httpConnection.getResponseCode();
    if(responseCode == 200)
    {
   
    	      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
    	      Document document = builder.parse(httpConnection.getInputStream());
    	      XPathFactory xPathfactory = XPathFactory.newInstance();
    	      XPath xpath = xPathfactory.newXPath();
    	      XPathExpression expr = xpath.compile("/ElevationResponse/status");
    	      String status = (String)expr.evaluate(document, XPathConstants.STRING);
    	      if(status.equals("OK"))
    	      {
    	         expr = xpath.compile("//elevation");
    	         elevation = (String)expr.evaluate(document, XPathConstants.STRING);
    	         
    }
    	      else{
    	    	  throw new Exception("Error from the API - response status: "+status);
    	      }
    }
    	   
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	  return elevation;
	}


//roughly calculating tempe based on positions & time

public static String getTemperature(String lat, String lon, String ele)
{
	String temper=null;
	
	//( 3.5 x Change in elevation)/1000 = temp loss due to elevation change
	//Double tempval=((lat.charAt(0)*lon.charAt(0)*Float.parseFloat(ele))*(-15.9))/1000;
	int latsign=1;
	int lonsign=1;
	
    if (String.valueOf((lat.charAt(0))).equals("-"))
    {
    	latsign=-1;
    }
    if (String.valueOf((lon.charAt(0))).equals("-"))
    {
    	lonsign=-1;
    }
    Double tempval=((latsign*lonsign*Float.parseFloat(ele))*(-15.9))/1000;
    DecimalFormat df = new DecimalFormat(".#");
    temper=(df.format(tempval));
    
    return temper;
    }


  //atmospheric pressure decrease with increase in elevation
    //trying to roughly calculate pressure using barometric formula by taking default value
    public static String getPressure(String ele) {
    	
	  String pressure=null;
      Double pressure1= ((0.12)*(Float.parseFloat(ele))*100);
      DecimalFormat df = new DecimalFormat(".#");
      pressure=(df.format(pressure1));
    
    return pressure;
    }
    
    
    // Pv = RH * Es
    //where Pv= pressure of water vapor (partial pressure)
    //RH = relative humidity (expressed as a decimal value)
   // Es = saturation vapor pressure ( multiply mb by 100 to get Pascals)

    public static String getHumidity(String ele)
    {
    	//as humidity is inversly proportional to elevation
    	
    	Double humid=(double) ((1/Float.parseFloat(ele))*1000);
    	
    	
    	
    	//Double humid=(double) (Float.parseFloat(pres)/Float.parseFloat(temp));
    	  	
    	String humidity=String.valueOf(Math.round(humid));
        	
    	return humidity;
    
    }
    
    public static String getCondition(String temp, String pres, String humdity)
    {
    	String condition=null;
    	
    	Float temperature=Float.parseFloat(temp);
    	Float humidity=Float.parseFloat(humdity);
    	Float pressure=Float.parseFloat(pres);
    	
    	//
    	if(temperature < (-1)&& pressure < 200.0 )
    	{
    		condition="snow";
    	}
    	//if humidity is more then chances of rain
    	else if((temperature < 5.0)&& humidity > 50.0 ){
    		
    		condition="rainy";
    	}
    	else{
    		condition="sunny";
    	}
    	
    	
    	return condition;
    }
    
    {
       	

    }
    
    //trying to generate random time here
    public static String getTime(){
          	 
		    Random r = new Random();
		    long t1 = System.currentTimeMillis() + r.nextInt();
		    long t2 = t1 + 2 * 60 * 1000 + r.nextInt(60 * 1000) + 1;
		    java.sql.Date d1 = new java.sql.Date(t1);
		    //DateTime d2 = DateTime.ParseExact(t1);
		    	    
		    //final Random random = new Random();
		     int millisInDay = 24*60*60*1000;
		    Time time = new Time((long)r.nextInt(millisInDay));
		    
		    System.out.println("time"+time);
		    System.out.println("data&time"+(d1)+"T"+time+"Z");
		    
		    return d1+"T"+time+"Z";
    	
    }
    
    
    
    
	
	}

	


