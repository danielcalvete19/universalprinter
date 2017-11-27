/*
 * cordova.plugins.zbtprinter.print = function(str, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, 'UniversalBluetoothPrinter', 'print', [str]);
};
*/

//var ZebraBluetoothPrinterLoader = function (require, exports, module) {

	var exec = require("cordova/exec");
 
 	function UniversalBluetoothPrinter() {
    }

    UniversalBluetoothPrinter.prototype.esegui = function (successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'UniversalBluetoothPrinter', 'print', ["AC:3F:A4:57:71:79", "ciccio"]);
    };
    var bluetoothPrinter = new UniversalBluetoothPrinter();
    module.exports = bluetoothPrinter;
//};

//ZebraBluetoothPrinterLoader(require, exports, module);
//cordova.define("cordova/plugins/zbtprinter", UniversalBluetoothPrinter);
