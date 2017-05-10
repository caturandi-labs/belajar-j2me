import java.util.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Template extends MIDlet implements CommandListener {
	
	Display display;
	Command commandExit;

	//init constructor
	public Template(){
		commandExit = new Command("Exit",Command.EXIT,0);
	}

	public void startApp(){
		if(display == null){
			display = Display.getDisplay(this);
		}
		display.setCurrent(null);
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