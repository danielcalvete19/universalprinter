package it.zenitlab.cordova.plugins.universalprinter;

import com.RT_Printer.BluetoothPrinter.BLUETOOTH.BluetoothPrintDriver;

import java.io.IOException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import android.util.Log;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Message;

public class UniversalBluetoothPrinter extends CordovaPlugin {

    private static final String LOG_TAG = "UniversalBluetoothPrinter";
    // Name of the connected device
    private String mConnectedDeviceName = null;
    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;
    // Member object for the chat services
    private BluetoothPrintDriver mChatService = null;

    public UniversalBluetoothPrinter() {
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals("print")) {
            try {
                String mac = args.getString(0);
                String textPrint = args.getJSONArray(1);
                sendData(callbackContext, mac, textPrint);
            } catch (IOException e) {
                Log.e(LOG_TAG, e.getMessage());
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    /*
     * This will send data to be printed by the bluetooth printer
     */
    void sendData(final CallbackContext callbackContext, final String mac, final JSONArray textPrint) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    // Get local Bluetooth adapter
                    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                    // If the adapter is null, then Bluetooth is not supported
                    if (mBluetoothAdapter == null) {

                        callbackContext.error("Bluetooth is not available");
                    }else{

                        if (BluetoothPrintDriver.OpenPrinter(mac)){
                            if(BluetoothPrintDriver.IsNoConnection()){
                                callbackContext.error("Could not connect to " + mac);
                            }else{
                                // BluetoothPrintDriver.Begin();
                                // String tmpString = msg;
                                // BluetoothPrintDriver.ImportData(tmpString);
                                // BluetoothPrintDriver.ImportData("\r");
                                // BluetoothPrintDriver.LF();
                                // BluetoothPrintDriver.LF();
                                // BluetoothPrintDriver.excute();
                                // BluetoothPrintDriver.ClearData();

                                String NombreEmpresa = "GRUPO GIRALDO SAS";
                                String Nit = "Nit: 900.338.568-8";
                                String Telefonos = "Tel: (572)4471825 /(572)6684493";
                                String Direccion = "Dir: Cra 5 Nrt # 46 BN-31, Cali";
                                String VendedorNombre = "Vendedor: VENDEDOR GENERAL -";
                                String Fecha = "Fecha: 2017-10-06 11:59";
                                String Tercero = "Tercero/Cliente: BASTO FABIAN";
                                String TerceroNit = "Nit/Cedula: 14622384";
                                String MedioPago = "Medio de Pago: Efectivo - Contra Entrega";
                                String Contacto = "Contacto: Fabian contacto";

                                String lineaDelgada = "--------------------------------";

                                BluetoothPrintDriver.Begin();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.AddAlignMode((byte)1);
                                BluetoothPrintDriver.SetLineSpace((byte)30);    
                                BluetoothPrintDriver.SetZoom((byte)0x7);        
                                BluetoothPrintDriver.ImportData(NombreEmpresa);
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();

                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();

                                BluetoothPrintDriver.AddAlignMode((byte)0);       
                                BluetoothPrintDriver.SetZoom((byte)0x00);
                                BluetoothPrintDriver.ImportData(Nit);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(Telefonos);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(Direccion);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(VendedorNombre);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(lineaDelgada);

                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(Fecha);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(Tercero);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(TerceroNit);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(MedioPago);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(Contacto);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(lineaDelgada);

                                JSONArray arrayProducts = new JSONArray();
                                JSONObject product, summaryCart, summaryShipping = new JSONObject();

                                arrayProducts = textPrint.getJSONArray(10);
                                summaryCart = textPrint.getJSONObject(11);
                                summaryShipping = textPrint.getJSONObject(12);

                                for (int i=0; i < arrayProducts.length(); i++) {
                                    product = arrayProducts.getJSONObject(i);
                                }

                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();

                                BluetoothPrintDriver.close();

                                Thread.sleep(500);
                                callbackContext.success("Printed correctly");
                            }

                        }
                        else 
                        {
                            callbackContext.error("Could not connect to " + mac);
                        }



                        //mChatService = new BluetoothPrintDriver(this, mHandler);

                        // Get the BLuetoothDevice object
                        // BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(mac);
                        // // Attempt to connect to the device
                        // BluetoothPrintDriver.connect(device);

                        // if(BluetoothPrintDriver.IsNoConnection()){
                        //     callbackContext.success("Could not connect to " + mac);
                        // }else{
                        //     //BluetoothPrintDriver.StatusInquiry();
                        //     // BluetoothPrintDriver.Begin();

                        //     // String tmpContent = msg;

                        //     // BluetoothPrintDriver.BT_Write(tmpContent);
                        //     // BluetoothPrintDriver.BT_Write("\r");

                        //     // BluetoothPrintDriver.LF();
                        //     // BluetoothPrintDriver.LF();


                        //     BluetoothPrintDriver.Begin();
                        //     BluetoothPrintDriver.LF();
                        //     BluetoothPrintDriver.LF();
                        //     BluetoothPrintDriver.SetAlignMode((byte)1);//¾ÓÖÐ
                        //     BluetoothPrintDriver.SetLineSpacing((byte)50);  
                        //     BluetoothPrintDriver.SetFontEnlarge((byte)0x05);//±¶¸ß£¬±¶¿í        
                        //     BluetoothPrintDriver.BT_Write(tmpString1);
                            
                        //     BluetoothPrintDriver.LF();
                        //     BluetoothPrintDriver.LF();
                        //     BluetoothPrintDriver.LF();

                        //     BluetoothPrintDriver.SetAlignMode((byte)0);//×ó¶ÔÆë     
                        //     BluetoothPrintDriver.SetFontEnlarge((byte)0x00);//Ä¬ÈÏ¿í¶È¡¢Ä¬ÈÏ¸ß¶È
                        //     BluetoothPrintDriver.BT_Write(tmpString2);
                        //     BluetoothPrintDriver.LF();
                        //     BluetoothPrintDriver.BT_Write(tmpString3);
                        //     BluetoothPrintDriver.LF();
                        //     BluetoothPrintDriver.BT_Write(tmpString4);
                        //     BluetoothPrintDriver.LF();
                        //     BluetoothPrintDriver.BT_Write(tmpString12);
                        //     BluetoothPrintDriver.LF();
                        //     BluetoothPrintDriver.BT_Write(tmpString5);
                        //     BluetoothPrintDriver.LF();
                        //     BluetoothPrintDriver.BT_Write(tmpString12);
                        //     BluetoothPrintDriver.LF();
                        //     BluetoothPrintDriver.BT_Write(tmpString6);
                        //     BluetoothPrintDriver.LF();
                        //     BluetoothPrintDriver.BT_Write(tmpString7);
                        //     BluetoothPrintDriver.LF();
                        //     BluetoothPrintDriver.BT_Write(tmpString12);
                        //     BluetoothPrintDriver.LF();
                        //     BluetoothPrintDriver.BT_Write(tmpString8);
                        //     BluetoothPrintDriver.LF();


                        //     Thread.sleep(500);
                        //     callbackContext.success("Stampa terminata");

                        // }

                        
                    }                    

                } catch (Exception e) {
                    // Handle communications error here.
                    callbackContext.error(e.getMessage());
                }
            }
        }).start();
    }

}

