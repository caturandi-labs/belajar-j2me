import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;

public class BioData extends MIDlet implements CommandListener {
	Display display;
	Form formBiodata;
	ChoiceGroup choiceBahasa,choiceJenisKelamin,choicePendidikan;
	Command exitCommand,daftarCommand,resetCommand;
	Alert alertRegister;
	TextField namaLengkap,alamat,url,eMail;
	DateField tanggalLahir;

	public BioData(){

		exitCommand = new Command("Exit",Command.EXIT,0);
		daftarCommand = new Command("Daftar",Command.OK,1);
		resetCommand = new Command("Reset Field",Command.OK,1);

		formBiodata = new Form("Form Pendaftaran");
		formBiodata.setCommandListener(this);
		formBiodata.addCommand(exitCommand);
		formBiodata.addCommand(daftarCommand);
		formBiodata.addCommand(resetCommand);

		namaLengkap = new TextField("Nama Lengkap","",60,TextField.ANY);
		alamat = new TextField("Alamat","",60,TextField.ANY);
		
		formBiodata.append(namaLengkap);
		formBiodata.append(alamat);

		url = new TextField("URL","",60,TextField.URL);
		eMail = new TextField("E-Mail","",40,TextField.EMAILADDR);		

		choiceJenisKelamin = new ChoiceGroup("Jenis Kelamin",Choice.EXCLUSIVE);
		choiceJenisKelamin.append("Laki-Laki",null);
		choiceJenisKelamin.append("Perempuan",null);

		formBiodata.append(choiceJenisKelamin);

		choiceBahasa = new ChoiceGroup("Bahasa Yang Dikuasai ",Choice.MULTIPLE);
		choiceBahasa.append("JAVA",null);
		choiceBahasa.append("PHP",null);
		choiceBahasa.append("RUBY",null);
		formBiodata.append(choiceBahasa);

		choicePendidikan = new ChoiceGroup("Pendidikan",Choice.POPUP);
		choicePendidikan.append("SD",null);
		choicePendidikan.append("SMP",null);
		choicePendidikan.append("SMA/K",null);
		choicePendidikan.append("S1",null);
		choicePendidikan.append("S2",null);
		choicePendidikan.append("Lainya",null);
		formBiodata.append(choicePendidikan);

		formBiodata.append(url);
		formBiodata.append(eMail);

		tanggalLahir = new DateField("Tanggal Lahir",DateField.DATE);
		formBiodata.append(tanggalLahir);

		alertRegister = new Alert("Info Pendaftaran","",null,AlertType.INFO);
		alertRegister.setTimeout(Alert.FOREVER);

	}

	public void startApp(){
		if(display == null){
			display = Display.getDisplay(this);
		}
		display.setCurrent(formBiodata);
	}

	public void pauseApp(){}

	public void destroyApp(boolean conditional){}

	public void commandAction(Command c, Displayable d){
		if(c == exitCommand){
			destroyApp(true);
			notifyDestroyed();
		}else if(c == daftarCommand){
			registerUser();
		}else if(c == resetCommand){
			clearDisplay();
		}
	}

	private void registerUser(){
		String nama = namaLengkap.getString();
		String alamatRumah = alamat.getString();
		String jenisKelamin  = choiceJenisKelamin.getString(choiceJenisKelamin.getSelectedIndex());
		String bahasa = "";
		for(int i=0; i<choiceBahasa.size(); i++){
			if(choiceBahasa.isSelected(i) && (i == choiceBahasa.size()-1)){
				bahasa += choiceBahasa.getString(i);
			}else if(choiceBahasa.isSelected(i)){
				bahasa += choiceBahasa.getString(i) + ",";
			}
		} 
		String pendidikanUser = choicePendidikan.getString(choicePendidikan.getSelectedIndex());
		String website = url.getString();
		String emailAddress = eMail.getString();
		Date dateLahir = new Date(tanggalLahir.getDate().getTime());

		String showString = "Nama Lengkap : " + nama + "\n"
							+"Alamat : " + alamatRumah + "\n"
							+"Jenis Kelamin : " + jenisKelamin + "\n"
							+"Bahasa : " + bahasa + "\n" 
						    +"Pendidikan :" + pendidikanUser + "\n"
						    +"Website : " + website + "\n"
						    +"E-Mail : " + emailAddress + "\n"
						    +"Tanggal Lahir : " + dateLahir; 

		alertRegister.setString(showString);
		display.setCurrent(alertRegister);
	}

	private void clearDisplay(){
		namaLengkap.setString("");
		alamat.setString("");
		url.setString("");
		eMail.setString("");
	}
}