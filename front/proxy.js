const { createProxyMiddleware } = require("http-proxy-middleware");
const express = require("express");

const app = express();

app.use(
  "/api", // Proxy all requests starting with /api
  createProxyMiddleware({
    target: "http://localhost:8080", // Target backend
    changeOrigin: true,
    pathRewrite: { "^/api": "" }, // Keep the API path
  })
);

app.listen(3000, () => {
  console.log("Proxy running on http://localhost:3000");
});