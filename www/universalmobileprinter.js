var exec = require('cordova/exec');

exports.print = function(mac, str, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, 'UniversalBluetoothPrinter', 'print', [mac, str]);
};

