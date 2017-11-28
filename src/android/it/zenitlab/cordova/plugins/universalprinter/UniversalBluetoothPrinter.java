package it.zenitlab.cordova.plugins.universalprinter;

import com.RT_Printer.BluetoothPrinter.BLUETOOTH.BluetoothPrintDriver;

import java.io.IOException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
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
                String msg = args.getString(1);
                sendData(callbackContext, mac, msg);
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
    void sendData(final CallbackContext callbackContext, final String mac, final String msg) throws IOException {
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

                                String tmpString1 = "GRUPO GIRALDO SAS";
                                String tmpString2 = "Nit: 900.338.568-8";
                                String tmpString3 = "Tel: (572)4471825 /(572)6684493";
                                String tmpString4 = "Dir: Cra 5 Nrt # 46BN-31, Cali";
                                String tmpString5 = "Vendedor: VENDEDOR GENERAL -";
                                String tmpString6 = "Fecha: 2017-10-06 11:59";
                                String tmpString7 = "Tercero/Cliente: BASTO FABIAN";
                                String tmpString8 = "Nit/Cedula: 14622384";
                                String tmpString9 = "Medio de Pago: Efectivo - Contra Entrega";
                                String tmpString12 = "--------------------------------";

                                BluetoothPrintDriver.Begin();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.AddAlignMode((byte)1);
                                BluetoothPrintDriver.SetLineSpace((byte)30);    
                                BluetoothPrintDriver.SetZoom((byte)0x10);        
                                BluetoothPrintDriver.ImportData(tmpString1);
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
                                BluetoothPrintDriver.ImportData(tmpString2);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(tmpString3);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(tmpString4);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(tmpString5);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(tmpString12);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(tmpString6);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(tmpString7);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(tmpString8);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(tmpString9);
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData(tmpString12);
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

