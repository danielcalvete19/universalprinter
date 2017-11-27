# universalprinter

A Cordova/Phonegap driver for Universal bluetooth printers

##Usage
You can send data in Srings commands:

```
cordova.plugins.universalprinter.print("hola", "AC:3F:A4:57:71:79",
    function(success) { 
        alert("Print ok"); 
    }, function(fail) { 
        alert(fail); 
    }
);
```

##Install
###Cordova

```
cordova plugin add https://github.com/danielcalvete19/cordovaPluginUniversalPrinter.git
```

###Very important!

This plugin is in developer version, you need to set MAC Address of your printer at following file:


