import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;

/**
* @author Catur Andi Pamungkas
* @version 1.00
* @date 24/04/2017
*/
public class LoginHitungBangun extends MIDlet implements CommandListener {
	
	Display display;
	Command exitCommand,hitungCommand,loginCommand,pilihCommand,backCommand;
	Form formLogin,formUtama,formPersegi,formSegitiga,formLingkaran;
	TextField txtUsername,txtPassword,txtLuas,txtKeliling,txtPanjang,txtLebar,txtTinggi,txtAlas,txtRadius;
	ChoiceGroup choicePilihBangun;
	String textForAlert;
	Alert alertLuas,alertLogin;

	public LoginHitungBangun(){
		
		exitCommand = new Command("Exit",Command.EXIT,0);
		hitungCommand = new Command("Hitung",Command.OK,1);
		pilihCommand = new Command("Pilih",Command.OK,1);
		loginCommand = new Command("Login",Command.OK,1);
		backCommand = new Command("Kembali",Command.BACK,0);

		txtPanjang = new TextField("Panjang","",30,TextField.DECIMAL);
		txtLebar = new TextField("Lebar","",30,TextField.DECIMAL);
		txtTinggi = new TextField("Tinggi","",30,TextField.DECIMAL);
		txtAlas = new TextField("Alas","",30,TextField.DECIMAL);
		txtRadius = new TextField("Radius","",30,TextField.DECIMAL);

		formLogin = new Form("Login");
		formLogin.setCommandListener(this);
		formLogin.addCommand(exitCommand);
		formLogin.addCommand(loginCommand);
		txtUsername = new TextField("Username","",30,TextField.ANY);
		txtPassword = new TextField("Password","",30,TextField.PASSWORD);
		formLogin.append(txtUsername);
		formLogin.append(txtPassword);

		formUtama = new Form("Form Menu Utama");
		choicePilihBangun = new ChoiceGroup("Pilih Bangun",Choice.POPUP);
		choicePilihBangun.append("Persegi Panjang",null);
		choicePilihBangun.append("Segitiga",null);
		choicePilihBangun.append("Lingkaran",null);
		formUtama.append(choicePilihBangun);
		formUtama.addCommand(exitCommand);
		formUtama.addCommand(pilihCommand);
		formUtama.setCommandListener(this);

		formPersegi = new Form("Persegi Panjang");
		formPersegi.setCommandListener(this);
		formPersegi.addCommand(backCommand);
		formPersegi.addCommand(hitungCommand);
		formPersegi.append(txtPanjang);
		formPersegi.append(txtLebar);

		formSegitiga = new Form("Segitiga");
		formSegitiga.setCommandListener(this);
		formSegitiga.addCommand(backCommand);
		formSegitiga.addCommand(hitungCommand);
		formSegitiga.append(txtAlas);
		formSegitiga.append(txtTinggi);

		formLingkaran = new Form("Lingkaran");
		formLingkaran.setCommandListener(this);
		formLingkaran.addCommand(backCommand);
		formLingkaran.addCommand(hitungCommand);
		formLingkaran.append(txtRadius);
		
		alertLuas = new Alert("Hasil Luas","",null,AlertType.INFO);
		alertLuas.setTimeout(Alert.FOREVER);

		alertLogin = new Alert("Login Notification","",null,AlertType.INFO);
		alertLogin.setTimeout(4000);

	}

	public void startApp(){
		if(display == null){
			display = Display.getDisplay(this);
		}
		display.setCurrent(formLogin);
	}

	public void pauseApp(){}
	public void destroyApp(boolean conditional){}

	public void commandAction(Command c, Displayable d){
		if(c == exitCommand){
			destroyApp(true);
			notifyDestroyed();
		}
		if(c == loginCommand){
			authenticate();
		}

		if(c == pilihCommand && d == formUtama){
			int index = choicePilihBangun.getSelectedIndex();
			System.out.println(index);
			switch(index){
				case 0 : display.setCurrent(formPersegi); break;
				case 1 : display.setCurrent(formSegitiga); break;
				case 2 : display.setCurrent(formLingkaran); break;
			}
		}

		if(c == backCommand){
			display.setCurrent(formUtama);
		}

		if(c == hitungCommand && d == formPersegi){
			double panjang = Double.parseDouble(txtPanjang.getString());
			double lebar = Double.parseDouble(txtLebar.getString());
			double luasPersegi = hitungLuas(panjang,lebar,formPersegi);
			showResult(luasPersegi,formPersegi);
		}else if(c == hitungCommand && d == formSegitiga){
			double alas = Double.parseDouble(txtAlas.getString());
			double tinggi = Double.parseDouble(txtTinggi.getString());
			double luasSegitiga = hitungLuas(alas,tinggi,formSegitiga);
			showResult(luasSegitiga,formSegitiga);
		}else if(c == hitungCommand && d == formLingkaran){
			double radius = Double.parseDouble(txtRadius.getString());
			double luasLingkaran = hitungLuas(radius);
			showResult(luasLingkaran,formLingkaran);
		}
	}

	/**
	* @param double luas : hasil dari perhitungan akhir
	* @param displayable form atau element displayable lain
	*/
	private void showResult(double luas,Displayable d){
		alertLuas.setString("Luas " + d.getTitle() + " adalah " + luas);
		display.setCurrent(alertLuas);
	}

	/**
	* @param String textForAlert (Teks yang akan ditempilkan)
	*/
	private void showResult(String textForAlert){
		alertLogin.setTitle("Login Gagal");
		alertLogin.setString(textForAlert);
		display.setCurrent(alertLogin);
	}

	/**
	* @param null
	* Login Authentication
	*/
	private void authenticate(){
		String username = txtUsername.getString();
		String password = txtPassword.getString();
		if(username.equals("admin")){
			if(password.equals("admin")){
				display.setCurrent(formUtama);
			}else{
				textForAlert = "Username Benar, Password salah!, Login Gagal";
				showResult(textForAlert);
			}
		}else{
			if(password.equals("admin")){
				textForAlert = "Username salah, Password Benar!, Login Gagal";
				showResult(textForAlert);
			}else{
				textForAlert = "Username Salah, Password salah!, Login Gagal";
				showResult(textForAlert);
			}
		}
	}

	private double hitungLuas(double param1,double param2,Displayable d){
		if(d == formPersegi){
			return param1 * param2;
		}else{
			return param1 * param2 / 2;
		}
	}

	private double hitungLuas(double radius){
		final double PHI = 3.1456;
		return PHI * radius * radius;
	}

}