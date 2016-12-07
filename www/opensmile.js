/*global cordova, module*/

module.exports = {
    start: function (name, path, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "OpenSmile", "start", [name, path]);
    },
    stop: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "OpenSmile", "stop", [name]);
    }
};
