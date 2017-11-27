package cordova.mobile.printer;

import com.RT_Printer.BluetoothPrinter.BLUETOOTH.BluetoothPrintDriver;

import java.io.IOException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
/*import com.zebra.sdk.comm.BluetoothConnectionInsecure;
import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.printer.PrinterStatus;
import com.zebra.sdk.printer.ZebraPrinter;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import com.zebra.sdk.printer.ZebraPrinterLanguageUnknownException;*/

public class CordovaMobilePrinterBluetooth extends CordovaPlugin {

    private static final String LOG_TAG = "CordovaMobilePrinterBluetooth";
    // Name of the connected device
    private String mConnectedDeviceName = null;
    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;
    // Member object for the chat services
    private BluetoothPrintDriver mChatService = null;

    public CordovaMobilePrinterBluetooth() {
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

                        if (BluetoothPrintDriver.OpenPrinter(mac)) {

                            mChatService = new BluetoothPrintDriver(this, mHandler);

                            // Get the BLuetoothDevice object
                            BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(mac);
                            // Attempt to connect to the device
                            mChatService.connect(device);

                            if(BluetoothPrintDriver.IsNoConnection()){
                                return;
                            }else{
                                //BluetoothPrintDriver.StatusInquiry();
                                BluetoothPrintDriver.Begin();

                                String tmpContent = msg;

                                // BluetoothPrintDriver.BT_Write(tmpContent);
                                // BluetoothPrintDriver.BT_Write("\r");

                                BluetoothPrintDriver.ImportData(tmpContent);
                                BluetoothPrintDriver.ImportData("\r");
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.LF();
                                BluetoothPrintDriver.excute();
                                BluetoothPrintDriver.ClearData();

                                Thread.sleep(500);
                                callbackContext.success("Stampa terminata");

                            }

                        }else{
                            callbackContext.error("printer is not ready");
                        }

                        
                    }                    


//                     // Stop the Bluetooth chat services
//                     if (mChatService != null) mChatService.stop();

//                     // Instantiate insecure connection for given Bluetooth MAC Address.
//                     Connection thePrinterConn = new BluetoothConnectionInsecure(mac);

//                     // Verify the printer is ready to print
//                     if (isPrinterReady(thePrinterConn)) {

//                         // Open the connection - physical connection is established here.
//                         thePrinterConn.open();

//                         // Send the data to printer as a byte array.
// //                        thePrinterConn.write("^XA^FO0,20^FD^FS^XZ".getBytes());
//                         thePrinterConn.write(msg.getBytes());


//                         // Make sure the data got to the printer before closing the connection
//                         Thread.sleep(500);

//                         // Close the insecure connection to release resources.
//                         thePrinterConn.close();
//                         callbackContext.success("Stampa terminata");
//                     } else {
// 						callbackContext.error("printer is not ready");
// 					}

                } catch (Exception e) {
                    // Handle communications error here.
                    callbackContext.error(e.getMessage());
                }
            }
        }).start();
    }

    // The Handler that gets information back from the BluetoothChatService
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case MESSAGE_STATE_CHANGE:
                switch (msg.arg1) {
                case BluetoothPrintDriver.STATE_CONNECTED:
                    break;
                case BluetoothPrintDriver.STATE_CONNECTING:
                    break;
                case BluetoothPrintDriver.STATE_LISTEN:
                case BluetoothPrintDriver.STATE_NONE:
                    break;
                }
                break;
            case MESSAGE_WRITE:
                break;
            case MESSAGE_READ:
                String ErrorMsg = null;
                byte[] readBuf = (byte[]) msg.obj;
                float Voltage = 0;
                if(D) Log.i(TAG, "readBuf[0]:"+readBuf[0]+"  readBuf[1]:"+readBuf[1]+"  readBuf[2]:"+readBuf[2]);
                if(readBuf[2]==0)
                    ErrorMsg = "NO ERROR!         ";
                else
                {
                    if((readBuf[2] & 0x02) != 0)
                        ErrorMsg = "ERROR: No printer connected!";
                    if((readBuf[2] & 0x04) != 0)
                        ErrorMsg = "ERROR: No paper!  ";
                    if((readBuf[2] & 0x08) != 0)
                        ErrorMsg = "ERROR: Voltage is too low!  ";
                    if((readBuf[2] & 0x40) != 0)
                        ErrorMsg = "ERROR: Printer Over Heat!  ";
                }
                Voltage = (float) ((readBuf[0]*256 + readBuf[1])/10.0);
                //if(D) Log.i(TAG, "Voltage: "+Voltage);
                DisplayToast(ErrorMsg+"                                        "+"Battery voltage£º"+Voltage+" V");

                //throw new ConnectionException("Cannot Print because the printer is paused.");

                break;
            case MESSAGE_DEVICE_NAME:
                // save the connected device's name
                break;
            case MESSAGE_TOAST:
                break;
            }
        }
    };

    private Boolean isPrinterReady(Connection connection) throws ConnectionException, ZebraPrinterLanguageUnknownException {
        Boolean isOK = false;
        connection.open();
        // Creates a ZebraPrinter object to use Zebra specific functionality like getCurrentStatus()
        ZebraPrinter printer = ZebraPrinterFactory.getInstance(connection);
        PrinterStatus printerStatus = printer.getCurrentStatus();
        if (printerStatus.isReadyToPrint) {
            isOK = true;
        } else if (printerStatus.isPaused) {
            throw new ConnectionException("Cannot Print because the printer is paused.");
        } else if (printerStatus.isHeadOpen) {
            throw new ConnectionException("Cannot Print because the printer media door is open.");
        } else if (printerStatus.isPaperOut) {
            throw new ConnectionException("Cannot Print because the paper is out.");
        } else {
            throw new ConnectionException("Cannot Print.");
        }
        return isOK;
    }

    // private void print (String msg, CallbackContext callbackContext) {

    //     if(BluetoothPrintDriver.IsNoConnection()){
    //         return;
    //     }

    //     cordova.getActivity().runOnUiThread( new Runnable() {
    //         @Override
    //         public void run() {
    //             BluetoothPrintDriver.Begin();
    //             String tmpString = msg;
    //             BluetoothPrintDriver.ImportData(tmpString);
    //             BluetoothPrintDriver.ImportData("\r");
    //             BluetoothPrintDriver.LF();
    //             BluetoothPrintDriver.LF();
    //             BluetoothPrintDriver.excute();
    //             BluetoothPrintDriver.ClearData();
    //         }
    //     });
    // }
}

