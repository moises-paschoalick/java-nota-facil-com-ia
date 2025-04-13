const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://3.238.149.189:8080',
      changeOrigin: true,
      pathRewrite: {
        '^/api': '', // remove /api do caminho
      },
    })
  );
}; 