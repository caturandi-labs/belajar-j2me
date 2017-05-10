import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class PenjualanPulsa extends MIDlet implements CommandListener, ItemCommandListener {
    
    Display display;
    Command exitCommand,orderCommand;
    Form formPenjualan,formCheckOut;
    TextField txtNama,txtAlamat,txtNoTelpon;
    DateField datePenjualan;
    ChoiceGroup choiceOperator,choiceNominal;
    StringItem strNama,strAlamat,strTanggal,strOperator,strNominal,strNoTelepon,strTotal;
    String namaOperator;
    double nominal,total;
            
    public PenjualanPulsa() {
        exitCommand = new Command("Exit",Command.EXIT,0);
        orderCommand = new Command("Order",Command.OK,1);
        
        formPenjualan = new Form("Dodolan Pulsa");
        formPenjualan.setCommandListener(this);
        formPenjualan.addCommand(exitCommand);
        formPenjualan.addCommand(orderCommand);
        
        txtNama = new TextField("Nama", "", 60, TextField.ANY);
        txtNoTelpon = new TextField("No Telepon", "",60,TextField.PHONENUMBER);
        txtAlamat = new TextField("Alamat", "", 60, TextField.ANY);
        
        formPenjualan.append(txtNama);
        formPenjualan.append(txtAlamat);
        formPenjualan.append(txtNoTelpon);
        
        
        datePenjualan = new DateField("Tanggal", DateField.DATE);
        formPenjualan.append(datePenjualan);
        
        choiceOperator = new ChoiceGroup("Operator", ChoiceGroup.POPUP);
        choiceOperator.append("Indosat", null);
        choiceOperator.append("Telkomsel", null);
        choiceOperator.append("Axis", null);
        choiceOperator.append("XL", null);
        choiceOperator.append("Three", null);
        formPenjualan.append(choiceOperator);
        
        choiceNominal = new ChoiceGroup("Nominal", ChoiceGroup.EXCLUSIVE);
        choiceNominal.append("5000", null);
        choiceNominal.append("10000", null);
        choiceNominal.append("25000", null);
        formPenjualan.append(choiceNominal);

        formCheckOut = new Form("Checkout");
        formCheckOut.setCommandListener(this);
        formCheckOut.addCommand(exitCommand);
        formCheckOut.addCommand(orderCommand);
    }
    
    
    public void startApp() {
        if(display == null){
            display = Display.getDisplay(this);
        }
        display.setCurrent(formPenjualan);
    }
    
    public void pauseApp() {
    
    }
    
    public void destroyApp(boolean unconditional) {
        
    }

    public void commandAction(Command c, Displayable d) {
        if(c == exitCommand){
            destroyApp(true);
            notifyDestroyed();
        }
        
        if(c == orderCommand && d == formCheckOut){
            clearDisplay();
            display.setCurrent(formPenjualan);
        }
        
        if(c == orderCommand && d==formPenjualan){
            if(choiceOperator.getSelectedIndex() == 0){
                namaOperator = choiceOperator.getString(choiceOperator.getSelectedIndex());
                nominal = checkNominal();
                total = calculateTotal(nominal, 0.02);
            }else if(choiceOperator.getSelectedIndex() == 1){
                namaOperator = choiceOperator.getString(choiceOperator.getSelectedIndex());
                nominal = checkNominal();
                total = calculateTotal(nominal, 0.03);
            }else if(choiceOperator.getSelectedIndex() == 2){
                namaOperator = choiceOperator.getString(choiceOperator.getSelectedIndex());
                nominal = checkNominal();
                total = calculateTotal(nominal, 0.04);
            }else if(choiceOperator.getSelectedIndex() == 3){
                namaOperator = choiceOperator.getString(choiceOperator.getSelectedIndex());
                nominal = checkNominal();
                total = calculateTotal(nominal, 0.05);
            }else{
                namaOperator = choiceOperator.getString(choiceOperator.getSelectedIndex());
                nominal = checkNominal();
                total = calculateTotal(nominal,0.06);
            }
        }
        
        orderPulsa();
        display.setCurrent(formCheckOut);
    }

    public void commandAction(Command c, Item item) {
        
    }
    
    private double calculateTotal(double nominal, double persentase){
        return  nominal + (nominal * persentase);
    }
    
    private double checkNominal(){
        double nominalPulsa = 0;
        if(choiceNominal.getSelectedIndex() == 0){
            nominalPulsa = 5000;
        }else if(choiceNominal.getSelectedIndex() == 1){
            nominalPulsa = 10000;
        }else{
            nominalPulsa = 25000;
        }
        return nominalPulsa;
    }
    
    private void orderPulsa(){
        strNama = new StringItem("Nama Pelanggan : ", txtNama.getString());
        strAlamat = new StringItem("Alamat Pelanggan : ", txtAlamat.getString());
        strNoTelepon = new StringItem("No Telepon : ", txtNoTelpon.getString());
        strTanggal = new StringItem("Tanggal order : ", formatTanggalOrder());
        strOperator = new StringItem("Operator : ", namaOperator);
        strNominal = new StringItem("Nominal Pembelian : ", Double.toString(nominal));
        strTotal = new StringItem("Total harga : ", Double.toString(total));
        formCheckOut.append(strNama);
        formCheckOut.append(strAlamat);
        formCheckOut.append(strNoTelepon);
        formCheckOut.append(strTanggal);
        formCheckOut.append(strOperator);
        formCheckOut.append(strNominal);
        formCheckOut.append(strTotal);
    }
    
    private String formatTanggalOrder(){
        Calendar calendar = Calendar.getInstance();
        Date tanggalOrder = datePenjualan.getDate();
        calendar.setTime(tanggalOrder);
        
        int hari = calendar.get(Calendar.DAY_OF_MONTH);
        int noBulan = calendar.get(Calendar.MONTH);
        int tahun = calendar.get(Calendar.YEAR);
        
        String[]namaBulans = {"Januari","Februari","Maret","April",
                "Mei","Juni","Juli","Agustus","September","Oktober"
                ,"Nopember","Desember"
        };
        String bulan = namaBulans[noBulan];
        String value = hari + "-" + bulan + "-" + tahun;
        return value;
    }
    
    private void clearDisplay(){
        txtNama.setString("");
        datePenjualan.setDate(null);
        txtAlamat.setString("");
        txtNoTelpon.setString("");
        choiceNominal.setSelectedIndex(0, true);
        choiceOperator.setSelectedIndex(0, true);
        nominal=total=0;
        formCheckOut.deleteAll();
    }
  
}
