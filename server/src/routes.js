const AuthenticationController = require('./controllers/AuthenticationController')
const AuthenticationControllerPolicy = require('./policies/AuthenticationControllerPolicy')
const EventoController = require('./controllers/EventoController')

module.exports = (app) => {
  app.post('/register', AuthenticationControllerPolicy.register, AuthenticationController.register)
  app.post('/login', AuthenticationController.login)
  app.put('/user/:userId', AuthenticationController.put)
  app.get('/user/:userId', AuthenticationController.show)
  app.get('/eventos', EventoController.index)
  app.post('/eventos', EventoController.post)
  app.get('/eventos/:eventoId', EventoController.show)
  app.get('/eventosUsuario/:userId', EventoController.getUser)
  app.put('/eventos/:eventoId', EventoController.put)
  app.delete('/eventos/:eventoId', EventoController.delete)
}
