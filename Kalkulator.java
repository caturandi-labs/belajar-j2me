import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
* 
* @author Catur Andi Pamungkas
* @version 1.0.0
*
*/

public class Kalkulator extends MIDlet implements CommandListener{
	
	Display display;
	Form calcForm;
	TextField txtOperand1,txtOperand2, txtResult;
	ChoiceGroup choiceOperator;
	Command commandHitung, commandExit,commandReset;
	double hasil;
	
	public Kalkulator(){
		commandExit = new Command("Exit",Command.EXIT,0);
		commandHitung = new Command("Hitung",Command.OK,1);
		commandReset = new Command("Reset",Command.OK,1);
		
		calcForm = new Form("Kalkulator ");
		calcForm.setCommandListener(this);
		calcForm.addCommand(commandExit);
		calcForm.addCommand(commandHitung);
		calcForm.addCommand(commandReset);
		txtOperand1 = new TextField("Nilai Pertama","",25,TextField.DECIMAL);
		txtOperand2 = new TextField("Nilai Kedua","",25,TextField.DECIMAL);
		txtResult = new TextField("Hasil Perhitungan","",25,TextField.UNEDITABLE);

		choiceOperator = new ChoiceGroup("Operator",Choice.POPUP);
		choiceOperator.append("+",null);
		choiceOperator.append("-",null);
		choiceOperator.append("*",null);
		choiceOperator.append("/",null);
		choiceOperator.append("%",null);

		calcForm.append(txtOperand1);
		calcForm.append(choiceOperator);
		calcForm.append(txtOperand2);
		calcForm.append(txtResult);
	}

	public void startApp(){
		if(display == null){
			display = Display.getDisplay(this);
		}
		display.setCurrent(calcForm);
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

		if(c == commandHitung){
			double nilaiPertama = Double.parseDouble(txtOperand1.getString());
			double nilaiKedua = Double.parseDouble(txtOperand2.getString());
			
			hasil = 0;

			int index = choiceOperator.getSelectedIndex();
			String strOperator = choiceOperator.getString(index);
			System.out.println(strOperator);
			char operator = strOperator.charAt(0);
			switch(operator){
				case '+' : hasil = nilaiPertama + nilaiKedua; break;
				case '-' : hasil = nilaiPertama - nilaiKedua; break;
				case '*' : hasil = nilaiPertama * nilaiKedua; break;
				case '/' : hasil = nilaiPertama / nilaiKedua; break;
				case '%' : hasil = nilaiPertama % nilaiKedua; break;
			}
			txtResult.setString(String.valueOf(hasil));
		}

		if(c == commandReset){
			hasil = 0;
			txtOperand1.setString("");
			txtOperand2.setString("");
			txtResult.setString("");
		}
	}
}