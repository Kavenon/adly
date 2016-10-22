var path = require('path');
var webpack = require('webpack');

var env = require('./webpack.env');
var ROOT = env.ROOT;
var DEST = env.DEST;

var process = require('process');
var Config = require('webpack-config');


module.exports = new Config.Config().merge({
    devtool: 'source-map',
    entry: {
        app: env.ROOT + '/app.js',
        vendor: ['jquery', 'bootstrap', 'angular', 'angular-ui-bootstrap']
    },
    output: {
        path: DEST,
        filename: '[name].bundle.js',
        publicPath: env.PUBLIC_PATH
    },
    module: {
        loaders: [
            // **IMPORTANT** This is needed so that each bootstrap js file required by
            // bootstrap-webpack has access to the jQuery object
            { test: /bootstrap\/js\//, loader: 'imports?jQuery=jquery' },
            { test: /\.scss$/, loaders: ['raw-loader', 'sass-loader'] },
            {test: /\.css$/, loader: 'style-loader!css-loader'},
            {
                test: /\.html$/,
                loader: 'ngtemplate!html'
            },
            // Needed for the css-loader when [bootstrap-webpack](https://github.com/bline/bootstrap-webpack)
            // loads bootstrap's css.
            { test: /\.(png|woff|woff2|eot|ttf|svg|jpg)$/, loader: 'url-loader?limit=100000' },
            { test: /\.woff(2)?(\?v=\d+\.\d+\.\d+)?$/,   loader: 'url?limit=10000&mimetype=application/font-woff' },
            { test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,    loader: 'url?limit=10000&mimetype=application/octet-stream' },
            { test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,    loader: 'file' },
            { test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,    loader: 'url?limit=10000&mimetype=image/svg+xml' },
            
          
            {test: /src.*\.js$/, loaders: ['ng-annotate']}
        ]
    },
    node: {
        net: 'empty',
        fs: 'empty',
        tls: 'empty',
        dns: 'empty'
    },
    resolve: {
        modulesDirectories: ['node_modules'],
        alias: {
            modules:  path.resolve(env.ROOT, 'modules'),
            assets:  path.resolve(env.ROOT, 'assets'),
            npm:  env.NODE
        }
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin('vendor', 'vendor.bundle.js'),
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery'
        }),
        new webpack.DefinePlugin({
            __PUBLIC_PATH__: JSON.stringify(env.PUBLIC_PATH)
        })
    ]
});
