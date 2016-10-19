var path = require('path');

module.exports = {
    NODE : path.resolve(__dirname, 'node_modules/'),
    BASE : path.resolve(__dirname, 'src/main/resources/static/'),
    ROOT : path.resolve(__dirname, 'src/main/resources/static/frontend/src'),
    DEST : path.resolve(__dirname, 'src/main/resources/static/frontend/dist'),
    PUBLIC_PATH : "/frontend/dist/"
};
