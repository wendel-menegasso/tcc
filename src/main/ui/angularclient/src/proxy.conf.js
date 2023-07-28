const PROXY_CONFIG = [
  {
    context: ['/'],
    target: 'http://localhost:9090',
    secure: true,
    logLevel: 'debug'
  }
]

module.exports = PROXY_CONFIG;
