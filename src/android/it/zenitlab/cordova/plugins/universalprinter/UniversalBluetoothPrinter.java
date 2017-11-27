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
                                BluetoothPrintDriver.Begin();
                                String tmpString = msg;
                                BluetoothPrintDriver.ImportData(tmpString);
                                BluetoothPrintDriver.ImportData("\r");
                                BluetoothPrintDriver.LF();
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

                        
                    }     

                } catch (Exception e) {
                    // Handle communications error here.
                    callbackContext.error(e.getMessage());
                }
            }
        }).start();
    }
}

