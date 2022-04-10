var exec = require('cordova/exec');

var PLUGIN_NAME = 'IminPrinter';

var IminPrinter = {

    printText: function(text) {
        return new Promise(function(resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, 'printText', [text]);
        });
    },

    checkStatus: function() {
        return new Promise(function(resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, 'checkStatus', []);
        });
    },

    setTextStyleBold: function() {
        return new Promise(function(resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, 'setTextStyleBold', []);
        });
    },

    printBitmap: function(text) {
        return new Promise(function(resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, 'printBitmap', [text]);
        });
    },
    initPrinter: function() {
        return new Promise(function(resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, 'initPrinter', []);
        });
    },
    printAndFeedPaper: function(value) {
        return new Promise(function(resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, 'printAndFeedPaper', [value]);
        });
    },



};

module.exports = IminPrinter;