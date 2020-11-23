const { Inscricao } = require('../models')
const { User } = require('../models')
const { Evento } = require('../models')


module.exports = {
  async index (req, res) {
    try {
      const inscricoes = await Inscricao.findAll({
        limit: 50
      })
      res.send(inscricoes)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar a lista de inscrições'
      })
    }
  },
  async getOwner (req, res) {
    try {
      User.hasMany(Inscricao, {foreignKey: 'userId'})
      Inscricao.belongsTo(User, {foreignKey: 'userId'})
      Evento.hasMany(Inscricao, {foreignKey: 'eventoId', where: {userId: req.params.userId}})
      Inscricao.belongsTo(Evento, {foreignKey: 'eventoId'})
      const inscricoes = await Inscricao.findAll({
        include: [Evento, User]
      })
      res.send(inscricoes)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar a lista de inscrições'
      })
    }
  },
  async getUser (req, res) {
    try {
      User.hasMany(Inscricao, {foreignKey: 'userId'})
      Inscricao.belongsTo(User, {foreignKey: 'userId'})
      Evento.hasMany(Inscricao, {foreignKey: 'eventoId'})
      Inscricao.belongsTo(Evento, {foreignKey: 'eventoId'})
      const inscricoes = await Inscricao.findAll({
        where: {
          userId: req.params.userId
        }, include: [Evento, User]
      })
      res.send(inscricoes)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar a lista de inscrições'
      })
    }
  },
  async getEvento (req, res) {
    try {
      User.hasMany(Inscricao, {foreignKey: 'userId'})
      Inscricao.belongsTo(User, {foreignKey: 'userId'})
      Evento.hasMany(Inscricao, {foreignKey: 'eventoId'})
      Inscricao.belongsTo(Evento, {foreignKey: 'eventoId'})
      const inscricoes = await Inscricao.findAll({
        where: {
          eventoId: req.params.eventoId
        }, include: [Evento, User]
      })
      res.send(inscricoes)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar a lista de inscrições'
      })
    }
  },
  async post (req, res) {
    try {
      const inscricao = await Inscricao.create(req.body)
      res.send(inscricao)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao salvar inscrição'
      })
    }
  },
  async put (req, res) {
    try {
      const inscricao = await Inscricao.update(req.body, {
        where: {
          id: req.params.inscricaoId
        }
      })
      res.send(inscricao)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao salvar inscrição'
      })
    }
  },
  async delete (req, res) {
    try {
      await Inscricao.destroy({
        where: {
          id: req.params.inscricaoId
        }
      })
      res.send('')
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao deletar inscrição'
      })
    }
  },
  async show (req, res) {
    try {
      const inscricao = await Inscricao.findOne({
        where: {
          id: req.params.inscricaoId
        }
      })
      res.send(inscricao)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar o inscrição'
      })
    }
  }
}
