const AuthenticationController = require('./controllers/AuthenticationController')
const AuthenticationControllerPolicy = require('./policies/AuthenticationControllerPolicy')
const BanksController = require('./controllers/BanksController')

module.exports = (app) => {
  app.post('/register', AuthenticationControllerPolicy.register, AuthenticationController.register)
  app.post('/login', AuthenticationController.login)
  app.get('/banks', BanksController.index)
  app.post('/banks', BanksController.post)
  app.get('/banks/:bankId', BanksController.show)
  app.put('/banks/:bankId', BanksController.put)
  app.delete('/banks/:bankId', BanksController.delete)
}
