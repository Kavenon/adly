var Config = require('webpack-config');

Config.environment.setAll({
        env: function() {
             return process.env.NODE_ENV;
        }
});

module.exports = new Config.Config().extend('webpack.[env].config.js');