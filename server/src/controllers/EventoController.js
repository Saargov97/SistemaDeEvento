const { Evento } = require('../models')


module.exports = {
  async index (req, res) {
    try {
      const eventos = await Evento.findAll({
        limit: 50
      })
      res.send(eventos)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar a lista de eventos'
      })
    }
  },
  async getUser (req, res) {
    try {
      const eventos = await Evento.findAll({
        where: {
          id: req.params.userId
        },
        limit: 50
      })
      res.send(eventos)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar a lista de eventos'
      })
    }
  },
  async post (req, res) {
    try {
      const evento = await Evento.create(req.body)
      res.send(evento)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao salvar evento'
      })
    }
  },
  async put (req, res) {
    try {
      const evento = await Evento.update(req.body, {
        where: {
          id: req.params.eventoId
        }
      })
      res.send(evento)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao salvar evento'
      })
    }
  },
  async delete (req, res) {
    try {
      await Evento.destroy({
        where: {
          id: req.params.eventoId
        }
      })
      res.send('')
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao deletar evento'
      })
    }
  },
  async show (req, res) {
    try {
      const evento = await Evento.findOne({
        where: {
          id: req.params.eventoId
        }
      })
      res.send(evento)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar o evento'
      })
    }
  }
}
