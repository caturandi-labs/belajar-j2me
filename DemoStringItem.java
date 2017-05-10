import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
* @author Catur Andi Pamungkas
* @date 21/04/2017
*/
public class DemoStringItem extends MIDlet implements CommandListener, ItemCommandListener{
	
	Display display;
	Command commandExit;
	Form formString;
	StringItem plain,hyperlink,button;

	public DemoStringItem(){
		commandExit = new Command("Exit",Command.EXIT,0);
		formString = new Form("Demo String Item");
		formString.setCommandListener(this);
		formString.addCommand(commandExit);

		plain = new StringItem("Plain","Plain Text",Item.PLAIN);
		hyperlink = new StringItem("Google","http://www.google.co.id",Item.HYPERLINK);
		hyperlink.setDefaultCommand(new Command("Set",Command.OK,0));
		hyperlink.setItemCommandListener(this);

		button = new StringItem("Button","Klik Saya",Item.BUTTON);
		button.setDefaultCommand(new Command("OK",Command.OK,0));
		button.setItemCommandListener(this);

		formString.append(plain);
		formString.append(hyperlink);
		formString.append(button);
	}

	public void startApp(){
		if(display == null){
			display = Display.getDisplay(this);
		}
		display.setCurrent(formString);
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

	public void commandAction(Command c, Item item){
		if(item.getLabel().equals("Button")){
			System.out.println("Button Diklik!");
		}else if(item.getLabel().equalsIgnoreCase("Plain")){
			System.out.println("Plain Telah Diklik");
		}
	}
}