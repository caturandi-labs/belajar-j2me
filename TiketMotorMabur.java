import java.util.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class TiketMotorMabur extends MIDlet implements CommandListener {
	
	Display display;
	Command commandExit,commandPesan;
	Form formUtama,formKonfirmasi;
	TextField txtNik,txtNama,txtUsia,txtJumlahTiket;
	ChoiceGroup choiceJenisKelamin,choiceKelas;
	DateField dateKeberangkatan;
	int jumlah;
	double harga,potongan;
	double total,subTotal;
	StringItem strNik,strNama,strUsia,strJenisKelamin,strKelas,strTanggalBerangkat,strJumlah,strTotal,strPotongan,strSubTotal;


	//init constructor
	public TiketMotorMabur(){
		commandExit = new Command("Exit",Command.EXIT,0);
		commandPesan = new Command("Pesan",Command.OK,1);

		formUtama = new Form("App Tiket Motor Mabur");
		formUtama.setCommandListener(this);
		formUtama.addCommand(commandExit);
		formUtama.addCommand(commandPesan);

		txtNik = new TextField("NIK","",60,TextField.ANY);
		txtNama = new TextField("Nama","",60,TextField.ANY);
		txtUsia = new TextField("Usia","",60,TextField.NUMERIC);
		txtJumlahTiket = new TextField("Jumlah Tiket","",60,TextField.NUMERIC);

		formUtama.append(txtNik);
		formUtama.append(txtNama);
		formUtama.append(txtUsia);

		choiceJenisKelamin = new ChoiceGroup("Jenis Kelamin",ChoiceGroup.EXCLUSIVE);
		choiceJenisKelamin.append("Laki-Laki",null);
		choiceJenisKelamin.append("Perempuan",null);

		formUtama.append(choiceJenisKelamin);

		choiceKelas = new ChoiceGroup("Kelas",ChoiceGroup.POPUP);
		choiceKelas.append("VVIP",null);
		choiceKelas.append("VIP",null);
		choiceKelas.append("Bisnis",null);
		choiceKelas.append("Ekonomi",null);
		formUtama.append(choiceKelas);

		dateKeberangkatan = new DateField("Tanggal Keberangkatan",DateField.DATE);
		formUtama.append(dateKeberangkatan);
		formUtama.append(txtJumlahTiket);

		formKonfirmasi = new Form("Konfirmasi Order : ");
		formKonfirmasi.setCommandListener(this);
		formKonfirmasi.addCommand(commandExit);
		formKonfirmasi.addCommand(commandPesan);


	}

	public void startApp(){
		if(display == null){
			display = Display.getDisplay(this);
		}
		display.setCurrent(formUtama);
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

		if(c == commandPesan && d==formKonfirmasi){
			clearDisplay();
			display.setCurrent(formUtama);
		}

		if(c == commandPesan && d == formUtama){
			if(choiceKelas.getSelectedIndex() == 0){
				harga = 2000000;
			}else if(choiceKelas.getSelectedIndex() == 1){
				harga = 1500000;
			}else if(choiceKelas.getSelectedIndex() == 2){
				harga = 1000000;
			}else{
				harga = 500000;
			}
			
			jumlah = Integer.parseInt(txtJumlahTiket.getString());
			subTotal = harga * jumlah;

			if(jumlah > 3){
				potongan = subTotal * 0.1;
				total = subTotal - potongan;
			}else{
				potongan = 0;
				total = subTotal-potongan;
			}

			initStringItem();

			display.setCurrent(formKonfirmasi);

		}
	}

	private void initStringItem(){
		strNik = new StringItem("NIK : " , txtNik.getString());
		strNama = new StringItem("Nama : " , txtNama.getString());
		strUsia = new StringItem("Usia : ", txtUsia.getString());
		strJenisKelamin = new StringItem("Jenis Kelamin : ", choiceJenisKelamin.getString(choiceJenisKelamin.getSelectedIndex()));
		strKelas = new StringItem("Kelas : " , choiceKelas.getString(choiceKelas.getSelectedIndex()));
		strTanggalBerangkat  = new StringItem("Tanggal Keberangkatan : " , formatTanggalOrder());
		strJumlah = new StringItem("Jumlah : " , txtJumlahTiket.getString());
		strPotongan = new StringItem("Potongan : ", Double.toString(potongan));
		strSubTotal = new StringItem("Sub Total : ",Double.toString(subTotal));
		strTotal = new StringItem("Total : " , Double.toString(total));

		formKonfirmasi.append(strNik);
		formKonfirmasi.append(strNama);
		formKonfirmasi.append(strUsia);
		formKonfirmasi.append(strJenisKelamin);
		formKonfirmasi.append(strKelas);
		formKonfirmasi.append(strTanggalBerangkat);
		formKonfirmasi.append(strJumlah);
		formKonfirmasi.append(strSubTotal);
		formKonfirmasi.append(strPotongan);
		formKonfirmasi.append(strTotal);
		

	} 

	private void clearDisplay(){
		txtNik.setString("");
		txtNama.setString("");
		txtUsia.setString("");
		txtJumlahTiket.setString("");
		dateKeberangkatan.setDate(null);
		choiceJenisKelamin.setSelectedIndex(0,true);
		choiceKelas.setSelectedIndex(0,true);
		jumlah = 0;
		harga = potongan = total = subTotal = 0;
		formKonfirmasi.deleteAll();
	}

	private String formatTanggalOrder(){
		Calendar calendar = Calendar.getInstance();
		Date tanggalOrder = dateKeberangkatan.getDate();
		calendar.setTime(tanggalOrder);
		int hari = calendar.get(Calendar.DAY_OF_MONTH);
		int noBulan = calendar.get(Calendar.MONTH);
		int tahun = calendar.get(Calendar.YEAR);

		String[] namaBulan = {
			"Januari","Pebruari","Maret","April","Mei","Juni",
			"Juli","Agustus","September","Oktober","Nopember",
			"Desember"
		}; 

		String bulan = namaBulan[noBulan];
		String result =  hari + "-" + bulan + "-" + tahun; 
		return result;
	}

}