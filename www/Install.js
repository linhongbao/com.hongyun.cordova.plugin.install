var exec = require('cordova/exec');
module.exports = {
	install: function (message, win, fail) {
        exec(win, fail, 'Install', 'install', [message]);
	}
};