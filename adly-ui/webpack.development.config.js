var webpack = require('webpack');
var Config = require('webpack-config');
var path = require('path');
var CopyWebpackPlugin = require('copy-webpack-plugin');

var env = require('./webpack.env');
module.exports = new Config.Config().extend('webpack.base.config.js').merge({
    devServer: {
        port: 9090,
        proxy: {
            '!/frontend/dist/**': {
                target: 'http://localhost:8080'
            }
        },
        publicPath: 'http://localhost:9090/frontend/dist'
    },
    plugins: [
        new webpack.DefinePlugin({
            __DEV__: JSON.stringify('true'),
            __PRODUCTION__: JSON.stringify('false')
        })
    ]
});
