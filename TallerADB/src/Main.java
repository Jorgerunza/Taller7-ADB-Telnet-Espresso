import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Main {


	public static final String ADB_ROOT = "C:\\Users\\Jorge\\AppData\\Local\\Android\\sdk\\platform-tools";
	public static final String ADB_INPUT = "adb shell input ";
	public static final String TELNET_TOKEN = "wGmyGPQWIZs0rWDi";
	public static final String EMULATOR_PORT = "5554";
	public static final String INSTALL_APK = "adb install -r ";
	public static final String RUN_APK = "adb shell am start -n ";

	private static BufferedWriter connectToTelnet() throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process telnet = rt.exec("telnet localhost "+EMULATOR_PORT);
		return new BufferedWriter(new OutputStreamWriter(telnet.getOutputStream()));
	}
	
	
	
	public static void tap(Runtime rt,Random random) throws IOException {
    int x = random.nextInt(1080);
    int y = random.nextInt(1920);
    rt.exec(ADB_INPUT + "tap " + x + " " + y);
	}
	
	
	public static void text(Runtime rt,Random random) throws IOException {
		    String textIn= UUID.randomUUID().toString().replace("-", "");
		    rt.exec(ADB_INPUT + "text \""+(textIn)+"\"");
		}
	
	public static void swipe(Runtime rt,Random random) throws IOException {
	    int x = random.nextInt(50);
	    int y = random.nextInt(150);
	    int v = random.nextInt(400);
	    int z = random.nextInt(700);	
	    rt.exec(ADB_INPUT + "swipe " + x +" "+ y +" "+ v +" "+ z +" ");

	}
	
	public static void keyevent(Runtime rt,Random random) throws IOException {
		int tecla = random.nextInt(300);
	    rt.exec(ADB_INPUT + "keyevent " + tecla);
	}


	public static void rotate() throws IOException {
		BufferedWriter out = connectToTelnet();
		out.write("auth "+TELNET_TOKEN+"\n");
		out.write("rotate\n");
		out.write("quit\n");
		out.flush();
	}


	public static void speed(Random random) throws IOException {
	    int x = random.nextInt(3000);
	    int y = random.nextInt(10000);
		BufferedWriter out = connectToTelnet();
		out.write("auth "+TELNET_TOKEN+"\n");
		out.write("network speed "+(x)+" "+(y)+"\n");
		out.write("quit\n");
		out.flush();
	}


	public static void sensor(Random random) throws IOException {
		String[] names  = new String[]{"acceleration","gyroscope","magnetic-field","orientation","temperature","proximity","light","pressure","humidity","magnetic-field-uncalibrated"};
		int n= random.nextInt(9);
        float x = random.nextFloat() * (99 - 0) + 0;
        float y = random.nextFloat() * (99 - 0) + 0;
        float z = random.nextFloat() * (99 - 0) + 0;
        
		BufferedWriter out = connectToTelnet();
		out.write("auth "+TELNET_TOKEN+"\n");
		out.write("sensor set "+(names[n])+" "+(x)+":"+(y)+":"+(z)+"\n");
		out.write("sensor get "+(names[n])+"\n");
		out.write("quit\n");
		out.flush();
	}

	public static void InstallApp(String appTest, Runtime rt) throws IOException, InterruptedException{
			Process install = rt.exec(INSTALL_APK + appTest);
			install.waitFor();
	}

	public static void RunApp(String appId, Runtime rt) throws IOException, InterruptedException{
			Process runapp = rt.exec(RUN_APK + appId);
			runapp.waitFor();
	}
	


	public static void main(String[] args) {
		try {

			Runtime rt = Runtime.getRuntime();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			Random random = new Random(12345);
			int numEvents =0;
			String appComands ="";
			String appProb ="";
			String appTest ="";
			String appId ="";
			
			//Menu
			System.out.print("Por favor seleccione: \n\n");
			System.out.print("1. número de eventos a realizar: ");
			numEvents = Integer.parseInt(br.readLine());


			System.out.print("2. Ingrese los comandos a probar, separelos por , sin espacios (Ej tap,text,swipe,keyevent,rotate,speed,sensor):");
			appComands = br.readLine();
			String[] comandsList = appComands.split(",");


			System.out.print("3. Ingrese el Nombre de la Aplicacion a Probar ( ruta al apk EJ: C:\\Users\\Jorge\\Desktop\\transmilenio-y-sitp-14-8-1.apk): ");
			appTest = br.readLine();

			System.out.print("4. Ingrese Id de la Aplicacion (Ej: com.rutasdeautobuses.transmileniositp/com.rutasdeautobuses.transmileniositp.Home): ");
			appId = br.readLine();


			InstallApp(appTest,rt);
			RunApp(appId,rt);
			
			String[] cList = new String[] {"tap","text","swipe","keyevent","rotate","speed","sensor" };
			int i = 0;
			while(i < numEvents) {
				
				
				int size= comandsList.length;
				int r = random.nextInt(size);

					switch (comandsList[r]) {
		              case "tap" : 
		            	  tap(rt, random);
		            	   break;
		              case "text" :
		            	  text( rt, random);
		                  break;
		              case "swipe" : 
		            	  swipe( rt, random);
		                  break;
		              case "keyevent" :
		            	  keyevent( rt, random) ;
		                  break;
		              case "rotate" :
		            	  rotate() ;
		                  break;  
		              case "speed" : 
		            	  speed(random);
		                  break;
		              case "sensor" :
		            	  sensor(random);
		            	  break;
					  }
	                i++;
	                Thread.sleep(1000);
					
				}
			System.out.print("Fin Ejecucion");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Error: "+ e.getMessage());
		}
	}
}