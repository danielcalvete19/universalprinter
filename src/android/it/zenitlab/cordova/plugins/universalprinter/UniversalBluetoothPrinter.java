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

                        if (BluetoothPrintDriver.OpenPrinter(mac)) 
                        {
                            if(BluetoothPrintDriver.IsNoConnection()){
                                callbackContext.success("Could not connect to " + mac);
                            }else{
                                // BluetoothPrintDriver.Begin();
                                // String tmpString = msg;
                                // BluetoothPrintDriver.ImportData(tmpString);
                                // BluetoothPrintDriver.ImportData("\r");
                                // BluetoothPrintDriver.LF();
                                // BluetoothPrintDriver.LF();
                                // BluetoothPrintDriver.excute();
                                // BluetoothPrintDriver.ClearData();

                                BluetoothPrintDriver.Begin();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.AddAlignMode((byte)1);
                                BluetoothPrintDriver.SetLineSpace((byte)50);    
                                BluetoothPrintDriver.SetZoom((byte)0x11);        
                                BluetoothPrintDriver.ImportData('GRUPO GIRALDO SAS');
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
                                BluetoothPrintDriver.ImportData('Nit: 900.338.568-8');
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData('Tel: (572) 4471825 / (572) 6684493');
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData('Dire: Cra 5 Norte # 46BN - 31, Cali');
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData('Vendedor: VENDEDOR GENERAL -');
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData('--------------------------------');
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData('Fecha: 2017-10-06 11:59');
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData('Tercero/Cliente: BASTO FABIAN');
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData('Nit/Cedula: 14622384');
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData('Medio de Pago: Efectivo - Contra Entrega');
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();
                                BluetoothPrintDriver.ImportData('--------------------------------');
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

                                Thread.sleep(500);
                                callbackContext.success("Printed correctly");
                            }

                        }
                        else 
                        {
                            callbackContext.error("Could not connect to " + mac);
                        }
                        // mChatService = new BluetoothPrintDriver(this, mHandler);

                        // // Get the BLuetoothDevice object
                        // BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(mac);
                        // // Attempt to connect to the device
                        // mChatService.connect(device);

                        // if(BluetoothPrintDriver.IsNoConnection()){
                        //     return;
                        // }else{
                        //     //BluetoothPrintDriver.StatusInquiry();
                        //     BluetoothPrintDriver.Begin();

                        //     String tmpContent = msg;

                        //     BluetoothPrintDriver.BT_Write(tmpContent);
                        //     BluetoothPrintDriver.BT_Write("\r");

                        //     BluetoothPrintDriver.LF();
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

