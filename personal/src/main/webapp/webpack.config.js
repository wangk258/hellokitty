/**
 * Created by qmtv on 2016/9/8.
 */
var path = require("path");
var webpack = require("webpack");
var ExtractTextPlugin = require("extract-text-webpack-plugin");
var AutoPrefixer = require("autoprefixer");
module.exports = {
    entry: {
    	'admin/app':path.join(__dirname,'static',"js","admin","admin.js")
    },
    output: {
        path: path.join(__dirname,'static',"dest"),
        filename: "[name].js",
    },
    devtool:false,
    module: {
        //加载器配置
        loaders: [
            { test: /\.css$/, loader: ExtractTextPlugin.extract('style-loader',['css-loader'])},
            { test: /\.(png|jpg)$/, loader: 'url-loader?limit=8192'},
            { test: /\.(sass|scss)$/, loader: ExtractTextPlugin.extract('style-loader', ['css-loader', 'postcss-loader', 'sass-loader'])},
            { test: /\.vue$/, loader: 'vue'},
            {
                test: /\.(eot|svg|ttf|woff|woff2)(\?\S*)?$/,
                loader: 'file-loader'
            }
        ]
    },
    vue:{
        postcss: function(){
            return [AutoPrefixer]
        },
        loaders: {
            sass: ExtractTextPlugin.extract('vue-style-loader', ['css-loader','sass-loader'])
        },
    },
    postcss: function() {
        return [AutoPrefixer];
    },
    plugins: [
        new ExtractTextPlugin("styles.css")
    ],
    resolve: {
        //自动扩展文件后缀名，意味着我们require模块可以省略不写后缀名
        extensions: ['', '.js', '.json', '.scss'],
        //模块别名定义，方便后续直接引用别名，无须多写长长的地址
        alias: {
            'vue': 'vue/dist/vue.js'
        }
    }
};
