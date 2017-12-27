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
                JSONObject textPrint = args.getJSONObject(1);
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
    void sendData(final CallbackContext callbackContext, final String mac, final JSONObject textPrint) throws IOException {
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

                                JSONArray arrayProducts, arrayTributario = new JSONArray();
                                JSONObject product, summaryOrder, summaryCart, summaryShipping, tributario = new JSONObject();
                                JSONObject summaryResolution = new JSONObject();

                                summaryOrder = textPrint.getJSONObject("general");
                                arrayProducts = textPrint.getJSONArray("productos");
                                summaryCart = textPrint.getJSONObject("canasta");
                                summaryShipping = textPrint.getJSONObject("envio");

                                try{
                                    arrayTributario = textPrint.getJSONArray("tributaria");
                                    summaryResolution = textPrint.getJSONObject("resolucion");
                                }catch (Exception e){

                                }

                                
                                String lineaDelgada = "--------------------------------";

                                BluetoothPrintDriver.Begin();

                                if(!textPrint.isNull("resolucion")){

                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.AddAlignMode((byte)1);
                                    BluetoothPrintDriver.SetLineSpace((byte)20);    
                                    BluetoothPrintDriver.SetZoom((byte)0x01);
                                    BluetoothPrintDriver.AddBold((byte)0x01);
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("RazonSocial"));        
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("NombreEmpresa"));
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
                                    BluetoothPrintDriver.AddBold((byte)0x00);
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("Nit"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("Telefonos"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("Direccion"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.ImportData(lineaDelgada);

                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();

                                    BluetoothPrintDriver.AddAlignMode((byte)1);
                                    BluetoothPrintDriver.SetLineSpace((byte)10);    
                                    BluetoothPrintDriver.SetZoom((byte)0x01);        
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("PedidoNro"));

                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();


                                    BluetoothPrintDriver.AddAlignMode((byte)0);       
                                    BluetoothPrintDriver.SetZoom((byte)0x00);
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("Fecha"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("Tercero"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("TerceroNit"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData(); //M.pago cantidad Q:
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("MedioPago"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("Contacto"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("VendedorNombre"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.ImportData(lineaDelgada);
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();

                                    for (int i=0; i < arrayProducts.length(); i++) {
                                        product = arrayProducts.getJSONObject(i);
                                        BluetoothPrintDriver.AddAlignMode((byte)0);
                                        BluetoothPrintDriver.ImportData("Item: " + product.getString("nombre"));
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.ImportData("Codigo: " + product.getString("codigo") + " U.M.: " + product.getString("medida"));
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.ImportData("Cant: " + product.getString("cantidad") + " Vr.Unt: " + product.getString("precioUnitario"));
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.AddAlignMode((byte)2);
                                        BluetoothPrintDriver.AddBold((byte)0x01);
                                        BluetoothPrintDriver.ImportData("Total: " + product.getString("subtotal"));
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.AddBold((byte)0x00);
                                        BluetoothPrintDriver.ImportData(lineaDelgada);
                                    }

                                    BluetoothPrintDriver.AddAlignMode((byte)2);

                                    if(!textPrint.isNull("resolucion")){
                                        BluetoothPrintDriver.ImportData("Vta Gravada: " + summaryCart.getString("subtotal"));
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.ImportData("IVA: " + summaryCart.getString("iva"));
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.ImportData("IMPOCONSUMO: " + summaryCart.getString("impoconsumo"));
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                    }

                                    BluetoothPrintDriver.AddBold((byte)0x01);
                                    BluetoothPrintDriver.ImportData("VALOR TOTAL: " + summaryCart.getString("valortotal"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.AddBold((byte)0x00);
                                    BluetoothPrintDriver.ImportData(lineaDelgada);

                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();

                                    if(!textPrint.isNull("resolucion") && !textPrint.isNull("tributaria")){
                                        BluetoothPrintDriver.AddAlignMode((byte)1); 
                                        BluetoothPrintDriver.ImportData("INFORMACION TRIBUTARIA");
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();

                                        for (int i=0; i < arrayTributario.length(); i++) {
                                            tributario = arrayTributario.getJSONObject(i);
                                            BluetoothPrintDriver.LF();
                                            BluetoothPrintDriver.excute();
                                            BluetoothPrintDriver.ClearData();

                                            BluetoothPrintDriver.AddAlignMode((byte)0);
                                            BluetoothPrintDriver.ImportData("%: " + tributario.getString("porcentaje") + " VLR_BASE: " + tributario.getString("valor_base"));
                                            BluetoothPrintDriver.LF();
                                            BluetoothPrintDriver.excute();
                                            BluetoothPrintDriver.ClearData();
                                            BluetoothPrintDriver.AddAlignMode((byte)2);
                                            BluetoothPrintDriver.ImportData("VLR_IMPUESTO: " + tributario.getString("valor_impuesto"));

                                            BluetoothPrintDriver.LF();
                                            BluetoothPrintDriver.excute();
                                            BluetoothPrintDriver.ClearData();
                                        }

                                        BluetoothPrintDriver.ImportData(lineaDelgada);
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        
                                    }

                                    if(textPrint.isNull("resolucion")){
                                        BluetoothPrintDriver.AddAlignMode((byte)1); 
                                        BluetoothPrintDriver.ImportData("INFORMACION DE DESPACHO");
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.AddAlignMode((byte)0);
                                        BluetoothPrintDriver.ImportData("Direccion: " + summaryShipping.getString("direccion"));
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.ImportData("Telefono: " + summaryShipping.getString("telefono"));
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.ImportData("Ciudad: " + summaryShipping.getString("ciudad"));
                                    }

                                    if(!textPrint.isNull("resolucion")){

                                        String numeroRes = summaryResolution.getString("numero");
                                        String fechaRes = summaryResolution.getString("fecha");
                                        String prefRes = summaryResolution.getString("prefijo");
                                        String minRes = summaryResolution.getString("minimo");
                                        String maxRes = summaryResolution.getString("maximo");

                                        BluetoothPrintDriver.AddAlignMode((byte)1); 
                                        BluetoothPrintDriver.ImportData("RESOLUCION");
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.ImportData("No.: " + numeroRes + " " + fechaRes);
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.ImportData("Factura: " + prefRes + "-" + minRes + " al " + prefRes + "-" + maxRes);
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.ImportData("SOMOS AUTORRETENEDORES EN RENTA Y CREE RESOL. 06224");
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.ImportData("JULIO 25 DE 2013");
                                        BluetoothPrintDriver.LF();
                                        BluetoothPrintDriver.excute();
                                        BluetoothPrintDriver.ClearData();
                                        BluetoothPrintDriver.ImportData("GRACIAS POR SU COMPRA");

                                    }

                                }else{

                                    BluetoothPrintDriver.AddAlignMode((byte)1);
                                    BluetoothPrintDriver.SetLineSpace((byte)10);    
                                    BluetoothPrintDriver.SetZoom((byte)0x01);        
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("PedidoNro"));

                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();


                                    BluetoothPrintDriver.AddAlignMode((byte)0);       
                                    BluetoothPrintDriver.SetZoom((byte)0x00);
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("Fecha"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("Tercero"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("VendedorNombre"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.ImportData(summaryOrder.getString("VendedorTelefono"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.ImportData(lineaDelgada);
                                    BluetoothPrintDriver.AddAlignMode((byte)2);
                                    BluetoothPrintDriver.AddBold((byte)0x01);
                                    BluetoothPrintDriver.ImportData("VALOR TOTAL: " + summaryCart.getString("valortotal"));
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.AddBold((byte)0x00);
                                    BluetoothPrintDriver.ImportData(lineaDelgada);
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();

                                    BluetoothPrintDriver.AddAlignMode((byte)1); 
                                    BluetoothPrintDriver.ImportData("FECHA DE ENTREGA");
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();

                                    BluetoothPrintDriver.ImportData("____  /  ____   /  ____");

                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.LF();
                                    BluetoothPrintDriver.excute();
                                    BluetoothPrintDriver.ClearData();
                                    BluetoothPrintDriver.ImportData("FAVOR REVISAR SU PEDIDO EN EL MOMENTO DE LA ENTREGA");

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

