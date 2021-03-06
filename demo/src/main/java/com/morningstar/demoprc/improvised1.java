package com.morningstar.demoprc;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.rabbitmq.client.ConnectionFactory;

import com.rabbitmq.client.Channel;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.CoreAdminParams.CoreAdminAction;



public class improvised1
{
	public void solrStatusCheck() throws SolrServerException, IOException
	   {
		             String urlString="http://rsmdrdevap8022:8983/solr/";
		             SolrClient solrserver = new HttpSolrClient.Builder(urlString).build();			
	   		         CoreAdminRequest request = new CoreAdminRequest();
		             request.setAction(CoreAdminAction.STATUS);
	                 CoreAdminResponse cores = request.process(solrserver);

							   	  for (int i = 0; i < cores.getCoreStatus().size(); i++) {
							   		     if(cores.getStatus()==0)
							   		     {
							   		    	 System.out.println("working well");
							   		     }
							   		     else
							   		     {
							   		    	System.out.println("notworking well");
							   		     }
							    	    
							    	  }
	    }
							
		
    public void rabitStatusCheck()
		{
                 	ConnectionFactory factory = new ConnectionFactory();
                 	factory.setUsername("admin");
                 	factory.setPassword("admin123");
                 	factory.setVirtualHost("/");
                 	factory.setHost("rsmdrdevap8002");
                 	factory.setPort(5672);
    	
                 		try {
                 			com.rabbitmq.client.Connection connection = factory.newConnection();
                 			System.out.println("rabitmq is up");
                 			}
                 		catch(Exception e)
                 			{
                 				System.out.println("rabitmq is down");
                 			}
		
		
		
		
		}
    
    
    public void dbCheck() throws ClassNotFoundException, SQLException
    {
    	Class.forName("net.sourceforge.jtds.jdbc.Driver");
    	Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://rsmdrdevdb8001;user=RP-MDR-NONPROD;password=rpMdr@2015;database=MDR_EntitlementData");            
    	if(conn!=null)
    	{
    		System.out.println("up");
    	}
    }
	
	
	public static void main(String[] args) throws SolrServerException, IOException, ClassNotFoundException, SQLException {
	
	improvised1 obj = new improvised1();
	obj.solrStatusCheck();
	obj.rabitStatusCheck();
	obj.dbCheck();

}
}