import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;

public class PenjualanBarang extends MIDlet implements CommandListener {
	
	Display display;
	Command exitCommand,pesanCommand;
	TextField txtNamabarang,txtHarga,txtJumlah,txtNamaPelanggan,txtAlamat;
	TextField txtBerat;
	Form formUtama,formOrder;
	ChoiceGroup choiceJasaPengiriman, choiceBubbleWarp;
	DateField tanggalPenjualan;
	double jumlah,total,berat,biayaPengiriman,diskon,harga,pengemasan,totalBayar;

	StringItem strNamaBarang,strHargaBarang,strJumlah,strPelanggan,strAlamat,strTanggal;
	StringItem strBubble,strBerat,strJasaKurir,strSubTotal,strBiaya,strDiskon,strSetelahDiskon;


	public PenjualanBarang(){
		exitCommand = new Command("Exit",Command.EXIT,0);
		pesanCommand = new Command("Pesan",Command.OK,1);

		formUtama = new Form("App penjualan");
		formUtama.setCommandListener(this);
		formUtama.addCommand(exitCommand);
		formUtama.addCommand(pesanCommand);

		txtNamabarang = new TextField("Nama Barang","",60,TextField.ANY);
		txtHarga = new TextField("Harga Barang","",60,TextField.DECIMAL);
		txtJumlah = new TextField("Jumlah Barang","",60,TextField.NUMERIC);
		txtNamaPelanggan = new TextField("Nama Pelanggan","",60,TextField.ANY);
		txtAlamat = new TextField("Alamat","",60,TextField.ANY);
		tanggalPenjualan = new DateField("Tanggal Penjualan",DateField.DATE);

		formUtama.append(txtNamabarang);
		formUtama.append(txtHarga);
		formUtama.append(txtJumlah);
		formUtama.append(txtAlamat);
		formUtama.append(tanggalPenjualan);
		formUtama.append(txtNamaPelanggan);

		choiceBubbleWarp = new ChoiceGroup("Pengemasan",ChoiceGroup.EXCLUSIVE);
		choiceBubbleWarp.append("Dengan Bubble Warp",null);
		choiceBubbleWarp.append("Tanpa Bubble Warp",null);
		formUtama.append(choiceBubbleWarp);

		txtBerat = new TextField("Berat","",60,TextField.DECIMAL);
		formUtama.append(txtBerat);

		choiceJasaPengiriman = new ChoiceGroup("Jasa pengiriman",ChoiceGroup.POPUP);
		choiceJasaPengiriman.append("JNE",null);
		choiceJasaPengiriman.append("J&T",null);
		choiceJasaPengiriman.append("TIKI",null);
		formUtama.append(choiceJasaPengiriman);

		formOrder = new Form("Confirmation");
		formOrder.setCommandListener(this);
		formOrder.addCommand(exitCommand);
		formOrder.addCommand(pesanCommand);


			
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

	public void commandAction(Command c , Displayable d){
		if(c == pesanCommand && d==formUtama){
			harga = Double.parseDouble(txtHarga.getString());
			jumlah = Double.parseDouble(txtJumlah.getString());
			berat = Double.parseDouble(txtBerat.getString());
			biayaPengiriman = checkJasaPengiriman();
			pengemasan = checkPengemasan();
			total = harga * jumlah;
			total += (berat * biayaPengiriman);
			total += pengemasan;
			if(total >= 300000){
				diskon = 50000;
			}else{
				diskon = 0;
			}

			totalBayar = total-diskon;

			initStringItem();
			display.setCurrent(formOrder);
		}

		if(c == exitCommand){
			destroyApp(true);
			notifyDestroyed();
		}

		if(c == pesanCommand && d== formOrder){
			txtBerat.setString("");
			txtJumlah.setString("");
			txtHarga.setString("");
			txtNamabarang.setString("");
			txtAlamat.setString("");
			txtNamaPelanggan.setString("");
			tanggalPenjualan.setDate(null);
			display.setCurrent(formUtama);

		}
	}

	private double checkJasaPengiriman(){
		double biaya;
		if(choiceJasaPengiriman.getSelectedIndex()==0){
			biaya = 30000;
		}else if(choiceJasaPengiriman.getSelectedIndex()==1){
			biaya = 30000;
		}else{
			biaya = 25000;
		}
		return biaya;
	}

	private double checkPengemasan(){
		double biayaPengemasan;
		if(choiceBubbleWarp.getSelectedIndex()==0){
			biayaPengemasan = 3000;
		}else{
			biayaPengemasan = 0;
		}
		return biayaPengemasan;
	}

	private void initStringItem(){
		strNamaBarang = new StringItem("Nama Barang : " , txtNamabarang.getString());
		strHargaBarang = new StringItem("Harga Barang : " , txtHarga.getString());
		strJumlah = new StringItem("Jumlah Barang : " ,txtJumlah.getString());
		strPelanggan = new StringItem("Pelanggan : " , txtNamaPelanggan.getString());
		strAlamat = new StringItem("Alamat : " , txtAlamat.getString());
		strBubble = new StringItem("Dengan Bubble Warp : " , choiceBubbleWarp.getString(choiceBubbleWarp.getSelectedIndex()));
		strBerat = new StringItem("Berat : " , txtBerat.getString());
		strJasaKurir = new StringItem("Jasa Kurir : " , choiceJasaPengiriman.getString(choiceJasaPengiriman.getSelectedIndex()));
		strSubTotal = new StringItem("Sub Total : " , Double.toString(harga * jumlah));
		strBiaya=  new StringItem("Biaya Pengiriman : " , Double.toString(biayaPengiriman));
		strDiskon =  new StringItem("Diskon : " , Double.toString(diskon)) ;
		strSetelahDiskon = new StringItem("Setelah Diskon : " , Double.toString(totalBayar));
		strTanggal = new StringItem("Tanggal : " , formatTanggalOrder());
		formOrder.append(strNamaBarang);
		formOrder.append(strHargaBarang);
		formOrder.append(strJumlah);
		formOrder.append(strPelanggan);
		formOrder.append(strAlamat);
		formOrder.append(strBubble);
		formOrder.append(strBerat);
		formOrder.append(strJasaKurir);
		formOrder.append(strSubTotal);
		formOrder.append(strBiaya);
		formOrder.append(strDiskon);
		formOrder.append(strSetelahDiskon);
	}

	private String formatTanggalOrder(){
		Calendar calendar = Calendar.getInstance();
		Date tanggalOrder = tanggalPenjualan.getDate();
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