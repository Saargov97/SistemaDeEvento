const { User } = require('../models')
const jwt = require('jsonwebtoken')
const config = require('../config/config')

function jwtSignUser(user) {
  const ONE_WEEK = 60 * 60 * 24 * 7
  return jwt.sign(user, config.authentication.jwtSecret, {
    expiresIn: ONE_WEEK
  })
}

module.exports = {
  async register(req, res) {
    try {
      const user = await User.create(req.body)
      const userJson = user.toJSON()
      res.send({
        user: userJson,
        token: jwtSignUser(userJson)
      })
    } catch (err) {
      res.status(400).send({
        error: 'This email account is already in use.'
      })
    }
  },
  async put (req, res) {
    try {
      const user = await User.update(
        { 
          nom_pessoa: req.body.nom_pessoa,
          num_cpf: req.body.num_cpf
        }, 
        {
          where: {
            id: req.params.userId
        }
      })
      res.send(user)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao salvar usuário'
      })
    }
  },
  async index (req, res) {
    try {
      const users = await User.findAll({
        limit: 50
      })
      res.send(users)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar a lista de usuários'
      })
    }
  },
  async show (req, res) {
    try {
      const user = await User.findOne({
        where: {
          id: req.params.userId
        }
      })
      res.send(user)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar o user'
      })
    }
  },
  async login(req, res) {
    try {
      console.log('1')
      const { email, password } = req.body
      console.log('2')
      const user = await User.findOne({
        where: {
          email: email
        }
      })
      console.log('3')
      console.log(user.toJSON())
      if (!user) {
        return res.status(403).send({
          error: 'Usuário inválido!'
        })
      }
      const isPasswordValid = user.comparePassword(password)    
      console.log(isPasswordValid)  
      if (!isPasswordValid) {
        return res.status(403).send({
          error: 'Senha inválida!'
        })
      }
      const userJson = user.toJSON()
      res.send({
        user: userJson,
        token: jwtSignUser(userJson)
      })
    } catch (err) {
      console.log(err)
      res.status(500).send({
        error: 'Ocorreu um erro ao tentar logar'
      })
    }
  }
}
