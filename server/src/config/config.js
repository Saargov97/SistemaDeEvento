module.exports = {
  port: process.env.PORT || 8081,
  db: {
    database: process.env.DB_NAME || 'fincontrolJS',
    user: process.env.DB_USER || 'fincontrolJS',
    password: process.env.DB_PASS || 'fincontrolJS',
    options: {
      dialect: process.env.DIALECT || 'sqlite',
      host: process.env.HOST || 'localhost',
      storage:  './fincontrolJS.sqlite'
    }
  },
  authentication: {
    jwtSecret: process.env.JWT_SECRET || 'secret'
  }
}
