import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
* @author Catur Andi Pamungkas
* @date 21/04/2017
*/
public class HelloWorld extends MIDlet implements CommandListener{
	
	Display display;
	Command commandExit;
	Alert alertHallo;
	Ticker tickerText;

	public HelloWorld(){
		commandExit = new Command("Exit",Command.EXIT,0);
		alertHallo = new Alert("Hallo Alert","Belajar JAVA J2ME (Micro Edition)",null,AlertType.INFO);
		tickerText = new Ticker("BELAJAR JAVA J2ME EDITION");
		alertHallo.setCommandListener(this);
		alertHallo.addCommand(commandExit);
		alertHallo.setTicker(tickerText);
		alertHallo.setTimeout(Alert.FOREVER);
	}

	public void startApp(){
		if(display == null){
			display = Display.getDisplay(this);
		}
		display.setCurrent(alertHallo);
	}

	public void pauseApp(){

	}

	public void destroyApp(boolean unconditional){

	}

	public void commandAction(Command c, Displayable d){
		if(c == commandExit){
			destroyApp(true);
			notifyDestroyed();
		}
	}
}