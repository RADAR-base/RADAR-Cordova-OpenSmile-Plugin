/*global cordova, module*/

module.exports = {
    start: function (name, config, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "OpenSmile", "start", [name, config]);
    },
    stop: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "OpenSmile", "stop", [name]);
    }
};
