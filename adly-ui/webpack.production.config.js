var Config = require('webpack-config');
var webpack = require('webpack');
var NgAnnotatePlugin = require('ng-annotate-webpack-plugin');
var CopyWebpackPlugin = require('copy-webpack-plugin');
var env = require('./webpack.env');

module.exports = new Config.Config().extend('webpack.base.config.js').merge({
    plugins: [
        new webpack.optimize.UglifyJsPlugin({
            comments: false,
            compress: {
                warnings: false
            }
        }),
        new webpack.DefinePlugin({
            __PRODUCTION__: JSON.stringify('true')
        })
    ]
});
