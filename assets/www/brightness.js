var Brightness = function() {};

Brightness.prototype.get = function (successCallback, failureCallback) {
	return cordova.exec(
			successCallback,
			failureCallback,
			'BrightnessPlugin', // plugin name in plugins.xml
			'get',              // expected identifier for the Action
			[]);
};
	
Brightness.prototype.set = function (value, successCallback, failureCallback) {
	return cordova.exec(
			successCallback,
			failureCallback,
			'BrightnessPlugin', // plugin name in plugins.xml
			'set',              // expected identifier for the Action
			[value]);
};


cordova.addConstructor(function() {
	cordova.addPlugin('brightness', new Brightness());
});
